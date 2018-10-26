package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws FileNotFoundException {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      if(! app.db().groups().contains(app.properties.getProperty("baseGroupe"))) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(app.properties.getProperty("baseGroupe")));
        app.goTo().contactPage();
      }
      app.contact().create(null, true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletion() throws Exception {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }

}
