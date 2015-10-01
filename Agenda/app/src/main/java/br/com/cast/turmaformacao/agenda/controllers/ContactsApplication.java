package br.com.cast.turmaformacao.agenda.controllers;


import br.com.cast.turmaformacao.agenda.controllers.util.ApplicationUtil;

public class ContactsApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }


}
