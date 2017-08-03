package com.example.aggregate.controller;

import com.example.aggregate.domain.Address;
import com.example.aggregate.domain.Email;
import com.example.aggregate.domain.Person;
import com.example.aggregate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * This endpoint is called when updating.
     */
    @PutMapping("/person/{id}")
    public String updatePerson(@PathVariable("id") Integer id,
                               Person person,
                               Model model) {
        personService.update(person);
        model.addAttribute("person", personService.getById(id));
        return "ok";
    }

    /**
     * Add an address
     */
    @PostMapping("/person/{id}/address")
    public String addAddress(@PathVariable("id") Integer id,
                             Address address, Model model) {
        address.setPersonId(id);
        personService.addAddress(address);
        model.addAttribute("person", personService.getById(id));
        return "ok";
    }

    /**
     * Deletes the address from the person. The url has both ids.
     */
    @GetMapping("/person/{id}/address/{addressId}")
    public String deleteAddress(@PathVariable("id") Integer id,
                                @PathVariable("addressId") Integer addressId,
                                Address address, Model model) {
        personService.deleteAddress(id, addressId);
        model.addAttribute("person", personService.getById(id));
        return "ok";
    }

    /**
     * Add an email
     */
    @PostMapping("/person/{id}/email")
    public String addEmail(@PathVariable("id") Integer id,
                           @RequestParam(value="email", required=true) String emailAddress,
                           Model model) {
        Email email = new Email();
        email.setEmail(emailAddress);
        email.setPersonId(id);
        personService.addEmail(email);
        return "ok";
    }

    /**
     * Deletes the email from the person. The url has both ids.
     */
    @GetMapping("/person/{id}/email/{emailId}")
    public String deleteEmail(@PathVariable("id") Integer id,
                              @PathVariable("emailId") Integer emailId,
                              Address address, Model model) {
        personService.deleteEmail(id, emailId);
        model.addAttribute("person", personService.getById(id));
        return "ok";
    }


    /**
     * Get a list of all the people. The person does not have references to
     * its associated address or emails
     */
    @GetMapping("/persons")
    public List<Person> getAll(Model model) {
        return personService.get();
    }

    /**
     * Add person
     */
    @PostMapping("/person")
    public String addPerson(@RequestParam(value="firstName", required=true) String firstName, @RequestParam(value="lastName", required=true) String lastName) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setFirstName(lastName);
        personService.add(person);

        return "ok";
    }

    /*
     * Calls the person service to get the person for the given id.
     */
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") Integer id) {
        return personService.getById(id);
    }
}
