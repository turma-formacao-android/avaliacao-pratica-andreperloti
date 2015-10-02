package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Social;


/**
 * Created by Administrador on 02/10/2015.
 */

public class SocialContract {

    public static final String TABLE = "socials";
    public static final String ID = "id";
    public static final String SOCIAL = "social";
    public static final String ID_CONTACT = "id_social";


    public static final String[] COLUMNS = {ID, SOCIAL, ID_CONTACT};

    public static String getCreateTableScript() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(SOCIAL + " TEXT, ");
        sql.append(ID_CONTACT + " INTEGER, ");
        sql.append(" FOREIGN KEY (" + ID_CONTACT + ") REFERENCES " + ContactContract.TABLE + " ("+ ContactContract.ID + ") ");

        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Social social) {
        ContentValues values = new ContentValues();

        values.put(SocialContract.ID, social.getId());
        values.put(SocialContract.SOCIAL, social.getSocial());
        values.put(SocialContract.ID_CONTACT, social.getContact().getId());
        return values;
    }

    public static Social getSocial(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Social social = new Social();
            social.setId(cursor.getLong(cursor.getColumnIndex(SocialContract.ID)));
            social.setSocial(cursor.getString(cursor.getColumnIndex(SocialContract.SOCIAL)));
            social.setContact(ContactRepository.getContactById(cursor.getLong(cursor.getColumnIndex(SocialContract.ID_CONTACT))));

            return social;
        }
        return null;
    }

    public static List<Social> getSocials(Cursor cursor) {
        final List<Social> socials = new ArrayList<Social>();
        while (cursor.moveToNext()) {
            socials.add(getSocial(cursor));
        }
        return socials;
    }

}

