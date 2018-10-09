package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private String firstName;
  private String lastName;
  private String company;
  private String phone;
  private String email;
  private String address;
  private String group;

  public ContactData(String firstName, String lastName, String company, String phone, String email, String address, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public ContactData(Integer id, String firstName, String lastName, String company, String phone, String email, String address, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public int getId() { return id; }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastname() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() { return group; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
