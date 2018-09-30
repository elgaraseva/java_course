package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContact();
      if(! app.getContactHelper().selectOptionInGroup()) {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("group_name", null, null));
        app.getNavigationHelper().gotoAddNewContact();
      }
      app.getContactHelper().createContact(null, true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData ("Jim", "Halpert", "Dunder Mifflin", "019827364490", "j.halpert@dm.com", "Paper Street, 7, Scranton", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }
}
