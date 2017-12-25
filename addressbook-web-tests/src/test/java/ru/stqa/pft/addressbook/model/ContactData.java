package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String mobile;
  private String group;
  private final String id;


  public ContactData(String name1,String name2,String address,String email,String mobile,String group) {
    this.id = null;
    this.firstname = name1;
    this.lastname = name2;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
  }

  public ContactData(String id,String name1,String name2,String address,String email,String mobile,String group) {
    this.id = id;
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

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (id != null ? id.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", id='" + id + '\'' +
            '}';
  }
}
