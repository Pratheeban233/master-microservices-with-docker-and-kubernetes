package com.tutorial.userapi;

import com.tutorial.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/jpa")
public class UserJpaController {

	@Autowired
	private UserJpaRespository userJpaRespository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping(path = "/users")
	public List<UserJpa> retriveUserList(){
		return userJpaRespository.findAll();
	}
	@GetMapping(path = "/users/{id}")
	public EntityModel<UserJpa> findUserById(@PathVariable int id){
		UserJpa userJpa = userJpaRespository.findById(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

		EntityModel<UserJpa> userEntityModel = EntityModel.of(userJpa);
		//HATEOAS -> Hypermedia as the Engine of Application State
		WebMvcLinkBuilder usersLinks =
				linkTo(methodOn(this.getClass()).retriveUserList());
		userEntityModel.add(usersLinks.withRel("all-Users"));
		return userEntityModel;
	}

	@GetMapping(path = "/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id){
		UserJpa userJpa = userJpaRespository.findById(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
		return userJpa.getPosts();
	}

	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> addUser(@PathVariable int id, @RequestBody @Valid Post post){
		UserJpa userJpa = userJpaRespository.findById(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
		post.setUserJpa(userJpa);
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(post);
	}
}
