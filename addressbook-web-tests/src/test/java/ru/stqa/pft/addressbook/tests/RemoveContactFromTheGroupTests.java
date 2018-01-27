package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertFalse;


public class RemoveContactFromTheGroupTests extends TestBase{

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
  public void TestRemoveContactFromGroup() {
    Groups notCheckedGroups = app.db().groups();
    app.contact().checkGroups(notCheckedGroups);

    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    GroupData thisGroup = app.contact().goToGroupTable(groups);

    app.wd.findElement(By.name("selected[]")).click();
    int id = Integer.parseInt(app.wd.findElement(By.name("selected[]")).getAttribute("id"));
    ContactData thisContact = app.contact().findContactToId(id, contacts);
    app.wd.findElement(By.name("remove")).click();
    app.wd.findElement(By.linkText("group page \"" + thisGroup.getName() + "\"")).click();

    assertFalse(thisGroup.getContacts().without(thisContact).contains(thisContact));
  }
}
