package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
    click(By.linkText("groups"));
  }

  public void addNewPage() {
    wd.findElement(By.linkText("add new")).click();
  }
  public void homePage(){
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      wd.findElement(By.linkText("home")).click();
    }
  }
}
