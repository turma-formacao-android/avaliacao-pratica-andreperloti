package br.com.cast.turmaformacao.agenda.controllers.util;

import android.content.Context;

public class ApplicationUtil {

    private static Context context;

    private ApplicationUtil(){
        super();
    }

    public static void  setContext(Context context){
        ApplicationUtil.context = context;
    }

    public static Context getContext(){
        return ApplicationUtil.context;
    }

}
