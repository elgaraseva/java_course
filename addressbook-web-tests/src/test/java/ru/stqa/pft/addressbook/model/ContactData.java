package ru.stqa.pft.addressbook.model;

public class ContactData {
  private String firstName;
  private String surname;
  private String company;
  private String phone;
  private String email;
  private String address;
  private String group;

  public ContactData(String firstName, String surname, String company, String phone, String email, String address, String group) {
    this.firstName = firstName;
    this.surname = surname;
    this.company = company;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.group = group;
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

  public String getGroup() { return group; }
}
