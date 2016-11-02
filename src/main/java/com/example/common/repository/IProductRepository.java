package com.example.common.repository;

import java.util.Map;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.springframework.stereotype.Component;

import com.example.common.cache.SimpleProductCacheKeyGenerator;
import com.example.common.entity.SimpleProduct;

// CRUD
@Component("productRepository")
public interface IProductRepository {
	
	// create
	/* The default behavior is to call {@link Cache#put(Object, Object)}
	 * ****after the annotated method is invoked****, this behavior can be changed by setting
	 * {@link #afterInvocation()} to false in which case
	 * {@link Cache#put(Object, Object)} will be called before the annotated method is
	 * invoked.
	  * <p>
	 * Example of caching the Domain object with a key generated from the String and
	 * int parameters. The {@link CacheValue} annotation is used to designate which
	 * parameter should be stored in the "domainDao" cache.
	 * <pre><code>
	 * package my.app;
	 * 
	 * public class DomainDao {
	 *   &#64;CachePut(cacheName="domainCache")
	 *   public void updateDomain(String domainId, int index, &#64;CacheValue Domain
	 * domain) {
	 *     ...
	 *   }
	 * }
	 * </code></pre>
	 * <p>
	 */
	//@CachePut(cacheName="productCache")// defined in member
	@CachePut(cacheName="productCache", cacheKeyGenerator=SimpleProductCacheKeyGenerator.class)
	SimpleProduct create(@CacheKey @CacheValue SimpleProduct simpleProduct);
	
	// read
	@CacheResult(cacheName="productCache")
	SimpleProduct read(Long id);
	
	// TODO: not sure if have to deal w/ key generator here ... 
	// update
	@CachePut(cacheName="productCache")
	SimpleProduct update(@CacheValue SimpleProduct simpleProduct);
	
	// delete
	@CacheRemove(cacheName="productCache")
	void delete(Long id);
	
	// have to add for testing
	Map<Long, SimpleProduct> getProductMap();
}
