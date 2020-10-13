package be.abis.exercise.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;

@Service
public class ApiPersonService implements PersonService {
	
	@Autowired
	private RestTemplate rt;
	private String baseUri = "http://localhost:8085/exercise/api/persons";

	@Override
	public ArrayList<Person> getAllPersons() {
		System.out.println("ExB1 getAllPersons");
		ResponseEntity<ArrayList<Person>> persons = rt.exchange(baseUri,  HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Person>>(){});
		ArrayList<Person> personList = persons.getBody();
		return personList;
	}

	@Override
	public Person findPerson(int id) {
		System.out.println("ExB1 findPerson");
		Person p = rt.getForObject(baseUri+"/"+id, Person.class);
		return p;
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) {
		System.out.println("ExB1 findPerson with Login");
		Login login = new Login();
		login.setEmail(emailAddress);
		login.setPassword(passWord);
		Person p = rt.postForObject(baseUri+"/login", login, Person.class);
		return p;
	}

	@Override
	public void addPerson(Person p) throws IOException {
		System.out.println("ExB1 addPerson");
		rt.postForObject(baseUri, p,  Void.class);
	}

	@Override
	public void deletePerson(int id) throws PersonCanNotBeDeletedException {
		System.out.println("ExB1 deletePerson");
		rt.delete(baseUri+"/"+ id);

	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		System.out.println("ExB1 changePassword");
		Login login = new Login();
		login.setPassword(newPswd);
		rt.put(baseUri+"/"+p.getPersonId(), login);
	}

}
