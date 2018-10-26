package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws FileNotFoundException, ParserConfigurationException {
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
  public void testContactAddress() throws FileNotFoundException {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
    verifyContactListInUI();
  }

  private String mergeAddresses(ContactData contact) throws FileNotFoundException {
    return Arrays.asList(contact.getAddress(), contact.getSecondAddress())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
