package com.rakesh.lightUrlService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



/**
 * @author Rakesh
 *
 */
@SpringBootApplication
@EnableCaching
public class LightUrlServiceApplication {

	/**
	 * 
	 * @param args  main application to run the program
	 */
	public static void main(String[] args) {
		SpringApplication.run(LightUrlServiceApplication.class, args);
	}
	
	
   


}
