package com.atparinas.tutorials.ui.controller;

import javax.validation.Valid;

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

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="10") int limit) {
		
		return "Get Users was called page=" + page + " limit=" + limit; 
	}
	
	@GetMapping(path="/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {
		
		UserRest returnValue = new UserRest();
		
		returnValue.setFirstName("Andy");
		returnValue.setLastName("Parinas");
		returnValue.setEmail("atparinas@gmail.com");
		returnValue.setUserId("atparinas");
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setLastName(userDetails.getLastName());
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		
	}
	
	@PutMapping
	public String updateUser() {
		
		return "Update User was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "Delete user was called";
	}

}