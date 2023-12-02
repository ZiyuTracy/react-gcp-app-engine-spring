package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "games")
public class Game {
  @Id
  Long id;

  String googleId;

  int score;

  String date;

  public Game(String googleId, int score, String date) {
    this.googleId = googleId;
    this.score = score;
    this.date = date;
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

  public int getScore() {
  	return this.score;
  }
  
  public void setScore(int score) {
  	this.score=score;
  }

  public String getDate() {
  	return this.date;
  }
  
  public void setDate(String date) {
  	this.date=date;
  }
  


  @Override
  public String toString() {
    return "{" +
        "id:" + this.id +
        ", googleId:'" + this.googleId + '\'' +
        ", handle:" + this.score + '\'' +
         ", date:" + this.date + 
        '}';
  }
}