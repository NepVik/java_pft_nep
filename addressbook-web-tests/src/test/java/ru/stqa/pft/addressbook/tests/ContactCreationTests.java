package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.gotoAddNew();
    ContactData contact = new ContactData("Name1", "Name2", "Address1", "E-mail1", "1234567890", "[none]");
    app.getContactHelper().createContact(contact);
  //  app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }

}