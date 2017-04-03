package com.akurey.jruiz.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.Permission;



public class MainActivity extends AppCompatActivity {
    final private int REQUEST_READ_CONTACTS_CODE = 12;
    View.OnClickListener mOnClickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Se tiene acceso",Toast.LENGTH_SHORT).show();
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)){
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "You should let us read your contacts ;)", Snackbar.LENGTH_LONG)
                        .setAction("Enable Permission", new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        REQUEST_READ_CONTACTS_CODE
                                );
                            }
                        });
                snackbar.show();
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_READ_CONTACTS_CODE
                );
            }
        }
    }
}
