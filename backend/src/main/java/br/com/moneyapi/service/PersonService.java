package br.com.moneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moneyapi.model.Person;
import br.com.moneyapi.repository.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person getOne(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
