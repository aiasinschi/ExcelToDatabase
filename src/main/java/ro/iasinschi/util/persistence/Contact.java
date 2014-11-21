package ro.iasinschi.util.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Adrian Iasinschi (aiasinschi@gmail.com) on 11/19/2014.
 */
@Entity
public class Contact {
    @Id
    private long id;

    private String name;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String website;

    public Contact() {}

    public Contact(long id, String name, String address, String city, String zip, String phone, String website) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (!address.equals(contact.address)) return false;
        if (!city.equals(contact.city)) return false;
        if (!name.equals(contact.name)) return false;
        if (!phone.equals(contact.phone)) return false;
        if (website != null ? !website.equals(contact.website) : contact.website != null) return false;
        if (!zip.equals(contact.zip)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zip.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
