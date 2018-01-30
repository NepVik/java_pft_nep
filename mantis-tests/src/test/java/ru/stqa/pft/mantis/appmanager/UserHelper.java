package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void loginToMantis(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void initResetPassword(String username) {
    wd.findElement(By.xpath("//table[2]/tbody/tr/td[1]/a[7]")).click();
    wd.findElement(By.xpath("//div[2]/p/span[1]/a")).click();
    wd.findElement(By.linkText(username)).click();
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void resetPassword(String confirmationLink,String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public String findConfirmationLink(List<MailMessage> mailMessages,String email){
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void logoutWithoutMantis() {
    click(By.cssSelector("a[href='/mantisbt-1.2.19/logout_page.php'"));
  }
}
