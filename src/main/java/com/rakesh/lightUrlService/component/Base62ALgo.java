package com.rakesh.lightUrlService.component;

 

import java.io.ByteArrayOutputStream;

import java.math.BigInteger;

import java.util.ArrayList;

import java.util.List;

 

import org.springframework.stereotype.Component;

 

@Component

public class Base62ALgo {

       private final String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

       char[] dictionary = base62.toCharArray();

       List<Character> result = new ArrayList<Character>();

      

      

public String encode(BigInteger value) {

             

              BigInteger base = new BigInteger("" + dictionary.length);

              int exponent = 1;

              BigInteger remaining = value;

              while(true){

                     BigInteger a = base.pow(exponent);

                     BigInteger b = remaining.mod(a);

                     BigInteger c = base.pow(exponent - 1);

                     BigInteger d = b.divide(c);

                     result.add(dictionary[d.intValue()]);

                     remaining = remaining.subtract(b);

                     if(remaining.equals(BigInteger.ZERO)){

                           break;

                     }

                     exponent++;

              }

              StringBuffer sb = new StringBuffer();

              for(int i = result.size()-1; i >= 0; i--){

                     sb.append(result.get(i));

              }

              return sb.toString();

       }

 

public String encode(int data) {

       String encodedStr  = "";

       while(data > 0 ) {

              encodedStr =dictionary[data %62]+encodedStr;

              data/=62;

       }

       return encodedStr;

}

 

 

 

 

 

 

}