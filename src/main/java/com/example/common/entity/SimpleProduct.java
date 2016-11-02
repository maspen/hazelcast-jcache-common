package com.example.common.entity;

import java.io.Serializable;
import java.util.Date;

public class SimpleProduct implements Serializable {
	private static final long serialVersionUID = 4726671673473116234L;
	
	private Long id;
	private String name;
	private int price;
	private Date insertTime;
	
	public SimpleProduct() {
		this.id = null;
		this.name = null;
		this.price = -1;
		this.insertTime = null;
	}
	
	public SimpleProduct(String name, int price, Date insertTime) {
		this.id = null;
		this.name = name;
		this.price = price;
		this.insertTime = insertTime;
	}
	
	public SimpleProduct(Long id, String name, int price, Date insertTime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.insertTime = insertTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	// need method that simulates an update in the repository
	// need to maintain ID so that is not changed/updated on 'this'
	public SimpleProduct copyWithoutChangingId(SimpleProduct productToUpdate) {
		this.setInsertTime(productToUpdate.getInsertTime());
		this.setName(productToUpdate.getName());
		this.setPrice(productToUpdate.getPrice());
		
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
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
		SimpleProduct other = (SimpleProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SimpleProduct [id=" + id + ", name=" + name + ", price=" + price + ", insertTime=" + insertTime + "]";
	}
}
