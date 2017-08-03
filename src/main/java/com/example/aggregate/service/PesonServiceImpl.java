package com.example.aggregate.service;

import com.example.aggregate.domain.Address;
import com.example.aggregate.domain.Email;
import com.example.aggregate.domain.Person;
import com.example.aggregate.respository.AddressRepository;
import com.example.aggregate.respository.EmailRepository;
import com.example.aggregate.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PesonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailRepository emailRepository;

    @Transactional
    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public void add(List<Person> people) {
        for (Person person : people) {
            personRepository.save(person);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Person getById(int id) {
        return getPerson(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> get() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Person person = this.getPerson(id);
        emailRepository.delete(person.getEmails());
        if (person.getAddress() != null) {
            addressRepository.delete(person.getAddress());
        }
        personRepository.delete(id);
    }

    @Transactional
    @Override
    public Person addAddress(Address address) {
        address = addressRepository.save(address);
        Person person = personRepository.findOne(address.getPerson().getId());
        person.setAddress(address);
        personRepository.save(person);

        return getPerson(address.getPerson().getId());
    }

    @Transactional
    @Override
    public Person deleteAddress(int personId, int addressId) {
        addressRepository.delete(addressId);
        Person person = personRepository.findOne(personId);
        person.setAddress(null);
        personRepository.save(person);

        return getPerson(personId);
    }

    @Transactional
    @Override
    public Person addEmail(Email email) {
        emailRepository.save(email);
        Person person = personRepository.findOne(email.getPerson().getId());
        person.getEmails().add(email);
        personRepository.save(person);

        return getPerson(email.getPerson().getId());
    }

    @Transactional
    @Override
    public Person deleteEmail(int personId, int emailId) {
        Email email = emailRepository.findOne(emailId);
        emailRepository.delete(emailId);
        Person person = personRepository.findOne(personId);
        person.getEmails().remove(email);
        personRepository.save(person);

        return getPerson(personId);
    }

    private Person getPerson(int id) {
        Person person = personRepository.findOne(id);
        // For the lazy to load
        person.getEmails().size();
        return person;
    }
}
