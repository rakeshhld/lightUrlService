package com.rakesh.lightUrlService.component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Rakesh
 * 
 * Class with component bean object.
 * This class provides algorithm to url shortening
 *
 */
@Component
public class Base62ALgo {
	private final String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char[] dictionary = base62.toCharArray(); 
	List<Character> result = new ArrayList<Character>();
	

/**
 * 
 * @param data which will be of type int 
 * 
 * @return this method returns a string which is encoded in base62 format for a given integer.
 */
public String encode(int data) {
	String encodedStr  = "";
	while(data > 0 ) {
		encodedStr =dictionary[data %62]+encodedStr;
		data/=62;
	}
	return encodedStr;
}






}
