package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteGroupTests extends TestBase {

  @Test
  public void testDeleteGroup() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.gotoGroupPage();
  }

}
