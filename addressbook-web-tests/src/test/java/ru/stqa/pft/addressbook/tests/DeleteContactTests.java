package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

  @Test
  public void testDeleteContact() throws Exception {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}
