package com.rakesh.lightUrlService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.LightUrlServiceApplication;
import com.rakesh.lightUrlService.model.UrlDetails;

@RunWith(SpringRunner.class)
@ContextConfiguration( classes = LightUrlServiceApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class URLServiceTest {
	
	 @Resource
	 private URLService  service;
	 
	 @Test
		public void happyCase() {
			System.out.println("Start!!!!!!!!!!");
			String url = "https://www.youtube.com/";
			UrlDetails ud = new UrlDetails(url);
			String response = service.saveOrUpdate(ud);
			System.out.println("response"+response);
			String output = service.getlongURl(response);
			System.out.println("output"+output);
			service.delete(1);
			assertEquals(url, output);
			System.out.println("Stop!!!!!!!!!!");
		}
		
		@Test(expected=URLEmptyException.class)
		public void shorturlnotpresent() {
			String longurl = service.getlongURl("dbhfd");
			System.out.println("getting long url"+longurl);
		}
		
		@Test
		public void getAllURLs() {
			List<UrlDetails> udList =service.getAllURLS();
			System.out.println("list before delete"+udList);
			assertTrue(udList.isEmpty());
		}
		
		@Test
		public void deletelURLs() {
			System.out.println("new start!!!!!!!!!!!!!!!!!");
			service.getAllURLS();
			String url = "https://www.youtube.com/";
			UrlDetails ud = new UrlDetails(url);
			String response = service.saveOrUpdate(ud);
			service.getAllURLS();
			service.delete(2);
			List<UrlDetails> udList =service.getAllURLS();
			System.out.println("list after delete"+udList);
			assertTrue(udList.isEmpty());
		}	


}
