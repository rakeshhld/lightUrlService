package com.rakesh.lightUrlService.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.LightUrlServiceApplication;
import com.rakesh.lightUrlService.model.UrlDetails;
import com.rakesh.lightUrlService.service.URLService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LightUrlServiceApplication.class)
@WebAppConfiguration
public class URLControllerTest {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Resource
	private URLService  service;


	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void createShorturl() throws Exception {
		UrlDetails newURLData = new UrlDetails("https://youtube.com");
		System.out.println(newURLData);
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		System.out.println("ss" + mvcResult.getResponse().getContentAsString());
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void getAllUrls() throws Exception {
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		System.out.println("esdcscd" + mvcResult.getResponse().getContentAsString());
		String content = mvcResult.getResponse().getContentAsString();
		UrlDetails[] urlList = this.mapFromJson(content, UrlDetails[].class);
		assertTrue(urlList.length == 0);
	}

	@Test
	public void addemptyURL() throws Exception {
		UrlDetails newURLData = new UrlDetails("");
		System.out.println(newURLData);
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		assertEquals("{\"message\":null,\"hint\":\"Please add any valid url string.\"}",
				mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void addNullURL() throws Exception {
		UrlDetails newURLData = new UrlDetails();
		System.out.println(newURLData);
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		assertEquals("{\"message\":null,\"hint\":\"Please add loginurl as parameter\"}",
				mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void deleteURL() throws Exception{
		service.getAllURLS();
		//service.delete(1);
		UrlDetails newURLData = new UrlDetails("https://youtube.com");
		System.out.println(newURLData);
		service.getAllURLS();
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		System.out.println("ss" + mvcResult.getResponse().getContentAsString());
		mvc.perform(MockMvcRequestBuilders.delete(url+"/2").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		MvcResult mvcResult3 =mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String content = mvcResult3.getResponse().getContentAsString();
		UrlDetails[] urlList = this.mapFromJson(content, UrlDetails[].class);
		System.out.println("URL List @@@"+urlList);
		assertTrue(urlList.length == 0);
	}
	
	@Test
	public void checkURLRedirection() throws Exception {
		UrlDetails newURLData = new UrlDetails("https://youtube.com");
		System.out.println(newURLData);
		String url = "/url";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(newURLData))).andReturn();
		System.out.println("ss" + mvcResult.getResponse().getContentAsString());
		String responsedata =  mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		MvcResult  mvcResult1 = mvc.perform(MockMvcRequestBuilders.get(url+"/"+responsedata).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int newstatus = mvcResult1.getResponse().getStatus();
		service.delete(1);
		assertEquals(302, newstatus);
	}
	
	

}
