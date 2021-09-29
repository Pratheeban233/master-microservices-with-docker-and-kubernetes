package com.tutorial.userapi;

import com.tutorial.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
	private static List<User> userList = new ArrayList<>();
	private static int userCount = 3;
	static {
		userList.add(new User(1,"Pratheeban", LocalDate.of(1995,4,19)));
		userList.add(new User(2,"Abiya karolin", LocalDate.of(1995,9,14)));
		userList.add(new User(3,"Dhiya", LocalDate.of(2021,7,15)));
	}
	public List<User> retrieveAllUsers(){
		return userList;
	}
	public User findByid(int id){
		return userList.stream().filter(user -> user.getId()==id).findAny().orElseThrow(() -> new UserNotFoundException(String.format("User not found for the id=%d",id)));
	}

	public User addUser (User user) {
		if ( user.getId() == null || user.getId() == 0 ) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
}
