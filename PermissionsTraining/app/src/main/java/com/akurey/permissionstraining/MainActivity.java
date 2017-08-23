package com.akurey.permissionstraining;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button permissionBtn;
    //This permissions must been declared in AndroidManifest.xml
    private static final String[] permissions = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS
    };
    private static final int PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Look for button created in activity_main.xml
        permissionBtn = (Button) findViewById(R.id.permissionBnt);

    }

    //With permissions granted, the app is able to work properly
    private void makeSomething(){
        Toast.makeText(this, "Now you have permissions", Toast.LENGTH_SHORT).show();
    }

    //Checks if permissions was granted previously
    private boolean allPermissionsGranted(){
        int result = 0;

        for (String permission : permissions){
            result = checkCallingOrSelfPermission(permission);
            if(result != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
    //since Android API Level 23 ask for permissions on runtime
    // we need to verify the device API Level
    private boolean isMarshmallowOrNewer(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void askForPermissions(){
        if(isMarshmallowOrNewer()){
            requestPermissions(permissions,PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allowed = true;
        switch (requestCode){
            case PERMISSIONS_REQUEST_CODE:
                for (int result : grantResults){
                    allowed = allowed && (result == PackageManager.PERMISSION_GRANTED);
                }
                break;
            default:
                allowed = false;
                break;
        }
        if (allowed){
            makeSomething();
        }else{
            if(isMarshmallowOrNewer()){
                for(String permission : permissions){
                    if(shouldShowRequestPermissionRationale(permission)){
                        Toast.makeText(this,permission + " denied",Toast.LENGTH_SHORT).show();
                    }
                }


            }

        }
    }
    //When the button is clicked, the app ask for permissions
    public void btnClick(View view){
        if(allPermissionsGranted()){
            makeSomething();
        }else{
            askForPermissions();
        }
    }
}
