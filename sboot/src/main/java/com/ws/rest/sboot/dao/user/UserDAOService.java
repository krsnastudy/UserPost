package com.ws.rest.sboot.dao.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ws.rest.sboot.controller.user.User;
import com.ws.rest.sboot.controller.user.UserNotFoundException;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount=5;
	
	static {
		
		users.add(new User(1, "User1", new Date()));
		users.add(new User(2, "User2", new Date()));
		users.add(new User(3, "User3", new Date()));
		users.add(new User(4, "User4", new Date()));
		users.add(new User(5, "User5", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User update(User modUser){
		
		if(modUser.getId()==null) {
			throw new UserNotFoundException("id-"+modUser.getId());
		}
		
		User user = users.get(modUser.getId()-1);
		user.setName(modUser.getName());
		
		return user;
	}
	
	public User findOne(int id) {
		
		for(User user : users) {
		 if(user.getId()==id) {
			return user;
		 }
		}
		
		return null;
	}
	
	public String delete(int id) {
		
		for(User user : users) {
		 if(user.getId()==(id-1)) {
			 users.remove(user.getId());
			return "Deleted User "+id;
		 }
		}
		
		return "No User Found to delete";
	}
}