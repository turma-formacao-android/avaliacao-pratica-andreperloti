package br.com.cast.turmaformacao.agenda.controllers.util;

import android.content.Context;
import android.widget.EditText;

import br.com.cast.turmaformacao.agenda.R;


public class FormHelper {

    private FormHelper(){
        super();
    }

    public static boolean requiredValidate(Context context, EditText... editTexts){
        boolean isValid = true;
        for (EditText editText : editTexts){
            String value = editText.getText() == null ? null : editText.getText().toString();
            if(editText.getText() == null || value.trim().isEmpty()){
                String errorMessage = context.getString(R.string.requiredField);
                editText.setError(errorMessage);
                isValid = false;
            }
        }
        return  isValid;
    }
}