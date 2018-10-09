package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("mobile"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getAddress());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public boolean acceptNextAlert = true;

  public void selectContact(int index) {
    driver.findElements(By.xpath("//td/input")).get(index).click();
    acceptNextAlert = true;
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void initContactModification(int id) { 
    click(By.xpath("//td/a[contains(@href,'edit.php?id=" + id + "')]"));
  }

  public void submitContactModification() {
  click(By.xpath("//input[@name='update']"));
  }

  public void createContact(ContactData contact, boolean b) {
    fillContactForm(new ContactData("Jim", "Halpert", "Dunder Mifflin", "02012345678", "j.halpert@dm.com", "Paper Street, 7, Scranton", "group_name"), true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public boolean selectOptionInGroup() {
    if (driver.findElement(By.name("new_group")).getText().contains("group_name")) {
      Select drodDown = new Select(driver.findElement(By.name("new_group")));
      drodDown.selectByVisibleText("group_name");
      return true;
    } else {
      return false;
    }
  }

  public List<ContactData> getContactList(){
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    int trStartIndex = 2;
    for (WebElement element : elements){
      String firstName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" + "/td[3]")).getText();
      String lastName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" +"/td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null);
      contacts.add(contact);
      trStartIndex++;
    }
    return contacts;
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

}
