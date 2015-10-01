package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactContract {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String ZIPCODE = "zipcode";
    public static final String TYPE_OF_STREET = " typeOfStreet";
    public static final String STREET = " street";
    public static final String NEIGHBORHOOD = " neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] COLUMNS = {ID, NAME, AGE, PHONE, ADDRESS, ZIPCODE, TYPE_OF_STREET, STREET, NEIGHBORHOOD, CITY, STATE};

    public static String getCreateTableScript() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " INTEGER, ");
        sql.append(PHONE + " TEXT, ");
        sql.append(ADDRESS + " TEXT, ");
        sql.append(ZIPCODE + " TEXT, ");
        sql.append(TYPE_OF_STREET + " TEXT, ");
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
        values.put(ContactContract.AGE, contact.getAge());
        values.put(ContactContract.PHONE, contact.getPhone());
        values.put(ContactContract.ADDRESS, contact.getAddress());
        values.put(ContactContract.ZIPCODE, contact.getZipCode());
        values.put(ContactContract.TYPE_OF_STREET, contact.getTypeOfStreet());
        values.put(ContactContract.STREET, contact.getStreet());
        values.put(ContactContract.NEIGHBORHOOD, contact.getNeighborhood());
        values.put(ContactContract.CITY, contact.getCity());
        values.put(ContactContract.STATE, contact.getState());
        return values;
    }

    public static Contact getContact(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.setAge(cursor.getLong(cursor.getColumnIndex(ContactContract.AGE)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactContract.PHONE)));
            contact.setAddress(cursor.getString(cursor.getColumnIndex(ContactContract.ADDRESS)));
            contact.setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.ZIPCODE)));
            contact.setTypeOfStreet(cursor.getString(cursor.getColumnIndex(ContactContract.TYPE_OF_STREET)));
            contact.setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            contact.setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            contact.setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
            contact.setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));

            return contact;
        }
        return null;
    }

    public static List<Contact> getContacts(Cursor cursor) {
        final List<Contact> contacts = new ArrayList<Contact>();
        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }
        return contacts;
    }
}
