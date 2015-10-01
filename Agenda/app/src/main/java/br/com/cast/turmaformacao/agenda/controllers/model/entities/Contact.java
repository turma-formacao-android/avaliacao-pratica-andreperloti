package br.com.cast.turmaformacao.agenda.controllers.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {

    private Long id;
    private String name;
    private Long age;
    private String address;
    private String phone;
    private String zipCode;
    private String typeOfStreet;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", typeOfStreet='" + typeOfStreet + '\'' +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!id.equals(contact.id)) return false;
        if (!name.equals(contact.name)) return false;
        if (!age.equals(contact.age)) return false;
        if (!address.equals(contact.address)) return false;
        if (!phone.equals(contact.phone)) return false;
        if (!zipCode.equals(contact.zipCode)) return false;
        if (!typeOfStreet.equals(contact.typeOfStreet)) return false;
        if (!street.equals(contact.street)) return false;
        if (!neighborhood.equals(contact.neighborhood)) return false;
        if (!city.equals(contact.city)) return false;
        return state.equals(contact.state);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + typeOfStreet.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + neighborhood.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTypeOfStreet() {
        return typeOfStreet;
    }

    public void setTypeOfStreet(String typeOfStreet) {
        this.typeOfStreet = typeOfStreet;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.age);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.zipCode);
        dest.writeString(this.typeOfStreet);
        dest.writeString(this.street);
        dest.writeString(this.neighborhood);
        dest.writeString(this.city);
        dest.writeString(this.state);
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.age = (Long) in.readValue(Long.class.getClassLoader());
        this.address = in.readString();
        this.phone = in.readString();
        this.zipCode = in.readString();
        this.typeOfStreet = in.readString();
        this.street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
