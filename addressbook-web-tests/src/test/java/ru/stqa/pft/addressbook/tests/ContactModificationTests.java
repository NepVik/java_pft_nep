package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {

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
  public void testUpdateUser() {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Name12").withLastname("Name23").withAddress("Address14").withEmail("E-mail1").withMobile("1234567890");
    app.contact().modify(before,contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
