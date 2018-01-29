package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer1() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    HttpSession session = app.newSession();

    String login = "user1517250539176";
    String newPassword = "pass";
    String email = String.format("%s@localhost.localdomain", login);

    app.response().loginToMantis(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.response().initResetPassword(login); //нужно доставать
    List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.response().resetPassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(login, newPassword));
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email){
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regax = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regax.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer1() {
    app.mail().stop();
  }

}
