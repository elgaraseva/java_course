package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
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
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}
