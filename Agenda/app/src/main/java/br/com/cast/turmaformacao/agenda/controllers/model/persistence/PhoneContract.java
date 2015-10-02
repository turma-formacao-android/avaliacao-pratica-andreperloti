package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */

public class PhoneContract {


    public static final String TABLE = "phones";
    public static final String ID = "id";
    public static final String PHONE = "phone";
    public static final String ID_CONTACT = "id_phone";


    public static final String[] COLUMNS = {ID, PHONE, ID_CONTACT};

    public static String getCreateTableScript() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(PHONE + " TEXT, ");
        sql.append(ID_CONTACT + " INTEGER, ");
        sql.append(" FOREIGN KEY (" + ID_CONTACT + ") REFERENCES " + ContactContract.TABLE + " ("+ ContactContract.ID + ") ");

        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Phone phone) {
        ContentValues values = new ContentValues();

        values.put(PhoneContract.ID, phone.getId());
        values.put(PhoneContract.PHONE, phone.getPhone());
        values.put(PhoneContract.ID_CONTACT, phone.getContact().getId());
        return values;
    }

    public static Phone getPhone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Phone phone = new Phone();
            phone.setId(cursor.getLong(cursor.getColumnIndex(PhoneContract.ID)));
            phone.setPhone(cursor.getString(cursor.getColumnIndex(PhoneContract.PHONE)));
            phone.setContact(ContactRepository.getContactById(cursor.getLong(cursor.getColumnIndex(PhoneContract.ID_CONTACT))));

            return phone;
        }
        return null;
    }

    public static List<Phone> getPhones(Cursor cursor) {
        final List<Phone> phones = new ArrayList<Phone>();
        while (cursor.moveToNext()) {
            phones.add(getPhone(cursor));
        }
        return phones;
    }
}
