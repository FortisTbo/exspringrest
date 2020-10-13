package be.abis.exercise.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;


@RestController
public class PersonController {
	@Autowired
	PersonService personService;
	
	@GetMapping("persons/{id}")
	public Person findPerson(@PathVariable ("id") int id) {
		return personService.findPerson(id);
	}
	
	@GetMapping("persons")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@PostMapping("persons/login")
	public Person findPerson(@RequestBody  Login login) {
		System.out.println("withEmailPassword login : " +login.toString());
		
		return  personService.findPerson(login.getEmail(), login.getPassword());
	}
	
	@PostMapping("persons")
	public void addPerson(@RequestBody  Person person) {
		System.out.println("addPerson person : " +person.toString());
		
		try {
			personService.addPerson(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("persons/{id}")
	public void deletePerson(@PathVariable ("id") int id) {
		System.out.println("deletePerson person id : " + id);
		try {
			personService.deletePerson(id);
		} catch (PersonCanNotBeDeletedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@PutMapping("persons/{id}")
	public void changePassword(@RequestBody  Login login, @PathVariable ("id") int id) {
		System.out.println("changePassword person : " +  login.toString() + " id " + id);
		
		Person person = personService.findPerson(id);
		
		try {
			personService.changePassword(person, login.getPassword());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
}
