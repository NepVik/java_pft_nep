package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class AddContactToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData()
            .withFirstname("1").withLastname("2").withAddress("3").withEmail("4").withMobile("5");
    if (app.db().contacts().size() == 0) {
      app.goTo().addNewPage();
      app.contact().fillNewContactForm(contact, true);
      app.contact().create();
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("1").withHeader("2").withFooter("3"));
    }
  }

  @Test
  public void TestAddContactToGroup() {
    Groups groups = app.db().groups();
    ContactData thisContact = app.contact().selectContactInHomePage(app.db().contacts(), groups.size());
    Groups contactInGroups = thisContact.getGroups();
    GroupData thisGroup = app.contact().groupSelectionFor(thisContact, app.db().groups());
    app.contact().addContactToGroup(thisGroup);
    app.goTo().homePage();

    assertTrue(contactInGroups.withAdded(thisGroup).contains(thisGroup));
  }
}

