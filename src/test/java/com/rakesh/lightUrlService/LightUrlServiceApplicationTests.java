package com.rakesh.lightUrlService;

 

import static org.junit.Assert.assertEquals;

 

 

 

import java.net.URISyntaxException;

 

import javax.annotation.Resource;

 

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.web.server.LocalServerPort;

 

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;

 

 

import com.rakesh.lightUrlService.model.UrlDetails;

import com.rakesh.lightUrlService.service.URLService;

 

@RunWith(SpringRunner.class)

@ContextConfiguration( classes = LightUrlServiceApplication.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class LightUrlServiceApplicationTests {

 

       @LocalServerPort

    private int port;

      

        @Autowired

           private TestRestTemplate restTemplate;

      

        @Resource

           private URLService  service;

      

//     @Autowired

//         private RestTemplate restTemplate;

      

//     @Test

//            public void addURL() {

//            assertTrue(this.restTemplate("http://localhost:"+port+"/url",UrlDetails.class));

//           

//     }

      

       @Test

       public void contextLoads() throws URISyntaxException {

             

              System.out.println("Start!!!!!!!!!!");

              String url = "https://www.youtube.com/";

              UrlDetails ud = new UrlDetails(url);

              String response = service.saveOrUpdate(ud);

              System.out.println("response"+response);

              String output = service.getlongURl(response);

              System.out.println("output"+output);

              assertEquals(url, output);

              System.out.println("Stop!!!!!!!!!!");

             

//            final String baseUrl = "http://localhost:8080"+"/url";

//            URI uri = new URI(baseUrl);

//            HttpHeaders headers = new HttpHeaders();

////          headers.setContentType(MediaType.APPLICATION_JSON);

//            UrlDetails ud = new UrlDetails("https://www.youtube.com/");

//            restTemplate = new RestTemplate();

//                headers = new HttpHeaders();

//                headers.setContentType(MediaType.APPLICATION_JSON);

//                UrlDetails udetails = restTemplate.postForObject(baseUrl, ud, UrlDetails.class);

//                assertNotNull(udetails);

//                assertNotNull(udetails.getShorturl());

////          HttpEntity<UrlDetails> request = new HttpEntity<>(ud, headers);

////          ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

////          boolean value = false;

////          if(result.equals("q0Uaaaa"))

////          {

////                value = true;

////          }

////          assertTrue(result.equals("q0Uaaaa"));

       }

      

 

}