package br.com.cast.turmaformacao.agenda.controllers.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cast.turmaformacao.agenda.controllers.model.entities.Email;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Social;
import br.com.cast.turmaformacao.agenda.controllers.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "productdb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactContract.getCreateTableScript());
        db.execSQL(EmailContract.getCreateTableScript());
        db.execSQL(PhoneContract.getCreateTableScript());
        db.execSQL(SocialContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
