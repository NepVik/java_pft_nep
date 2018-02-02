package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UserData;

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
    UserData infoUser = users.iterator().next();
    String login = infoUser.getUsername();
    String email = infoUser.getEmail();
    String newPassword = "qwerty";

    app.response().loginToMantis(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.response().initResetPassword(login);
    List<MailMessage> mailMessages = app.mail().waitForMail(1,10000);
    String confirmationLink = app.response().findConfirmationLink(mailMessages, email);
    app.response().resetPassword(confirmationLink, newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(login, newPassword));
    Assert.assertTrue(session.isLoggedInAs(login));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
