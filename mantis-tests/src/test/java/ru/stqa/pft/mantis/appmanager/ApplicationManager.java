package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public Properties properties;
  WebDriver driver;

  private String browser;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.GOOGLECHROME)) {
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IEXPLORE)) {
      driver = new InternetExplorerDriver();
    }

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    driver.quit();
  }

}
