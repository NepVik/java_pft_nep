package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {



    Users users = app.db().users();
    String login = app.response().usernameById(21, users);
    String email = String.format("%s@localhost.localdomain", login);
    String newPassword = "1234";

    app.response().loginToMantis(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.response().initResetPassword(login); //нужно доставать
    List<MailMessage> mailMessages = app.mail().waitForMail(1,10000);
    String confirmationLink = app.response().findConfirmationLink(mailMessages, email);
    app.response().resetPassword(confirmationLink, newPassword);
    app.response().logoutWithoutMantis();
    HttpSession session = app.newSession();
    assertTrue(session.login(login, newPassword));
    Assert.assertTrue(session.isLoggedInAs(login));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
