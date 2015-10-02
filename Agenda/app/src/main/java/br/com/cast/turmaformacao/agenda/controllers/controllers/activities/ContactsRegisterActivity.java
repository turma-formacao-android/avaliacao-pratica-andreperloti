package br.com.cast.turmaformacao.agenda.controllers.controllers.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Email;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.controllers.model.entities.Social;
import br.com.cast.turmaformacao.agenda.controllers.model.http.Address;
import br.com.cast.turmaformacao.agenda.controllers.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.controllers.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.controllers.model.services.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.controllers.util.FormHelper;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ContactsRegisterActivity extends AppCompatActivity {

    private static final String PARAM_CONTACT = "CONTACT";

    private Contact contact;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextSocialNetworks;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;

    private ListView listViewPhones;
    private ListView listViewEmail;
    private ListView listViewSocialNetworks;

    public ArrayList<String> numbersString = new ArrayList<>();
    public ArrayList<String> emailsString = new ArrayList<>();
    public ArrayList<String> socialNetworksString = new ArrayList<>();


    public ArrayList<Phone> phones = new ArrayList<>();
    public ArrayList<Email> emails = new ArrayList<>();
    public ArrayList<Social> socials = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_register);
        initContact();
        bindEditTextName();
        bindEditTextPhone();
        bindEditTextEmail();
        bindEditTextSocial();
        bindEditTextZipCode();
        bindEditTextType();
        bindEditTextStreet();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();

        bindListViewPhones();
        bindListViewEmails();
        bindListViewSocial();


    }

    private void bindListViewPhones() {
        listViewPhones = (ListView) findViewById(R.id.listViewPhones);

        listViewPhones.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindListViewEmails() {
        listViewEmail = (ListView) findViewById(R.id.listViewEmail);

        listViewEmail.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }


    private void bindListViewSocial() {
        listViewSocialNetworks = (ListView) findViewById(R.id.listViewSocialNetworks);

        listViewSocialNetworks.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }




    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = extras.getParcelable(PARAM_CONTACT);
        }
        this.contact = contact == null ? new Contact() : this.contact;
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.edtTextName);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_add_contact, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });

    }

    private void bindEditTextPhone() {
        editTextPhone = (EditText) findViewById(R.id.edtTextPhone);
        editTextPhone.setText(contact.getPhone() == null ? "" : contact.getPhone());
        editTextPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_content_add, 0);
        editTextPhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextPhone.getRight() - editTextPhone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {


                        // #############################################
                        Phone phone = new Phone();

                        phone.setPhone(editTextPhone.getText().toString());

                        numbersString.add(phone.getPhone());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactsRegisterActivity.this, android.R.layout.simple_list_item_1, numbersString);
                        listViewPhones.setAdapter(adapter);

                        phones.add(phone);
                        Toast.makeText(ContactsRegisterActivity.this, R.string.msg_add_telephone, Toast.LENGTH_SHORT).show();
                        editTextPhone.setText("");
                    }
                }
                return false;
            }
        });
    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextEmail.setText(contact.getEmail() == null ? "" : contact.getEmail());
        editTextEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_content_add, 0);
        editTextEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextEmail.getRight() - editTextEmail.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {


                        // #############################################
                        Email email = new Email();

                        email.setEmail(editTextEmail.getText().toString());

                        emailsString.add(email.getEmail());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactsRegisterActivity.this, android.R.layout.simple_list_item_1, emailsString);
                        listViewEmail.setAdapter(adapter);

                        emails.add(email);
                        Toast.makeText(ContactsRegisterActivity.this, R.string.msg_add_telephone, Toast.LENGTH_SHORT).show();
                        editTextEmail.setText("");
                    }
                }
                return false;
            }

        });
    }


    private void bindEditTextSocial() {
        editTextSocialNetworks = (EditText) findViewById(R.id.edtTextSocialNetworks);
        editTextSocialNetworks.setText(contact.getSocialNetworks() == null ? "" : contact.getSocialNetworks());
        editTextSocialNetworks.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_content_add, 0);
        editTextSocialNetworks.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextSocialNetworks.getRight() - editTextSocialNetworks.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {


                        // #############################################
                        Social social = new Social();

                        social.setSocial(editTextSocialNetworks.getText().toString());

                        socialNetworksString.add(social.getSocial());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactsRegisterActivity.this, android.R.layout.simple_list_item_1, socialNetworksString);
                        listViewSocialNetworks.setAdapter(adapter);

                        socials.add(social);
                        Toast.makeText(ContactsRegisterActivity.this, R.string.msg_add_telephone, Toast.LENGTH_SHORT).show();
                        editTextSocialNetworks.setText("");
                    }
                }
                return false;
            }

        });
    }



    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.edtTextZipCode);
        if (contact.getAddress() != null) {
            editTextZipCode.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode());
        }
        editTextZipCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_action_search, 0);
        editTextZipCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextZipCode.getRight() - editTextZipCode.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        new GetAddressByZipCode().execute(editTextZipCode.getText().toString());
                    }
                }
                return false;
            }
        });


    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.edtTextStreet);
        if (contact.getAddress() != null) {
            editTextStreet.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
        }
    }

    private void bindEditTextType() {
        editTextType = (EditText) findViewById(R.id.edtTextType);
        if (contact.getAddress() != null) {
            editTextType.setText(contact.getAddress().getType() == null ? "" : contact.getAddress().getType());
        }
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.edtTextNeighborhood);
        if (contact.getAddress() != null) {
            editTextNeighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());
        }
    }


    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.edtTextCity);
        if (contact.getAddress() != null) {
            editTextCity.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
        }
    }

    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.editTextState);
        if (contact.getAddress() != null) {
            editTextState.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {

            if (FormHelper.requiredValidate(ContactsRegisterActivity.this, editTextName, editTextPhone,
                    editTextZipCode, editTextType, editTextStreet, editTextNeighborhood, editTextCity, editTextState)) {
                bindContact();
                save(contact);

                Toast.makeText(ContactsRegisterActivity.this, ContactRepository.getAll().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(ContactsRegisterActivity.this, R.string.msgSuccess, Toast.LENGTH_LONG).show();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void save(Contact contact) {
        ContactBusinessService.save(contact);
    }


    private Contact bindContact() {
        if (contact == null) {
            contact = new Contact();
        }
        contact.setName(editTextName.getText().toString());
        contact.setPhone(editTextPhone.getText().toString());


        Address address = new Address();

        address.setZipCode(editTextZipCode.getText().toString());
        address.setType(editTextType.getText().toString());
        address.setStreet(editTextStreet.getText().toString());
        address.setNeighborhood(editTextNeighborhood.getText().toString());
        address.setCity(editTextCity.getText().toString());
        address.setState(editTextState.getText().toString());


        contact.setAddress(address);
        return contact;
    }

    private void bindForm(Contact contact) {
        editTextName.setText(contact.getName());
        editTextPhone.setText(contact.getPhone());
        editTextType.setText(contact.getAddress().getType());
        editTextStreet.setText(contact.getAddress().getStreet());
        editTextNeighborhood.setText(contact.getAddress().getNeighborhood());
        editTextCity.setText(contact.getAddress().getCity());
        editTextState.setText(contact.getAddress().getState());
        editTextZipCode.setText(contact.getAddress().getZipCode());
    }


    private class GetAddressByZipCode extends AsyncTask<String, Void, Address> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ContactsRegisterActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAdressByZipcode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            editTextCity.setText(address.getCity().toString());
            editTextNeighborhood.setText(address.getNeighborhood().toString());
            editTextStreet.setText(address.getStreet().toString());
            editTextType.setText(address.getType().toString());
            editTextState.setText(address.getState().toString());

            progressDialog.dismiss();
        }

    }


    //******************************************************************************
    //#############################################################################

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
