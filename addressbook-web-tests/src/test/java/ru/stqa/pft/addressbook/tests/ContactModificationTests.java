package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ContactModificationTests extends TestBase {

  @Test
  public void testUpdateUser() {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.gotoAddNew();
      app.getContactHelper().createContact(new ContactData("1", "2", "3", "4", "5", "[none]"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification((before.size() - 1) + 2); //модифицируем последнюю строку
    ContactData contact = new ContactData("Name1", "Name2", "Address1", "E-mail1", "1234567890", null);
    app.getContactHelper().fillNewContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
