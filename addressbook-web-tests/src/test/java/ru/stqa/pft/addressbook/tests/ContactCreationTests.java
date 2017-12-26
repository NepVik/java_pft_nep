package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().addNewPage();
    ContactData contact = new ContactData()
            .withFirstname("Name2").withLastname("Name3").withAddress("Address1").withEmail("E-mail1").withMobile("1234567890").withGroup("[none]");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}