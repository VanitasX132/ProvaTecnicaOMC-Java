package cat.marcserrano.provatecnica.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.marcserrano.provatecnica.entities.TodoEntity;
import cat.marcserrano.provatecnica.entities.UserEntity;
import cat.marcserrano.provatecnica.repositories.TodoRepository;
import cat.marcserrano.provatecnica.repositories.UserRepository;

@RestController
public class TodoController {

	private TodoRepository todorepo;
	private UserRepository userrepo;
	
	public TodoController(TodoRepository todorepo, UserRepository userrepo) {
		super();
		this.todorepo = todorepo;
		this.userrepo = userrepo;
	}

	@GetMapping("/todos/all")
	public ResponseEntity<Object> retrieveAllTodos() {
		Optional<List<TodoEntity>> found = Optional.of(todorepo.findAll());
		if (!found.get().isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(found);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no TO-DOs registered.");
		}
	}
	
	@GetMapping("/todos/{id}") 
	public ResponseEntity<Object> retrieveTodoById(@PathVariable int id) {
		Optional<TodoEntity> found =todorepo.findById(id);
		if (!found.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(found);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The TO-DO with ID " + id + " has not been found.");
		}
	}
	
	@GetMapping("/users/{userId}/todos")
	public ResponseEntity<Object> retrieveTodoByUser(@PathVariable int userId) {
		List<TodoEntity> result = new ArrayList<TodoEntity>();
		Optional<UserEntity> found = userrepo.findById(userId);
		if (!found.isEmpty()) {
			Optional<List<TodoEntity>> allTodos = Optional.of(todorepo.findAll());
			for (TodoEntity todo : allTodos.get()) {
				if (todo.getUser().getId() == userId) {
					result.add(todo);
				}
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TO-DOs for the user with ID " + userId + " were not found.");
		}
	}
	
	@PostMapping("/users/{userId}/todos/add")
	public ResponseEntity<Object> addNewTodo(@PathVariable int userId, @RequestBody TodoEntity newTodo) {
		Optional<UserEntity> found = userrepo.findById(userId);
		if (found != null) {
			newTodo.setUser(found.get());
			TodoEntity savedTodo = todorepo.save(newTodo);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot create a new TO-DO for the user with ID " + userId + " as it cannot be found.");
		}
	}
	
	//TODO - Add Put mapping methods in TodoController
	//TODO - Add Delete mapping methods in TodoController
}
