package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "users")
public class User {
  @Id
  Long id;

  String googleId;

  String handle;


  public User(String googleId, String handle) {
    this.googleId = googleId;
    this.handle = handle;
  }

  public long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
  	this.id=id;
  }
  
  public String getGoogleId() {
  	return this.googleId;
  }
  
  public void setGoogleId(String googleId) {
  	this.googleId=googleId;
  }
   public String getHandle() {
  	return this.handle;
  }
  
  public void setHandle(String handle) {
  	this.handle=handle;
  }
  


  @Override
  public String toString() {
    return "{" +
        "id:" + this.id +
        ", googleId:'" + this.googleId + '\'' +
        ", handle:" + this.handle +
        '}';
  }
}