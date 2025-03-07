package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
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
       
    }

