package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.gotoAddNew();
    app.getContactHelper().fillNewContactForm(new UserData("Name1", "Name2", "Address1", "E-mail1", "1234567890"));
    app.getContactHelper().submitNewContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}