package com.example.aggregate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "person" )
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private List<Email> emails = new ArrayList<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(mappedBy = "person")
    public Address getAddress() {
        return address;
    }

    @JsonIgnore
    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "person", fetch= FetchType.LAZY)
    public List<Email> getEmails() {
        return emails;
    }

    @JsonIgnore
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;

        return id == person.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
          "id=" + id +
          ", firstName='" + firstName + '\'' +
          ", lastName='" + lastName + '\'' +
          ", address=" + address +
          ", emails=" + emails +
          '}';
    }
}
