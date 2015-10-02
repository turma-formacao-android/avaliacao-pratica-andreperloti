package br.com.cast.turmaformacao.agenda.controllers.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.http.Address;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.controllers.model.services.ContactBusinessService;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String SocialNetworks;

    private Address address;

    private Phone[] phones;
    private Email[] emails;
    private Social[] social;

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", SocialNetworks='" + SocialNetworks + '\'' +
                ", address=" + address +
                ", phones=" + Arrays.toString(phones) +
                ", emails=" + Arrays.toString(emails) +
                ", social=" + Arrays.toString(social) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!id.equals(contact.id)) return false;
        if (!name.equals(contact.name)) return false;
        if (!phone.equals(contact.phone)) return false;
        if (!email.equals(contact.email)) return false;
        if (!SocialNetworks.equals(contact.SocialNetworks)) return false;
        if (!address.equals(contact.address)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(phones, contact.phones)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(emails, contact.emails)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(social, contact.social);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + SocialNetworks.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + Arrays.hashCode(phones);
        result = 31 * result + Arrays.hashCode(emails);
        result = 31 * result + Arrays.hashCode(social);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialNetworks() {
        return SocialNetworks;
    }

    public void setSocialNetworks(String socialNetworks) {
        SocialNetworks = socialNetworks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone[] getPhones() {
        return phones;
    }

    public void setPhones(Phone[] phones) {
        this.phones = phones;
    }

    public Email[] getEmails() {
        return emails;
    }

    public void setEmails(Email[] emails) {
        this.emails = emails;
    }

    public Social[] getSocial() {
        return social;
    }

    public void setSocial(Social[] social) {
        this.social = social;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.SocialNetworks);
        dest.writeParcelable(this.address, 0);
        dest.writeParcelableArray(this.phones, 0);
        dest.writeParcelableArray(this.emails, 0);
        dest.writeParcelableArray(this.social, 0);
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.SocialNetworks = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.phones = (Phone[]) in.readParcelableArray(Phone.class.getClassLoader());
        this.emails = (Email[]) in.readParcelableArray(Email.class.getClassLoader());
        this.social = (Social[]) in.readParcelableArray(Social.class.getClassLoader());
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
