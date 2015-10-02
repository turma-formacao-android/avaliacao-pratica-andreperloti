package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Phone;

/**
 * Created by Administrador on 02/10/2015.
 */

public class PhoneRepository {

    public static void save(Phone phone){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = PhoneContract.getContentValues(phone);
        if (phone.getId()==null) {
            db.insert(PhoneContract.TABLE, null, values);
        }else{
            String where =PhoneContract.ID +" = ? ";
            String[] params = {phone.getId().toString()};
            db.update(PhoneContract.TABLE,values,where,params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = PhoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(PhoneContract.TABLE,where,params);

        db.close();
        databaseHelper.close();
    }

    public static List<Phone> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUMNS, null, null, null, null, PhoneContract.ID);
        List<Phone> values = PhoneContract.getPhones(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }


    public static Phone getPhoneById(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = PhoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        Cursor cursor = db.query(PhoneContract.TABLE, PhoneContract.COLUMNS, where, params, null, null, null);
        Phone phone = PhoneContract.getPhone(cursor);

        db.close();
        databaseHelper.close();
        return phone;
    }


}
