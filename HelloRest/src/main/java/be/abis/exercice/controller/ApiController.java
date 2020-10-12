package be.abis.exercice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import be.abis.exercice.model.Person;
import be.abis.exercice.service.PersonService;

@RestController
public class ApiController {
	@Autowired
	PersonService personService;
	
	@GetMapping("persons/{id}")
	public Person findPerson(@PathVariable ("id") int id) {
		return personService.getPersonId(id);
	}
}
