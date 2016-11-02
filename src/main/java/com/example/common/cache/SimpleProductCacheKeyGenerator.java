package com.example.common.cache;

import java.lang.annotation.Annotation;

import javax.cache.annotation.CacheInvocationParameter;
import javax.cache.annotation.CacheKeyGenerator;
import javax.cache.annotation.CacheKeyInvocationContext;
import javax.cache.annotation.GeneratedCacheKey;

import com.example.common.entity.SimpleProduct;

public class SimpleProductCacheKeyGenerator implements CacheKeyGenerator {

	// http://www.tomitribe.com/blog/2015/06/using-jcache-with-cdi/
	public GeneratedCacheKey generateCacheKey(
			CacheKeyInvocationContext<? extends Annotation> cacheKeyInvocationContext) {
		
		System.out.println("SimpleProductCacheKeyGenerator#generateCacheKey(cacheKeyInvocationContext) " + cacheKeyInvocationContext);
		
		final CacheInvocationParameter[] allParameters = cacheKeyInvocationContext.getAllParameters();
        for (final CacheInvocationParameter parameter : allParameters) {
            if (SimpleProduct.class.equals(parameter.getRawType())) {
                final SimpleProduct product = SimpleProduct.class.cast(parameter.getValue());
                return new DefaultSimpleProductGeneratedCacheKey(new Object[] { product.getPrice(), product.getName() });
            }
        }

        throw new IllegalArgumentException("No SimpleProduct arguments found in method signature");
	}
}
