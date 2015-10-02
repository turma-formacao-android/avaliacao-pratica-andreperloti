package br.com.cast.turmaformacao.agenda.controllers.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class Phone implements Parcelable {

    private Long id;
    private String phone;
    private Contact contact;

    public Phone() {
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", contact=" + contact +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone1 = (Phone) o;

        if (!id.equals(phone1.id)) return false;
        if (!phone.equals(phone1.phone)) return false;
        return contact.equals(phone1.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.phone);
        dest.writeParcelable(this.contact, 0);
    }

    protected Phone(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.phone = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
