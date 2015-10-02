package br.com.cast.turmaformacao.agenda.controllers.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class Social implements Parcelable {

    private Long id;
    private String social;
    private Contact contact;

    public Social(){
    }

    @Override
    public String toString() {
        return "Social{" +
                "id=" + id +
                ", social='" + social + '\'' +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Social social1 = (Social) o;

        if (!id.equals(social1.id)) return false;
        if (!social.equals(social1.social)) return false;
        return contact.equals(social1.contact);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + social.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
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
        dest.writeString(this.social);
        dest.writeParcelable(this.contact, 0);
    }

    protected Social(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.social = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
    }

    public static final Parcelable.Creator<Social> CREATOR = new Parcelable.Creator<Social>() {
        public Social createFromParcel(Parcel source) {
            return new Social(source);
        }

        public Social[] newArray(int size) {
            return new Social[size];
        }
    };
}
