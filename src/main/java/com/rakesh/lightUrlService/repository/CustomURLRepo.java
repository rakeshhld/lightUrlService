package com.rakesh.lightUrlService.repository;

 

import com.rakesh.lightUrlService.model.UrlDetails;

 

public interface CustomURLRepo {

       public UrlDetails getLongURL(String  shortUrl);

 

}