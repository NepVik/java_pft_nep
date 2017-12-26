package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = new ContactData()
            .withFirstname("Name2").withLastname("Name3").withAddress("Address1").withEmail("E-mail1").withMobile("1234567890").withGroup("[none]");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}