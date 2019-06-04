package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Items;
import com.example.demo.repository.ItemsRepositoryInterface;

@Service
public class ItemsService {
  @Autowired
  ItemsRepositoryInterface itr;
  
  //Single Item get
  public Optional<Items> get(int itemId){
	  return itr.findById(itemId);
  }
  //Get all items
  public List<Items> getAll(){
	  return (List<Items>) itr.findAll();
  }
  //Update/create item
  public Items update(Items item) {
	  return itr.save(item);
  }
  //Delete item
  public void delete(Items item) {
	  itr.delete(item);
  }
  //Delete all items
  public void deleteAll() {
	  itr.deleteAll();
  }
}
