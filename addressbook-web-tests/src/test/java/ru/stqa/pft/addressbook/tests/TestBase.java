package ru.stqa.pft.addressbook.tests;

import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m,Object[] p) {
    logger.info("Start test " + m.getName() + "with parametrs " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public  void logTestStop(Method m) {
    logger.info("Stop test test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts));
    }
  }

  boolean isIssueOpen(int issueId) throws IOException {

    app.ra().authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
    String state = new JsonParser().parse(json).getAsJsonObject().get("issues")
            .getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    if (state.equals("Closed")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
