package ro.iasinschi.util.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.iasinschi.util.excel.ExcelReader;
import ro.iasinschi.util.persistence.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * <b>UploadFromExcel</b> class
 * Reads an Excel file and inserts all the data into a database
 * @author Adrian Iasinschi (aiasinschi@gmail.com) on 11/19/2014.
 * @since 1.0
 */
public class UploadFromExcel {

    private EntityManager manager;

    private Log LOG = LogFactory.getLog(UploadFromExcel.class);

    public UploadFromExcel(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
        manager = factory.createEntityManager();
    }

    public void doPersistence(String fileName){
        ExcelReader reader = new ExcelReader(fileName);
        reader.read();
        List<List<String>> data = reader.getData();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (int i = 1; i < data.size(); i++){
            List<String> row = data.get(i);
            LOG.debug("First element in row that should be converted from double to long is: " + row.get(0));
            System.out.println("First element in row that should be converted from double to long is: " + row.get(0));
            long id = Math.round(Double.parseDouble(row.get(0)));
            String name = row.get(1);
            String address = row.get(2);
            String city = row.get(3);
            String zip = row.get(4);
            String phone = row.get(5);
            String website = row.get(6);
            Contact contact = new Contact(id, name, address, city, zip, phone, website);
            manager.persist(contact);
        }
        transaction.commit();
    }

    private void listContacts() {
        List<Contact> resultList = manager.createNativeQuery("Select * From Contact;", Contact.class).getResultList();
        System.out.println("Number of contacts: " + resultList.size());
        for (Contact next : resultList) {
            System.out.println("Next employee: " + next);
        }
    }

    public static void main(String args[]){
        UploadFromExcel app = new UploadFromExcel();
        //app.doPersistence("plumberdb.xlsx");
        app.listContacts();
    }

}
