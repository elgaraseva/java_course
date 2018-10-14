package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.goTo().contactPage();
      if(! app.contact().findGroup()){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group_name"));
        app.goTo().contactPage();
      }
      app.contact().create(null, true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactAddress(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergeAddresses(ContactData contact) {
    return Arrays.asList(contact.getAddress(), contact.getSecondAddress())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
