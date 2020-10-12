package com.rakesh.lightUrlService.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rakesh.exception.URLEmptyException;
import com.rakesh.lightUrlService.model.UrlDetails;

/**
 * @author Rakesh
 *
 */
@Repository
public class CustomURLRepoImpl implements CustomURLRepo {
	
	@PersistenceContext
    EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.rakesh.lightUrlService.repository.CustomURLRepo#getLongURL(java.lang.String)
	 * this is function finds actual url from db with respect to the encoded url
	 */
	@Override
	public UrlDetails getLongURL(String shortUrl) {
		CriteriaBuilder cb =  entityManager.getCriteriaBuilder();
		CriteriaQuery<UrlDetails> cr = cb.createQuery(UrlDetails.class);
		Root<UrlDetails> root = cr.from(UrlDetails.class);
		cr.select(root).where(cb.equal(root.get("shorturl"), shortUrl));
		List<UrlDetails> results = entityManager.createQuery(cr).getResultList();
		if(results.isEmpty()) 
			throw new URLEmptyException("Invalid short url", "provided url is not in our database");
		return results.get(0);
	}

}
