package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
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
    app.getContactHelper().deleteSelectedContact();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
