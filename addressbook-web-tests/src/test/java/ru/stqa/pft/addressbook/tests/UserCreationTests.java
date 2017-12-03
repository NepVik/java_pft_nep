package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() {
    app.gotoAddNew();
    app.fillNewUserForm(new UserData("Name1", "Name2", "Address1", "E-mail1", "1234567890"));
    app.submitNewUserCreation();
    app.returnToHomePage();
  }

}
