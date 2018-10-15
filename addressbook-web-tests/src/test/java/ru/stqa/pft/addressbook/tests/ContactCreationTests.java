package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if(! app.contact().findGroup()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group_name"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/jhalpert.jpg");
    ContactData contact = new ContactData()
            .withFirstName("Jim").withLastName("Halpert").withCompany("Dunder Mifflin").withPhone("02012345678")
            .withEmail("j.halpert@dm.com").withAddress("Paper Street, 7, Scranton").withGroup("group_name")
            .withPhoto(photo);
    app.goTo().contactPage();
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File("");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/jhalpert.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
