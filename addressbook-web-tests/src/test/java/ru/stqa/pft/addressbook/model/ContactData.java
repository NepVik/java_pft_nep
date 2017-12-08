package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String mobile;
  private String group;

  public ContactData(String name1, String name2, String address, String email, String mobile, String group) {
    this.firstname = name1;
    this.lastname = name2;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }
}
