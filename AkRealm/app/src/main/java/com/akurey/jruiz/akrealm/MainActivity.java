package com.akurey.jruiz.akrealm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akurey.jruiz.akrealm.model.Person;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    private EditText editText_name;
    private EditText editText_age;
    private TextView textView_log;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare the ui elements
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_age = (EditText) findViewById(R.id.editText_age);
        textView_log = (TextView) findViewById(R.id.textView_log);

        //initiate a realm instance with the default settings
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshViews();
    }

    public void onClick(View view){
        //TODO validate the data retrieved from user inputs to delete the try-catch statement
        try {
            //save the data retrieved from the text field inputs
            save_into_database(editText_name.getText().toString().trim(),
                    Integer.parseInt(editText_age.getText().toString().trim()));
        }catch(Exception exception){
            Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
            Log.v("database",exception.getMessage());
        }
    }

    private void refreshViews() {
        RealmResults<Person> personResults = realm.where(Person.class)
                .findAll();
        String output = "";
        for(Person person: personResults){
            output+= person.toString()+'\n';
        }
        textView_log.setText(output);
    }
    //save into the database a person with name and age
    private void save_into_database(final String name, final int age) {
        //create a async transaction
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                //create a realm object for the new person
                Person person = bgRealm.createObject(Person.class);
                //set the attributes to the person
                person.setName(name);
                person.setAge(age);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //onSuccess
                Log.v("database","The data were stored successfully");
                refreshViews();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //onError
                Log.v("database",error.getMessage());
            }
        });

    }
}
