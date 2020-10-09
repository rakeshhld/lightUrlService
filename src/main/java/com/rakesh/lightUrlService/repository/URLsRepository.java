package com.rakesh.lightUrlService.repository;
import org.springframework.stereotype.Repository;
import com.rakesh.lightUrlService.model.UrlDetails;
import org.springframework.data.repository.CrudRepository;  

/**
 * @author 	Rakesh
 * This is the main interface which implements CrudRepository UrlDetails
 * new functions related to db s can be added in CustomURLRepo.java
 *
 */
@Repository
public interface URLsRepository	extends CrudRepository<UrlDetails, Integer>   {
	

}
