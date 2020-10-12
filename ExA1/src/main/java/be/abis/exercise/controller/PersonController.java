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
	
	@PostMapping("persons/withEmailPassword")
	public Person findPerson(@RequestBody  Login login) {
		System.out.println("withEmailPassword login : " +login.toString());
		
		return  personService.findPerson(login.getEmail(), login.getPassword());
	}
	
	@PutMapping("persons/addPerson")
	public void addPerson(@RequestBody  Person person) {
		System.out.println("addPerson person : " +person.toString());
		
		try {
			personService.addPerson(person);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("persons/deletePerson/{id}")
	public void deletePerson(@PathVariable ("id") int id) {
		System.out.println("deletePerson person id : " + id);
		try {
			personService.deletePerson(id);
		} catch (PersonCanNotBeDeletedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@PutMapping("persons/changePassword/{newPswd}")
	public void changePassword(@RequestBody  Person person, @PathVariable ("newPswd") String newPswd) {
		System.out.println("changePassword person : " +person.toString() + " newPswd " + newPswd);
		
		try {
			personService.changePassword(person, newPswd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
}
