package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testDeleteUser() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().selectedContacts();
    app.getContactHelper().deleteContacts();
    app.wd.switchTo().alert().accept();
    app.getNavigationHelper().gotoHome();
  }

}
