package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.ContactDataGenerator;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMoveToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() throws FileNotFoundException {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      if(app.db().groups().size() == 0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(app.properties.getProperty("baseGroupe")));
        app.goTo().contactPage();
      }
      app.contact().create(new ContactDataGenerator().generateContacts(1).get(0), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactMoveToGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    GroupData moveGroup = allGroups.iterator().next();
      if (allGroups.equals(contact.getGroups())) {
        app.goTo().homePage();
        app.contact().removeFromGroup(contact, moveGroup);
      }
    allGroups.removeAll(contact.getGroups());
    app.goTo().homePage();
    app.contact().addToGroup(contact, moveGroup);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), hasItem(moveGroup));
  }

}
