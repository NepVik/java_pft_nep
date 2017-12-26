package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testUserCreation() {

    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    ContactData contact = new ContactData()
            .withFirstname("Name2").withLastname("Name3").withAddress("Address1").withEmail("E-mail1").withMobile("1234567890").withGroup("[none]");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() + 1));
    assertThat(after,equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}