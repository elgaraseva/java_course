package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContact() {
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

  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, true);
    submitContact();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    returnHomePage();
  }

  public void returnHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public boolean findGroup() {
    if (driver.findElement(By.name("new_group")).getText().contains("group_name")) {
      Select dropDown = new Select(driver.findElement(By.name("new_group")));
      dropDown.selectByVisibleText("group_name");
      return true;
    } else {
      return false;
    }
  }

  public List<ContactData> list(){
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    int trStartIndex = 2;
    for (WebElement element : elements){
      String firstName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" + "/td[3]")).getText();
      String lastName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" +"/td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
      trStartIndex++;
    }
    return contacts;
  }

  public Contacts all(){
    Contacts contacts = new Contacts();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    int trStartIndex = 2;
    for (WebElement element : elements){
      String firstName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" + "/td[3]")).getText();
      String lastName = element.findElement(By.xpath("//tr[" + trStartIndex + "]" +"/td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + trStartIndex + "]/td[6]")).getText();
      String allEmails = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + trStartIndex + "]/td[5]")).getText();
      String allAddresses = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + trStartIndex + "]/td[4]")).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllPhones(allPhones)
              .withAllEmails(allEmails).withAllAddresses(allAddresses));
      trStartIndex++;
    }
    return contacts;
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String email = driver.findElement(By.name("email")).getAttribute("value");
    String secondEmail = driver.findElement(By.name("email2")).getAttribute("value");
    String thirdEmail = driver.findElement(By.name("email3")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getAttribute("value");
    String secondAddress = driver.findElement(By.name("address2")).getAttribute("value");
    driver.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home)
            .withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withSecondEmail(secondEmail)
            .withThirdEmail(thirdEmail).withAddress(address).withSecondAddress(secondAddress);
  }

  private void initContactModificationById(int id){
    WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

}
