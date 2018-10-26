package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws FileNotFoundException {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      if(! app.db().groups().contains(app.properties.getProperty("baseGroupe"))){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(app.properties.getProperty("baseGroupe")));
        app.goTo().contactPage();
      }
      app.contact().create(null, true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactEmails() throws FileNotFoundException {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
    verifyContactListInUI();
  }

  private String mergeEmail(ContactData contact) throws FileNotFoundException {
    return Arrays.asList(contact.getEmail(), contact.getSecondEmail(), contact.getThirdEmail())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
