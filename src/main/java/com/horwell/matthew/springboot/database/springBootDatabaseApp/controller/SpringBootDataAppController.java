package com.horwell.matthew.springboot.database.springBootDatabaseApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horwell.matthew.springboot.database.springBootDatabaseApp.exception.ResourceNotFoundException;
import com.horwell.matthew.springboot.database.springBootDatabaseApp.model.SpringBootDataModel;
import com.horwell.matthew.springboot.database.springBootDatabaseApp.repository.SpringBootRepository;

@RestController
@RequestMapping("/api")
public class SpringBootDataAppController {
	
	@Autowired
	SpringBootRepository repository;
	
	@PostMapping("/person")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel SDM) {
		return repository.save(SDM);
	}
	
	@GetMapping("/person/{id}")
	public SpringBootDataModel getPersonbyID(@PathVariable(value = "id")Long personID) {
		return repository.findById(personID)
				.orElseThrow(()-> new ResourceNotFoundException("SpringBootDatabase","id",personID));
	}
	
	@GetMapping("/person")
	public List<SpringBootDataModel> getAllPeople(){
		return repository.findAll();
	}
	
	@PutMapping("/person/{id}")
	public SpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID,
			@Valid @RequestBody SpringBootDataModel personDetails) {
		SpringBootDataModel SDM = repository.findById(personID)
				.orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		
		SDM.setName(personDetails.getName());
		SDM.setAddress(personDetails.getAddress());
		SDM.setAge(personDetails.getAge());
		
		SpringBootDataModel updateData = repository.save(SDM);
		return updateData;
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personID){
		SpringBootDataModel SDM = repository.findById(personID)
				.orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		repository.delete(SDM);
		return ResponseEntity.ok().build();
	}

	
	

}
