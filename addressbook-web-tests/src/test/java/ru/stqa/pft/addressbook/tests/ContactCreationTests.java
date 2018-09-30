package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    if(! app.getContactHelper().selectOptionInGroup()) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("group_name", null, null));
      app.getNavigationHelper().gotoAddNewContact();
    }
    app.getContactHelper().createContact(new ContactData("Michael", "Scott", "Dunder Mifflin", "02012345678", "m.scott@gmail.com", "Green Street, 14, Scranton", "group_name"), true);
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}
