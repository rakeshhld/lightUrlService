package com.rakesh.lightUrlService.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.model.UrlDetails;
import com.rakesh.lightUrlService.service.URLService;

/**
 * @author Rakesh
 *
 */
@RestController
public class URLController {
	
	@Autowired  
	URLService urlsService;
	
	@GetMapping("/url")  
	public List<UrlDetails> getAllURLS()   
	{  
	return urlsService.getAllURLS();  
	}  
	
	/**
	 * @param urldDetails  json { longurl: '' , shorturl ''}
	 * @return string response or (text/plain) response which is shortcut url or encoded url
	 */
	@PostMapping("/url")  
	public String saveURL(  @RequestBody UrlDetails urldDetails,HttpServletResponse httpServletResponse)   
	{  
		if(null == urldDetails.getLongurl()) {
			throw new URLEmptyException("long url is not present","Please add loginurl as parameter");
		}
		if(urldDetails.getLongurl().trim().equals("")) {
			throw new URLEmptyException("long url is Empty","Please add any valid url string.");
		}
		httpServletResponse.setStatus(201);
    	return  urlsService.saveOrUpdate(urldDetails); 
	}  
	
	/**
	 * @param shorturl this is geturlwith input of encoded url 
	 * @param httpServletResponse this method redirects to the original url which was encoded
	 */
	@GetMapping("/url/{shorturl}")  
	public void getLongURL(@PathVariable("shorturl") String shorturl,HttpServletResponse httpServletResponse)   
	{  
		  httpServletResponse.setHeader("Location", urlsService.getlongURl(shorturl));
		  httpServletResponse.setStatus(302);
	}
	
	
	 
	@DeleteMapping("/url/{urlId}")  
	private void deleteUrl(@PathVariable("urlId") int urlId)   
	{  
	urlsService.delete(urlId);  
	}  

}
