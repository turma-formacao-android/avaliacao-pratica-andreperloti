package br.com.cast.turmaformacao.agenda.controllers.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.controllers.model.services.ContactBusinessService;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactsListActivity extends AppCompatActivity {

    private FloatingActionButton fAButton;
    private Contact contact;
    private ListView listViewContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        bindFAButton();

        bindContactList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdate();
    }

    private void onUpdate() {
        List<Contact> values = ContactBusinessService.findAll();
        listViewContacts.setAdapter(new ContactListAdapter(this, values));

        ContactListAdapter adapter = (ContactListAdapter) listViewContacts.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void bindContactList() {
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        registerForContextMenu(listViewContacts);
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListAdapter adapter = (ContactListAdapter) listViewContacts.getAdapter();
                contact = adapter.getItem(position);
                return false;
            }
        });


    }


    private void bindFAButton() {
        fAButton = (FloatingActionButton) findViewById(R.id.floatButtonAddContacts);
        fAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPrincipal = new Intent(ContactsListActivity.this, ContactsRegisterActivity.class);
                startActivity(goToPrincipal);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contact_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDelete:
                onMenuDeleteClick();
                break;
            case R.id.menuEdit:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(ContactsListActivity.this, ContactsRegisterActivity.class);
        goToTaskForm.putExtra("CONTACT", contact);
        startActivity(goToTaskForm);

    }

    private void onMenuDeleteClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ContactsListActivity.this);
        builder.setTitle(R.string.lbl_confirm);
        builder.setMessage(R.string.msg_confirma_delete);
        builder.setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactBusinessService.delete(contact);
                String message = getString(R.string.msg_delete_success);
                Toast.makeText(ContactsListActivity.this, message, Toast.LENGTH_LONG).show();
                onUpdate();
            }
        });
        builder.setNeutralButton(R.string.lbl_no, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
