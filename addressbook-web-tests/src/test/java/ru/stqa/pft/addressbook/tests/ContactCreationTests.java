package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewContact();
    if(! app.getContactHelper().selectOptionInGroup()) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData("group_name", null, null));
      app.getNavigationHelper().gotoAddNewContact();
    }
    ContactData contact = new ContactData("Jim", "Halpert", "Dunder Mifflin", "02012345678", "j.halpert@dm.com", "Paper Street, 7, Scranton", "group_name");
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
