package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test(enabled = false)
  public void testDeleteUser() {
    app.getNavigationHelper().gotoHome();
      if (! app.getContactHelper().isThereAContact()) {
        app.gotoAddNew();
        app.getContactHelper().createContact(new ContactData("1","2","3","4","5","[none]"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectedContacts(before.size() - 1);
    app.getContactHelper().deleteContacts();
    app.wd.switchTo().alert().accept();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

//       if (! app.getGroupHelper().isThereAGroup()) {
//       app.getNavigationHelper().gotoGroupPage();
//       app.getGroupHelper().createGroup(new GroupData("def",null,null));