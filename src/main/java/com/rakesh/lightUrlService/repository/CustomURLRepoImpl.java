package com.rakesh.lightUrlService.repository;

 

import java.util.List;

 

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.criteria.CriteriaBuilder;

import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

 

import org.springframework.stereotype.Repository;

 

import com.rakesh.lightUrlService.model.UrlDetails;

 

@Repository

//@Transactional

public class CustomURLRepoImpl implements CustomURLRepo {

      

       @PersistenceContext

    EntityManager entityManager;

 

       @Override

       public UrlDetails getLongURL(String shortUrl) {

              CriteriaBuilder cb =  entityManager.getCriteriaBuilder();

              CriteriaQuery<UrlDetails> cr = cb.createQuery(UrlDetails.class);

              Root<UrlDetails> root = cr.from(UrlDetails.class);

              cr.select(root).where(cb.equal(root.get("shorturl"), shortUrl));

              List<UrlDetails> results = entityManager.createQuery(cr).getResultList();

                                  return results.get(0);

       }

 

}