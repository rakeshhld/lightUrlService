package com.rakesh.lightUrlService.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rakesh.lightUrlService.component.Base62ALgo;
import com.rakesh.lightUrlService.model.UrlDetails;
import com.rakesh.lightUrlService.repository.CustomURLRepo;
import com.rakesh.lightUrlService.repository.URLsRepository;


/**
 * @author Rakesh
 *
 */
@Service
@Scope("singleton")
public class URLService {
	
	@Autowired 
	URLsRepository urlRepository;  
	
	@Autowired
	CustomURLRepo customRepo;
	
	@Autowired
	Base62ALgo algo;
	
	@Value("${shorturl.length}")
	private int urlLength;
	
	@Value("${counter.start}")
	private int startCounter;
	
	@Value("${counter.end}")
	private int endCounter;
	
	private int currentCounter = 0;
	
	
	public List<UrlDetails> getAllURLS()   
	{  
	List<UrlDetails> books = new ArrayList<UrlDetails>();  
	urlRepository.findAll().forEach(books1 -> books.add(books1));  
	return books;  
	}  
	


	/**
	 * @param url
	 * @return
	 */
	public String saveOrUpdate(UrlDetails url)   
	{  
		 // byte[] arrayURL = url.getLongurl().getBytes();
		if(currentCounter == 0 ) {
			currentCounter = startCounter;
		}
		if(currentCounter >= startCounter && currentCounter <= endCounter) {
			String ShortURL= algo.encode(currentCounter);
			if(ShortURL.length() != urlLength) {
				ShortURL = getStringData(ShortURL);
			}
			url.setShorturl(ShortURL);
			urlRepository.save(url);
			currentCounter++;
			return url.getShorturl();
		}else {
			return "error need to reset counter";
		}
		
	}  
	 
	/**
	 * @param id
	 */
	public void delete(int id)   
	{  
		urlRepository.deleteById(id);  
	}  
	
	/**
	 * @param url
	 * @param txId
	 */
	public void update(UrlDetails url, int txId)   
	{  
		urlRepository.save(url);  
	}  
	
	/**
	 * @param data
	 * @return
	 */
	public String getStringData(String data) {
		if(data.length() < urlLength) {
			return getStringData(data+"a");
		}
		if(data.length() > urlLength) {
			return data.substring(0, urlLength-1);
		}
		
		return data;
	}
	
	/**
	 * @param shorturl
	 * @return
	 */
	public String getlongURl(String shorturl) {
		return customRepo.getLongURL(shorturl).getLongurl();
	}

}
