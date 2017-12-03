package ru.stqa.pft.addressbook;

public class UserData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String mobile;

  public UserData(String firstname, String lastname, String address, String email, String mobile) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
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
}
