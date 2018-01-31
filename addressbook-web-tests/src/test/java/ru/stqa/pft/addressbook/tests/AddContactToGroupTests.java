package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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
    Contacts contacts = app.db().contacts();
    if (!app.contact().checkAllGroupsIsNotFull(groups, contacts)) { //Проверка, есть ли не переполненные группы, если нет, создает новую группу
      groups = app.db().groups();
    }

    ContactData thisContact = app.contact().selectContactInHomePage(contacts, groups.size());
    GroupData thisGroup = app.contact().groupSelectionFor(thisContact, app.db().groups());
    app.contact().addSelectedContactToGroup(thisGroup);
    app.wd.findElement(By.linkText("group page \"" + thisGroup.getName() + "\"")).click();
    assertTrue(thisContact.getGroups().withAdded(thisGroup).contains(thisGroup));
  }
}

