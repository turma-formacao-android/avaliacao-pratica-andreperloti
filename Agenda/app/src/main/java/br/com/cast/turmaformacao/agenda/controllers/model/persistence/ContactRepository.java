package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactRepository {


    public static void save(Contact contact){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ContactContract.getContentValues(contact);
        if (contact.getId()==null) {
            db.insert(ContactContract.TABLE, null, values);
        }else{
            String where =ContactContract.ID +" = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE,values,where,params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ContactContract.TABLE,where,params);

        db.close();
        databaseHelper.close();
    }

    public static List<Contact> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUMNS, null, null, null, null, ContactContract.ID);
        List<Contact> values = ContactContract.getContacts(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }


    public static Contact getContactById(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUMNS, where, params, null, null, null);
        Contact contact = ContactContract.getContact(cursor);

        db.close();
        databaseHelper.close();
        return contact;
    }

}
