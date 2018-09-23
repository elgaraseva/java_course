package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver driver;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private int browser;

  public ApplicationManager(int browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser == BrowserType.MOZILLA) {
      driver = new FirefoxDriver();
    } else if (browser == BrowserType.DEFAULT) {
      driver = new ChromeDriver();
    } else if (browser == BrowserType.INTERNET_EXPLORER) {
      driver = new InternetExplorerDriver();
    }

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/addressbook/addressbook/");
    groupHelper = new GroupHelper(driver);
    sessionHelper = new SessionHelper(driver);
    contactHelper = new ContactHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper.login("admin","secret");
  }

  public void stop() {
    driver.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public SessionHelper getSessionHelper() { return sessionHelper; }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

}
