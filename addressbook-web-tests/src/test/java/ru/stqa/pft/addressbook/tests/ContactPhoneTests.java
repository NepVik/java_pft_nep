package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData()
              .withFirstname("1").withLastname("2").withHomePhone("11111").withMobile("22222").withWorkPhone("33333").withGroup("[none]"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFormEditForm.getHomePhone())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFormEditForm.getMobile())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getWorkPhone())));
  }
  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
