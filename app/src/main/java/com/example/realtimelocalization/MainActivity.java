package com.example.realtimelocalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button changelang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        changelang=findViewById(R.id.button3);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangelanguageDailog();
            }
        });

    }

    private void showChangelanguageDailog() {
        final String[] listitems = {"English","hindi"};
        final AlertDialog.Builder mbuilder =new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle("Change language");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){
                    setLocal("En");
                    recreate();
                }
                else if (i==1)
                {
                    setLocal("hi");
                    recreate();
                }
               dialogInterface.dismiss();
            }
        });
        AlertDialog mdialog=mbuilder.create();
        mbuilder.show();
    }

    private void setLocal(String en) {
        Locale locale=new Locale(en);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor  editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",en);
        editor.apply();
    }

    public void loadLocale()
    {
      //SharedPreference can stored small things of data
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("My_Lang","");
        setLocal(language);
    }
}
