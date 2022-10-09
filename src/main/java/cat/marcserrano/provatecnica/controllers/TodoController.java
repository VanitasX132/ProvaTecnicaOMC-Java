package cat.marcserrano.provatecnica.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.marcserrano.provatecnica.entities.TodoEntity;
import cat.marcserrano.provatecnica.entities.UserEntity;
import cat.marcserrano.provatecnica.repositories.TodoRepository;
import cat.marcserrano.provatecnica.repositories.UserRepository;

@CrossOrigin
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"There are no TO-DOs registered.\"}");
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
	
	@PutMapping("/users/{userId}/todos/edit") 
	public ResponseEntity<Object> editTodo(@PathVariable int userId, @RequestBody TodoEntity editedTodo){
		Optional<UserEntity> found = userrepo.findById(userId);
		if (found != null) {
			editedTodo.setUser(found.get());
			Optional<TodoEntity> oldItem = todorepo.findById(editedTodo.getId());
			if (oldItem != null) {
				editedTodo.setId(oldItem.get().getId());
				todorepo.delete(oldItem.get());
				TodoEntity result = todorepo.save(editedTodo);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The To-Do made by that user has not been found.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with ID " + userId + " has not been found.");
		}
	}
	
	@DeleteMapping("/todos/{todoId}/delete")
	public ResponseEntity<Object> deleteTodo(@PathVariable int todoId) {
		ResponseEntity<Object> result;
		try {
			todorepo.deleteById(todoId);
			result = ResponseEntity.status(HttpStatus.NO_CONTENT).body("It has been deleted.");
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It has not been deleted.");
		}
		return result;
	}
}
