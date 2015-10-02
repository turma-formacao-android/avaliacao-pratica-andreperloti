package br.com.cast.turmaformacao.agenda.controllers.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ContactListAdapter extends BaseAdapter {

    private List<Contact> contactsList;
    private Activity context;

    public ContactListAdapter(Activity context, List<Contact> contactsList) {
        this.contactsList = contactsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.list_contacts_item, parent, false);
        Contact contact = getItem(position);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(contact.getName());

        return view;
    }

    public void setContacts(List<Contact> contacts) {
        this.contactsList = contacts;
    }

}
