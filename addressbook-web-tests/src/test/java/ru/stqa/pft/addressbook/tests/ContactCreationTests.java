package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoAddNewContact();
    app.fillContactForm(new ContactData("Michael", "Scott", "Dunder Mifflin", "02012345678", "m.scott@gmail.com", "Green Street, 14, Scranton"));
    app.submitContactCreation();
    app.returnToHomePage();
    app.logout();
  }

}
