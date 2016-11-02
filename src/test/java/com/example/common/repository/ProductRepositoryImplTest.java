package com.example.common.repository;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.common.config.Config;
import com.example.common.entity.SimpleProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
public class ProductRepositoryImplTest {

	private static Long FIRST_MAP_ID = new Long(10);
	
	@Autowired
	IProductRepository repository;
	
	@Test
	public void testInit() {
		Assert.assertTrue(repository.getProductMap().size() == 3);
	}
	
	@Test
	public void testCreateGeneratesProductIdAndMapInsertion() {
		SimpleProduct newProduct = new SimpleProduct(null, "four", 4, new Date());
		SimpleProduct persistedProduct = repository.create(newProduct);
		
		Assert.assertNotNull(persistedProduct.getId());
		Assert.assertEquals(4, repository.getProductMap().size());
	}
	
	@Test
	public void testReadOfExistingProductReturnsProduct() {
		SimpleProduct existingProduct = repository.getProductMap().get(FIRST_MAP_ID);
		Assert.assertNotNull(existingProduct);
		
		SimpleProduct readProduct = repository.read(existingProduct.getId());
		Assert.assertNotNull(readProduct);
	}

	@Test
	public void testReadOfNonExistantProductReturnNull() {
		SimpleProduct nonExistantProduct = repository.read(Long.MAX_VALUE);
		
		Assert.assertNull(nonExistantProduct);
	}
	
	@Test
	public void testUpdateOfExistingProductUpdatesProduct() {
		SimpleProduct existingProduct = repository.getProductMap().get(FIRST_MAP_ID);
		Assert.assertNotNull(existingProduct);
		
		// do NOT want id to change
		Long expectedProductId = existingProduct.getId();
		String newName = "newName";
		int newPrice = 123456;
		Date newDate = new Date();
		
		SimpleProduct updatingProduct = new SimpleProduct(expectedProductId, newName, newPrice, newDate);
		SimpleProduct updatedProducut = repository.update(updatingProduct);
		
		// check that id has NOT changed
		Assert.assertEquals(expectedProductId, updatedProducut.getId());
		
		// check that name HAS changed
		Assert.assertEquals(newName, updatedProducut.getName());
		
		// check that price HAS changed
		Assert.assertEquals(Integer.valueOf(newPrice), Integer.valueOf(updatedProducut.getPrice()));
		
		// check that date HAS changed
		Assert.assertEquals(newDate, updatedProducut.getInsertTime());
		
		// check that the map has the updated product at the same id as existingProduct
		Assert.assertEquals(updatedProducut, repository.getProductMap().get(FIRST_MAP_ID));
	}
	
	@Test
	public void testUpdateOfNonExistantProductReturnsNull() {
		SimpleProduct nonExistantProduct = new SimpleProduct(Long.MAX_VALUE, null, 0, null);
		
		Assert.assertNull(repository.update(nonExistantProduct));
	}
	
	@Test
	public void removeOfExistingProductRemovesProduct() {
		int mapSizeBeforeRemoval = repository.getProductMap().size();
		
		SimpleProduct existingProduct = repository.getProductMap().get(FIRST_MAP_ID);
		Assert.assertNotNull(existingProduct);
		
		repository.delete(existingProduct.getId());
		
		Assert.assertEquals((mapSizeBeforeRemoval - 1), repository.getProductMap().size());
	}
	
	@Test
	public void removeOfNonExistantProductDoesNothing() {
		int mapSizeBeforeRemoval = repository.getProductMap().size();
		
		SimpleProduct nonExistantProduct = new SimpleProduct(Long.MAX_VALUE, null, 0, null);
		
		repository.delete(nonExistantProduct.getId());
		
		Assert.assertEquals(mapSizeBeforeRemoval, repository.getProductMap().size());
	}
}
