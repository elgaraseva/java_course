package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts =  generateContacts(count);
    if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    xstream.alias("contact", ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    File photo1 = new File("src/test/resources/jhalpert.jpg");
    File photo2 = new File("src/test/resources/pbeesly.jpg");
    List <ContactData> contacts =  new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData().withFirstName(String.format("Jim", i))
              .withLastName(String.format("Halpert", i)).withCompany(String.format("Dunder Mifflin", i))
              .withPhone(String.format("021147499543", i)).withEmail(String.format("jhalpert@dm.com", i))
              .withAddress(String.format("Paper Street, 77, Scranton", i)).withGroup(String.format("group_name", i))
              .withPhoto(photo1));
      contacts.add(new ContactData().withFirstName(String.format("Pam", i))
              .withLastName(String.format("Beesly", i)).withCompany(String.format("Dunder Mifflin", i))
              .withPhone(String.format("021537499644", i)).withEmail(String.format("p.beesly@dm.com", i))
              .withAddress(String.format("Art Street, 8, Scranton", i)).withGroup(String.format("group_name", i))
              .withPhoto(photo2));
    }
    return contacts;
  }
}
