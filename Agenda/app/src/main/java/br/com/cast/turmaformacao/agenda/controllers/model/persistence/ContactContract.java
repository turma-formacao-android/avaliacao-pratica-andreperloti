package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.controllers.model.http.Address;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactContract {

    public static final String TABLE = "Contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ZIPCODE = "zipcode";
    public static final String TYPE = "type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] COLUMNS = {ID, NAME, ZIPCODE, TYPE, STREET, NEIGHBORHOOD, CITY, STATE};

    public static String getCreateTableScript() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(ZIPCODE + " TEXT, ");
        sql.append(TYPE + " TEXT, ");
        sql.append(STREET + " TEXT, ");
        sql.append(NEIGHBORHOOD + " TEXT, ");
        sql.append(CITY + " TEXT, ");
        sql.append(STATE + " TEXT ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.ZIPCODE, contact.getAddress().getZipCode());
        values.put(ContactContract.TYPE, contact.getAddress().getType());
        values.put(ContactContract.STREET, contact.getAddress().getStreet());
        values.put(ContactContract.NEIGHBORHOOD, contact.getAddress().getNeighborhood());
        values.put(ContactContract.CITY, contact.getAddress().getCity());
        values.put(ContactContract.STATE, contact.getAddress().getState());
        return values;
    }

    public static Contact getContact(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));

                Address address = new Address();

                address.setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.ZIPCODE)));
                address.setType(cursor.getString(cursor.getColumnIndex(ContactContract.TYPE)));
                address.setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
                address.setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
                address.setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
                address.setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));
                contact.setAddress(address);
            return contact;
        }
        return null;
    }

    public static List<Contact> getContacts(Cursor cursor) {
        final List<Contact> contacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }
        return contacts;
    }
}
