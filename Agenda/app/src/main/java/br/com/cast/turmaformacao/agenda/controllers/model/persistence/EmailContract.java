package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Email;

/**
 * Created by Administrador on 02/10/2015.
 */
public class EmailContract {

    public static final String TABLE = "emails";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String ID_CONTACT = "id_email";


    public static final String[] COLUMNS = {ID, EMAIL, ID_CONTACT};

    public static String getCreateTableScript() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(EMAIL + " TEXT, ");
        sql.append(ID_CONTACT + " INTEGER, ");
        sql.append(" FOREIGN KEY (" + ID_CONTACT + ") REFERENCES " + ContactContract.TABLE + " ("+ ContactContract.ID + ") ");

        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();

        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL, email.getEmail());
        values.put(EmailContract.ID_CONTACT, email.getContact().getId());
        return values;
    }

    public static Email getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();
            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));
            email.setContact(ContactRepository.getContactById(cursor.getLong(cursor.getColumnIndex(EmailContract.ID_CONTACT))));

            return email;
        }
        return null;
    }

    public static List<Email> getEmails(Cursor cursor) {
        final List<Email> emails = new ArrayList<Email>();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }

}
