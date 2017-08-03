package com.example.aggregate.domain;

public class Address {

    private int id;
    private String street;
    private String city;
    private String zip;
    private String state;
    private int personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Address address = (Address) o;

        if (personId != address.personId)
            return false;
        if (street != null ? !street.equals(address.street) : address.street != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null)
            return false;
        if (zip != null ? !zip.equals(address.zip) : address.zip != null)
            return false;
        return state != null ? state.equals(address.state) : address.state == null;

    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + personId;
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
          "id=" + id +
          ", street='" + street + '\'' +
          ", city='" + city + '\'' +
          ", zip='" + zip + '\'' +
          ", state='" + state + '\'' +
          ", personId=" + personId +
          '}';
    }
}
