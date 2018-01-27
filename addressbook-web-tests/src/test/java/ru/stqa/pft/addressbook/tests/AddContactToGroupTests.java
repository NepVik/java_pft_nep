package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroupTests extends TestBase{

  private SessionFactory sessionFactory;



  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData()
            .withFirstname("1").withLastname("2").withAddress("3").withEmail("4").withMobile("5");
 //   app.goTo().homePage();
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
  public void TestAddContactToGroup() { //Если входит в выбранную группу, выбрать другую; если входит во все, создать новую группу и уже в нее добавить контакт (БД)
    Groups groups = app.db().groups();
    ContactData thisContact = app.contact().selectContactInHomePage(app.db().contacts(), groups.size());
    Groups contactInGroups = thisContact.getGroups();
    GroupData thisGroup = app.contact().groupSelectionFor(thisContact, app.db().groups());
    app.contact().addContactToGroup(thisGroup);
    contactInGroups.add(thisGroup);
    Assert.assertTrue(contactInGroups.contains(thisGroup));
    app.goTo().homePage();
  }
}

