package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Email;

/**
 * Created by Administrador on 02/10/2015.
 */

public class EmailRepository {

    public static void save(Email email){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmailContract.getContentValues(email);
        if (email.getId()==null) {
            db.insert(EmailContract.TABLE, null, values);
        }else{
            String where =EmailContract.ID +" = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE,values,where,params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = EmailContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(EmailContract.TABLE,where,params);

        db.close();
        databaseHelper.close();
    }

    public static List<Email> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, null, null, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }


    public static Email getEmailById(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, params, null, null, null);
        Email email = EmailContract.getEmail(cursor);

        db.close();
        databaseHelper.close();
        return email;
    }


}
