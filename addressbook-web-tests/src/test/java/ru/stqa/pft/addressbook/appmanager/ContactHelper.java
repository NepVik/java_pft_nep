package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitNewContactCreation() {
    click(By.name("submit"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }



  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create() {
    submitNewContactCreation();
    contactCeche = null;
    returnToHomePage();
  }

  public void fillNewContactForm(ContactData contactData,boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
    type(By.name("mobile"),contactData.getMobile());
    attach(By.name("photo"),contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void modify(ContactData contact) {
    initModifyById(contact.getId());
    fillNewContactForm(contact,false);
    submitContactModification();
    contactCeche = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    contactCeche = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCeche = null;

  public Contacts all() {
    if (contactCeche != null) {
      return new Contacts(contactCeche);
    }
    contactCeche = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String addressFirst = element.findElement(By.xpath(".//td[4]")).getText();
      contactCeche.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmales(allEmails).withAddress(addressFirst));
    }
    return new Contacts(contactCeche);
  }

  public ContactData infoFormEditFormPhones(ContactData contact) {
    initModifyById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(homePhone).withMobile(mobile).withWorkPhone(workPhone);
  }

  public ContactData infoFormEditFormEmails(ContactData contact) {
    initModifyById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFormEditFormAddresses(ContactData contact) {
    initModifyById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withAddress2(address2);
  }

  private void initModifyById(int id) {
//    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();

    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements((By.tagName("td")));
    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();


  }

  public void addContactToGroup(GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.name("add"));
  }

  public GroupData groupSelectionFor(ContactData contact,Groups groups) {
    GroupData thisGroup = new GroupData();
    Groups contactInGroups = contact.getGroups(); //содержит группы, в которых состоит контакт

    if (contactInGroups.size() == 0) {
      return groups.iterator().next();
    } else {
      for (GroupData group : groups) {
        for (GroupData contactGroup : contactInGroups) {
          if (!group.equals(contactGroup)) {
            return group;
          } else if (contactInGroups.size() > 1) {
            contactInGroups.remove(group);
            break;
          }
        }
      }
    }
    return thisGroup; //Такой исход предоствращен в selectContactInHomePage
  }

  public ContactData selectContactInHomePage(Contacts contacts, int groupsSize) { //Выбор контакта, который не состоит хотя бы в одной группе
    for (ContactData contact : contacts) {
      if (groupsSize != contact.getGroups().size()) {
        selectContactById(contact.getId());
        return contact;
      }
    }
    ContactData newContact = new ContactData()
            .withFirstname("1").withLastname("2").withAddress("3").withEmail("4").withMobile("5").withPhoto(new File("src/test/resources/stru.png"));
    wd.findElement(By.linkText("add new")).click();
    fillNewContactForm(newContact,true);
    create();
    contacts.add(newContact);
    selectContact(newContact.getFirstname(), newContact.getLastname());
    return newContact;
  }

  public void selectContact(String fName, String lName) {
    wd.findElement(By.cssSelector("input[title='Select (" + fName + " " + lName + ")']")).click();
  }

  public GroupData goToGroupTable(Groups groups) {
    GroupData thisGroup = new GroupData();
    for (GroupData group : groups) {
      if (group.getContacts().size() == 0) {
        continue;
      } else {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        thisGroup = group;
        return thisGroup;
      }
    }
    return thisGroup;
  }

  public ContactData findContactToId(int id, Contacts contacts) {
    ContactData thisContact = new ContactData();
    for (ContactData contact : contacts) {
      if (contact.getId() == id) {
        return contact;
      }
    }
    return thisContact;
  }

  public void checkGroups(Groups groups) {
    int f = 0;
    for (GroupData group : groups) {
      if (group.getContacts().size() == 0) {
        f++;
      }
    }
    if (f == groups.size()) {
      wd.findElement(By.name("selected[]")).click();
      GroupData group1 = groups.iterator().next();
      addContactToGroup(group1);
      wd.findElement(By.linkText("home")).click();
    }
  }
}

