package com.guardian.guardianadmin_v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class GPSAndInternetChecker {
    public static boolean check(Context context){
        if(!isInternetConnected(context)) {
            showInternetAlert(context);
            return false;
        }
        return true;
    }


    public static void showInternetAlert(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.internet_alert_dialog, null);

        Button wifiBtn = view.findViewById(R.id.wifiButton);
        wifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        Button internetBtn = view.findViewById(R.id.internetButton);
        internetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Settings.ACTION_DATA_USAGE_SETTINGS));
            }
        });

        builder.setView(view);
        builder.show();

//        builder.setPositiveButton("اینترنت Wi-Fi", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//            }
//        });
//
//        builder.setNegativeButton("اینترنت موبایل",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                context.startActivity(new Intent(Settings.ACTION_DATA_USAGE_SETTINGS));
//
//            }
//        });


//        new AlertDialog.Builder(context)
//                .setTitle("عدم اتصال به اینترنت                 ")
//                .setMessage("اتصال شما به اینترنت برقرار نیست. برای استفاده از گاردین به اینترنت متصل شوید!")
//                .setPositiveButton("اینترنت Wi-Fi", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                    }
//                })
//                .setNegativeButton("اینترنت موبایل",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        context.startActivity(new Intent(Settings.ACTION_DATA_USAGE_SETTINGS));
//
//                    }
//                })
//                .show();

    }

    public static boolean isInternetConnected(Context context){

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected ;
    }
}
