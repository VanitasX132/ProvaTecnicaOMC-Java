package cat.marcserrano.provatecnica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.marcserrano.provatecnica.entities.UserEntity;
import cat.marcserrano.provatecnica.repositories.UserRepository;

@RestController
public class UserController {
	
	private UserRepository userrepo;
	
	public UserController (UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}
	
	@CrossOrigin
	@GetMapping("/users/all")
	public ResponseEntity<Object> retrieveAllUsers() {
		Optional<List<UserEntity>> found = Optional.of(userrepo.findAll());
		if (!found.get().isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(found);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user registered.");
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> retrieveUserById(@PathVariable Integer id) {
		Optional<UserEntity> found = userrepo.findById(id);
		System.out.println(found);
		if (!found.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(found);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with ID " + id + " was not found.");
		}
	}
	
	@CrossOrigin
	@PostMapping("/users/add")
	public ResponseEntity<Object> addNewUser(@RequestBody UserEntity newUser) {
		UserEntity savedTodo = userrepo.save(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
	}
}
