package be.abis.exercice.service;

import org.springframework.stereotype.Service;

import be.abis.exercice.model.Person;

@Service
public class AbisPersonService implements PersonService {

	@Override
	public Person getPersonId(int id) {
		
		return new Person ("Thierry","Bosmans");
	}

}
