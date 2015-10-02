package br.com.cast.turmaformacao.agenda.controllers.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class Email implements Parcelable {

    private Long id;
    private String email;
    private Contact contact;

    public Email() {
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (!id.equals(email1.id)) return false;
        if (!email.equals(email1.email)) return false;
        return contact.equals(email1.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        dest.writeString(this.email);
        dest.writeParcelable(this.contact, 0);
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
