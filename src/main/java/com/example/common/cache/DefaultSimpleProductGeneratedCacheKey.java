package com.example.common.cache;

import java.util.Date;

import javax.cache.annotation.GeneratedCacheKey;

public class DefaultSimpleProductGeneratedCacheKey implements GeneratedCacheKey {
	private static final long serialVersionUID = 6222117551872696799L;

	// winging it ..
	// these are the 2 parameters that are set when a new SimpleProduct is created;
	// passed in as object[]
	private Integer price;
	private String name;
	private Date now;
	
	public DefaultSimpleProductGeneratedCacheKey(Object[] objects) {
		// check to see that objects has 2 elements
		if(objects.length != 2) {
			throw new IllegalArgumentException("Object[] objects HAS to be of size 2");
		}
		this.price = (Integer) objects[0];
		this.name = (String) objects[1];
		now = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((now == null) ? 0 : now.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultSimpleProductGeneratedCacheKey other = (DefaultSimpleProductGeneratedCacheKey) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (now == null) {
			if (other.now != null)
				return false;
		} else if (!now.equals(other.now))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefaultSimpleProductGeneratedCacheKey [price=" + price + ", name=" + name + ", now=" + now + "]";
	}
}
