package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Items;
import com.example.demo.service.ItemsService;

@Controller
@ResponseBody

@RequestMapping("/api")
public class ItemsController {
	@Autowired
	ItemsService its;
	
	//Get All items
	@GetMapping(value= {"/items"})
	@Cacheable(value="allItems",unless="#result.size()==0")
	public List<Items> getAll(){
		List<Items> items = its.getAll();
		System.out.println("Getall: items retrieved "+items.size());
		return items;
	}
	//Get an item
	@GetMapping("/items/{id}")
	@Cacheable(value="items",key="#id",condition="#id > 1",unless="#result.itemPrice>150")
	public Items get(@PathVariable("id") Integer id) {
		System.out.println("Getting item id "+id);
		Optional<Items> i = its.get(id);
		return i.get();
	}
	//Insert an item
	@PostMapping("/items")
	@Caching(
	  put= {@CachePut(value="items",key="#result.itemId")},
	  evict= {@CacheEvict(value="allItems",allEntries=true)}
    )
	public Items add(@RequestBody Items item) {
		System.out.println("Inserting item");
		return its.update(item);
	}
	//Update an item
	@PutMapping("/items")
	@Caching(
			  put= {@CachePut(value="items",key="#result.itemId")},
			  evict= {@CacheEvict(value="allItems",allEntries=true)}
		    )
	public Items edit(@RequestBody Items item) {
		System.out.println("Updating item with id "+item.getItemId());
		return its.update(item);
	}
	//Delete an item
	@DeleteMapping("/items")
	@Caching(
			evict= {
					@CacheEvict(value="items",key="#id"),
					@CacheEvict(value="allItems",allEntries=true)
			})
	public String delete(@RequestParam(value="id") Integer id) {
		System.out.println("Deleting item with id "+id);
		Optional<Items> i = its.get(id);
		its.delete(i.get());
		return "Item id "+id+" deleted successfully";
	}
	//Delete all items
	@DeleteMapping("/deleteAll")
	@Caching(
			evict= {
					@CacheEvict(value="items",allEntries=true),
					@CacheEvict(value="allItems",allEntries=true)
			})
	public String deleteAll() {
		System.out.println("deleting all items");
		its.deleteAll();
		return "All items deleted successfully";
	}
	//Clear cache
	@GetMapping("/clearCache")
	@Caching(
			evict= {
					@CacheEvict(value="items",allEntries=true),
					@CacheEvict(value="allItems",allEntries=true)
			})
	public String clearCache() {
		return "Cleared cache successfully";
	}
	

}
