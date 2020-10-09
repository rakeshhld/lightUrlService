package com.rakesh.lightUrlService;

import static org.junit.Assert.assertEquals;
import java.net.URISyntaxException;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.controller.URLController;
import com.rakesh.lightUrlService.model.UrlDetails;
import com.rakesh.lightUrlService.service.URLService;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = LightUrlServiceApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LightUrlServiceApplicationTests {

	 @Resource
	 private URLService  service;

	 
	@Test
	public void happyCase() throws URISyntaxException {
		System.out.println("Start!!!!!!!!!!");
		String url = "https://www.youtube.com/";
		UrlDetails ud = new UrlDetails(url);
		String response = service.saveOrUpdate(ud);
		System.out.println("response"+response);
		String output = service.getlongURl(response);
		System.out.println("output"+output);
		assertEquals(url, output);
		System.out.println("Stop!!!!!!!!!!");
	}
	
	@Test(expected=URLEmptyException.class)
	public void shorturlnotpresent() {
		service.getlongURl("dbhfd");
	}
	

}
