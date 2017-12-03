package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testUpdateUser() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new UserData("Name1", "Name2", "Address1", "E-mail1", "1234567890"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }


}
