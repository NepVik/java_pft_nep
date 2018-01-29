package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

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
    click(By.cssSelector("input[value='Update User']"));
  }
}
