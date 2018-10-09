package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewContact();
      if(! app.getContactHelper().selectOptionInGroup()) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group_name"));
        app.goTo().gotoAddNewContact();
      }
      app.getContactHelper().createContact(null, true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.get(before.size() - 1).getId());
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Michael", "Scott", "Dunder Mifflin", "019827364490", "m.scott@gmail.com", "Green Street, 14, Scranton", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
