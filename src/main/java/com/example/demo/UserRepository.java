package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;


public interface UserRepository extends DatastoreRepository<User, Long> {

  List<User> findByGoogleId(String googleId);
}