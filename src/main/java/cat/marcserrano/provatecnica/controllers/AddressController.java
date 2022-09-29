package cat.marcserrano.provatecnica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.marcserrano.provatecnica.entities.AddressEntity;
import cat.marcserrano.provatecnica.entities.UserEntity;
import cat.marcserrano.provatecnica.repositories.AddressRepository;
import cat.marcserrano.provatecnica.repositories.UserRepository;

@RestController
public class AddressController {
	
	private AddressRepository addressrepo;
	private UserRepository userrepo;
	
	public AddressController(AddressRepository addressrepo, UserRepository userrepo) {
		super();
		this.addressrepo = addressrepo;
		this.userrepo = userrepo;
	}
	
	@GetMapping("/address/all")
	public ResponseEntity<Object> listAllAddesses() {
		Optional<List<AddressEntity>> found = Optional.of(addressrepo.findAll());
		if (!found.get().isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(found);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user registered.");
		}
	}
	
	@GetMapping("/users/{userId}/address")
	public ResponseEntity<Object> listAddressByUser(@PathVariable int userId) {
		Optional<UserEntity> found = userrepo.findById(userId);
		if (!found.isEmpty()) {
			Optional<AddressEntity> result = addressrepo.findById(found.get().getAddress().getAddress_id());
			if (!result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The address of the user with ID " + userId + " was not found.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with ID " + userId + " was not found.");
		}
	}
	
	@PostMapping("/address/add") 
	public ResponseEntity<Object> addNewAddress(@PathVariable int userId, @RequestBody AddressEntity newAddress) {
		AddressEntity savedAddress = addressrepo.save(newAddress);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
	}
	
	//TODO - Add Put mapping methods in AddressController
	//TODO - Add Delete mapping methods in AddressController
}
