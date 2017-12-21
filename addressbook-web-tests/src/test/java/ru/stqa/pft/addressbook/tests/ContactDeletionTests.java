package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testDeleteUser() {
    app.getNavigationHelper().gotoHome();
      if (! app.getContactHelper().isThereAContact()) {
        app.gotoAddNew();
        app.getContactHelper().createContact(new ContactData("1","2","3","4","5","[none]"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectedContacts();
    app.getContactHelper().deleteContacts();
    app.wd.switchTo().alert().accept();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}

//       if (! app.getGroupHelper().isThereAGroup()) {
//       app.getNavigationHelper().gotoGroupPage();
//       app.getGroupHelper().createGroup(new GroupData("def",null,null));