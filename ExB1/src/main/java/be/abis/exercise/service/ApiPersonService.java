package be.abis.exercise.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

		return null;
	}

	@Override
	public Person findPerson(int id) {
		Person p = rt.getForObject(baseUri+"/"+id, Person.class);
		return p;
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) {
		Login login = new Login();
		login.setEmail(emailAddress);
		login.setPassword(passWord);
		Person p = rt.postForObject(baseUri+"/login", login, Person.class);
		return p;
	}

	@Override
	public void addPerson(Person p) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(int id) throws PersonCanNotBeDeletedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		// TODO Auto-generated method stub

	}

}
