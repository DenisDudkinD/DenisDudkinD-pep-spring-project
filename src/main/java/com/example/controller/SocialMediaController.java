package com.example.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account){

        if(account.getUsername() != "" && account.getPassword().length() >= 4){
         if(accountService.registerAccount(account) == null){
            return ResponseEntity.status(409).body(account);
         }
        accountService.registerAccount(account);
        return ResponseEntity.ok().body(account);
    
        }
        return ResponseEntity.status(400).body(account);
    }
    @PostMapping("login")
        public ResponseEntity<Account> login(@RequestBody Account account){
            if (accountService.loginAccount(account) == null){
            return ResponseEntity.status(401).body(account);
        }
            account = accountService.loginAccount(account);
            return ResponseEntity.ok().body(account);
        }
    @PostMapping("messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        if(accountService.findByID(message.getPostedBy())==null){
            return ResponseEntity.status(400).body(message);
        }
        if(messageService.postMessage(message) == null){
            return ResponseEntity.status(400).body(message);
       }
        message = messageService.postMessage(message);
        return ResponseEntity.ok().body(message);

    }
    @GetMapping("messages")
    public ResponseEntity<List<Message>>  getMessageList(){
        return ResponseEntity.ok().body(messageService.getAllMessages());
    }
    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessagebyId(@PathVariable Integer messageId){
        return ResponseEntity.ok().body(messageService.getMessageById(messageId));
    }
    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessagebyId(@PathVariable Integer messageId){

      if(messageService.getMessageById(messageId) == null){
            return ResponseEntity.ok().body(null);
        }else{
        messageService.deleteMessageById(messageId);
        return ResponseEntity.ok().body(1);} 
    }
    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> updateMessagebyID(@PathVariable Integer messageId, @RequestBody Message messageText){
        if(messageService.getMessageById(messageId)==null || messageText.getMessageText().length() >255 || messageText.getMessageText() == ""){
            return ResponseEntity.status(400).body(0);
        }
        Message message = messageService.getMessageById(messageId);
        message.setMessageText(messageText.getMessageText());
        message = messageService.postMessage(message);

        return ResponseEntity.ok().body(1);}
    
    }

