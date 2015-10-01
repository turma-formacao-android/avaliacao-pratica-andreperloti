package br.com.cast.turmaformacao.agenda.controllers.util;

import android.widget.EditText;


public class FormHelper {

    private FormHelper(){
        super();
    }

    public static boolean validateRequired(String requiredMessage, EditText... editTexts ){
        boolean hasRequired =false;

        for(EditText editText: editTexts){
            String textvalue = editText.getText().toString();
            if(textvalue.trim().isEmpty()){
                editText.setError(requiredMessage);
                hasRequired = true;
            }
        }
        return hasRequired;
    }
}