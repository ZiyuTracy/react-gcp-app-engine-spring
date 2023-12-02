package com.example.demo;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.bind.annotation.RequestParam;

@ShellComponent
@SpringBootApplication
public class DemoApplication {
  @Autowired
  UserRepository userRepository;
  GameRepository gameRepository;

  public static void main(String[] args) {
     SpringApplication.run(DemoApplication.class, args);
  }

  @ShellMethod("Saves a user to Cloud Datastore: save-user <googleId> <handle>")
  public String saveUser(String googleId, String handle) {
     User savedUser = this.userRepository.save(new User(googleId, handle));
     return savedUser.toString();
  }

  @ShellMethod("Loads all users")
  public String findAllUsers() {
     Iterable<User> users = this.userRepository.findAll();
     return Lists.newArrayList(users).toString();
  }

  @ShellMethod("Loads books by author: find-by-author <author>")
  public String findByGoogleId(String googleId) {
     List<User> users = this.userRepository.findByGoogleId(googleId);
     return users.toString();
  }

  @ShellMethod("Update username: update-handle <userId> <newHandle>")
	public String updateByHandle(String googleId, String newHandle){
		List<User> users = this.userRepository.findByGoogleId(googleId);
		if(users.isEmpty()){
			return "User Id is not found: "+ googleId;
		}
		for(User user: users){
			user.setHandle(newHandle);
		}
		userRepository.saveAll(users);
		return "user name is updated.";
	}
  
   @ShellMethod("Delete by googleId: delete-userId <googleId>")
	public void deleteByGoogleId(String googleId){
		gameRepository.deleteByGoogleId(googleId);
	}

//   @ShellMethod("Loads books published after a given year: find-by-year-after <year>")
//   public String findByYearAfter(int year) {
//      List<User> books = this.bookRepository.findByYearGreaterThan(year);
//      return books.toString();
//   }

//   @ShellMethod("Loads books by author and year: find-by-author-year <author> <year>")
//   public String findByAuthorYear(String author, int year) {
//      List<User> books = this.bookRepository.findByAuthorAndYear(author, year);
//      return books.toString();
//   }

//   @ShellMethod("Removes all books")
//   public void removeAllBooks() {
//      this.bookRepository.deleteAll();
//   }
}
