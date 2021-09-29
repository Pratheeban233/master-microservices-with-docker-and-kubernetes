package com.tutorial.userapi;

import com.tutorial.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private MessageSource messageSource;

	// internalization or i18n
	@GetMapping(path = "/internalized-message")
	public String getMessageInternalized(
//			@RequestHeader(name = "Accept-Language",required = false)Locale locale
	){
//		return messageSource.getMessage("welcome.message",null,locale);
		return messageSource.getMessage("welcome.message",null,
				"Default Message", LocaleContextHolder.getLocale());
	}

	@GetMapping(path = "/users")
	public List<User> retriveUserList(){
		return userDaoService.retrieveAllUsers();
	}
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> findUserById(@PathVariable int id){
		User user = userDaoService.findByid(id);

		EntityModel<User> userEntityModel = EntityModel.of(user);
		//HATEOAS -> Hypermedia as the Engine of Application State
		WebMvcLinkBuilder usersLinks =
				linkTo(methodOn(this.getClass()).retriveUserList());
		userEntityModel.add(usersLinks.withRel("all-Users"));
		return userEntityModel;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> addUser(@RequestBody @Valid User user){
		User savedUser = userDaoService.addUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).body(savedUser);
	}
}
