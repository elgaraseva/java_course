package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String surname;
  private final String company;
  private final String phone;
  private final String email;
  private final String address;

  public ContactData(String firstName, String surname, String company, String phone, String email, String address) {
    this.firstName = firstName;
    this.surname = surname;
    this.company = company;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getSurname() {
    return surname;
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
}
