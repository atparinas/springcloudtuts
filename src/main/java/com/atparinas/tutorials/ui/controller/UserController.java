package com.atparinas.tutorials.ui.controller;

import javax.validation.Valid;

import com.atparinas.tutorials.exceptions.UserServiceException;
import com.atparinas.tutorials.ui.model.request.UpdateUserDetailsRequestModel;
import com.atparinas.tutorials.userservice.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atparinas.tutorials.ui.model.request.UserDetailsRequestModel;
import com.atparinas.tutorials.ui.model.response.UserRest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users;

	@Autowired
	UserServiceInterface userService;


	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="10") int limit) {
		
		return "Get Users was called page=" + page + " limit=" + limit; 
	}
	
	@GetMapping(path="/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {

		if(true) throw new UserServiceException("A User Service Exception is Thrown");

		if(users.containsKey(userId)){
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		

		UserRest user = userService.createUser(userDetails);

		if(users == null) users = new HashMap<>();

		users.put(user.getUserId(), user);


		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@PutMapping(path="/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails ) {


		UserRest storedUser = users.get(userId);

		storedUser.setFirstName(userDetails.getFirstName());
		storedUser.setLastName(userDetails.getLastName());

		users.put(userId, storedUser);
		
		return new ResponseEntity<>(storedUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity deleteUser(@PathVariable String userId) {

		if(users.containsKey(userId)){
			users.remove(userId);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}

	}

}
