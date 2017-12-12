package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.gotoAddNew();
    app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Address1", "E-mail1", "1234567890", "[none]"));
  }

}