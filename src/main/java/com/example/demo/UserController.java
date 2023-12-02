package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
  private final UserRepository userRepository;
  private final GameRepository gameRepository;

  public UserController(UserRepository userRepository, GameRepository gameRepository) {
    this.userRepository = userRepository;
    this.gameRepository = gameRepository;
  }

  @PostMapping("/saveUser")
  @CrossOrigin(origins = "*")
  public String saveUser(@RequestBody User user) {
    if (user == null) {
      return "The user is invalid";
    }
    this.userRepository.save(user);
    return "success";
  }

  @PostMapping("/saveGame")
  @CrossOrigin(origins = "*")
  public String saveGame(@RequestBody Game game) {
    if (game == null) {
      return "The game is invalid";
    }
    this.gameRepository.save(game);
    return "success";
  }
  
  
  @GetMapping("/findAllUsers")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public List<User> findAllUsers() {
  	Iterable<User> users = this.userRepository.findAll();
    List<User> userList = new ArrayList<>();
    users.forEach(userList::add);
    return userList;
  }
  
  @GetMapping("/findAllGames")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public List<Game> findAllGames() {
  	Iterable<Game> games = this.gameRepository.findAll();
    List<Game> gameList = new ArrayList<>();
    games.forEach(gameList::add);
    return gameList;
  }

  @GetMapping("/findByGoogleId")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public List<User> findByGoogleId(@RequestParam String googleId) {
  	Iterable<User> users = this.userRepository.findByGoogleId(googleId);
    List<User> userList = new ArrayList<>();
    users.forEach(userList::add);
    return userList;
  }

  @GetMapping("/findGameByGoogleId")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public List<Game> findGameByGoogleId(@RequestParam String googleId) {
  	Iterable<Game> games = this.gameRepository.findGameByGoogleId(googleId);
    List<Game> gameList = new ArrayList<>();
    games.forEach(gameList::add);
    return gameList;
  }

  @PutMapping("/updateHandle")
  @CrossOrigin(origins = "*")
  public String updateByHandle(@RequestParam String googleId, @RequestParam String newHandle){
        List<User> users = userRepository.findByGoogleId(googleId);
        if (users.isEmpty()){
            return "User Id is not found: "+ googleId;
        }
        for(User user:users){
            user.setHandle(newHandle);
        }
        userRepository.saveAll(users);
        return "user name is updated.";
  }
  
  @DeleteMapping("/deleteByGoogleId")
  @CrossOrigin(origins = "*")
  public void deleteByGoogleIde(@RequestParam String googleId) {
      gameRepository.deleteByGoogleId(googleId);
  }

}
