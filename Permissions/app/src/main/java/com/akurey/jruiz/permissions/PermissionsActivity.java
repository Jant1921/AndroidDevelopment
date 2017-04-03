package com.akurey.jruiz.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class PermissionsActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    protected void requestRunTimePermissions(final Activity activity, final String [] permissions, final int customPermissionConstant){
        if(permissions.length==1){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[0])){

                Snackbar.make(findViewById(android.R.id.content),"App needs permission to work",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(activity,permissions,customPermissionConstant);
                            }
                        }).show();
            }else {
                ActivityCompat.requestPermissions(this,new String[]{permissions[0]},customPermissionConstant);
            }
        }else if(permissions.length>1){
            final List<String> deniedPermissions=new ArrayList<>();

            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(this,permission)== PackageManager.PERMISSION_DENIED){
                    deniedPermissions.add(permission);
                }
            }
            if(deniedPermissions.size()==1){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,deniedPermissions.get(0))){

                    Snackbar.make(findViewById(android.R.id.content),"App needs permission to work",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String [] temp=deniedPermissions.toArray(new String[deniedPermissions.size()]);
                                    ActivityCompat.requestPermissions(activity,temp,customPermissionConstant);
                                }
                            }).show();
                }else {

                    String [] temp=deniedPermissions.toArray(new String[deniedPermissions.size()]);
                    ActivityCompat.requestPermissions(activity,temp,customPermissionConstant);
                }
            }else if(deniedPermissions.size()>1){
                final String [] temp=deniedPermissions.toArray(new String[deniedPermissions.size()]);
                    Snackbar.make(findViewById(android.R.id.content),"This functionality needs multiple app permissions",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(activity,temp,customPermissionConstant);
                        }
                    }).show();
                }
        }
    }
}