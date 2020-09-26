package com.rakesh.lightUrlService.repository;

import org.springframework.stereotype.Repository;

import com.rakesh.lightUrlService.model.UrlDetails;

import org.springframework.data.repository.CrudRepository; 

 

@Repository

public interface URLsRepository  extends CrudRepository<UrlDetails, Integer>   {

      

 

}