package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().isThereAContact()) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData()
              .withFirstname("1").withLastname("2").withAddress("3").withEmail("4").withMobile("5").withGroup("[none]"));
    }
  }

  @Test
  public void testDeleteUser() {
    List<ContactData> before = app.contact().list();
    app.contact().select(before.size() - 1);
    app.contact().delete();
    app.wd.switchTo().alert().accept();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

//       if (! app.getGroupHelper().isThereAGroup()) {
//       app.getNavigationHelper().gotoGroupPage();
//       app.getGroupHelper().createGroup(new GroupData("def",null,null));