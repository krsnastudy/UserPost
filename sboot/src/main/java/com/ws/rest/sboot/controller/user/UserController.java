package com.ws.rest.sboot.controller.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ws.rest.sboot.dao.user.UserDAOService;

@RestController
public class UserController {

	@Autowired
	private UserDAOService userDAO;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDAO.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userDAO.findOne(id);
		
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return user;
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saved = userDAO.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saved.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/users/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		User saved = userDAO.update(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saved.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
	}	
	
	@SuppressWarnings("unused")
	@PostMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		
		String status = userDAO.delete(id);
		
		return status;
		
	}	
}
