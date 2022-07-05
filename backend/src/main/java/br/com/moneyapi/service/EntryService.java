package br.com.moneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.moneyapi.exceptions.PersonDoesNotExistOrIsInactiveException;
import br.com.moneyapi.model.Entry;
import br.com.moneyapi.model.Person;
import br.com.moneyapi.repository.EntryRepository;
import br.com.moneyapi.repository.PersonRepository;
import br.com.moneyapi.repository.filter.EntryFilter;

@Service
public class EntryService {
    
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private PersonRepository personReRepository;

    public List<Entry> filter(EntryFilter entryFilter) { return entryRepository.filter(entryFilter); }

    public Entry getOne(Long id) { return findById(id); }

    public Entry save(Entry entry) { 
        Optional<Person> person = personReRepository.findById(entry.getPerson().getId());
        if (!person.isPresent() || person.get().isInactive()) {
            throw new PersonDoesNotExistOrIsInactiveException();
        }
        return entryRepository.save(entry); 
    }

    public void delete(Long id) { entryRepository.deleteById(id); }

    private Entry findById(Long id) {
        Optional<Entry> entry = entryRepository.findById(id);
        return entry.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
