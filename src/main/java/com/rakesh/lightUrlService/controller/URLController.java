package com.rakesh.lightUrlService.controller;

 

import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

 

import com.rakesh.lightUrlService.model.UrlDetails;

import com.rakesh.lightUrlService.service.URLService;

 

@RestController

public class URLController {

      

       @Autowired 

       URLService urlsService;

      

       @GetMapping("/url") 

       private List<UrlDetails> getAllBooks()  

       { 

       return urlsService.getAllURLS (); 

       } 

      

       @PostMapping("/url") 

       private String saveURL(@RequestBody UrlDetails urldDetails)  

       { 

              return  urlsService.saveOrUpdate(urldDetails); 

         //urldDetails.getShorturl(); 

       } 

      

       @PostMapping("/url/{shorturl}") 

       private String getLongURL(@PathVariable("shorturl") String shorturl)  

       { 

              return urlsService.getlongURl(shorturl);

       } 

 

}