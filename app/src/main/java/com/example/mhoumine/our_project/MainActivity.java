package com.example.mhoumine.our_project;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mhoumine.our_project.model.SharedPreference.SaveSharedPreference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button b = (Button) findViewById(R.id.something);
//        Button b2 = (Button) findViewById(R.id.something2);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SaveSharedPreference shared = new SaveSharedPreference();
//                try {
//                    String userInfo[] = shared.getUsernameAndPass(getApplicationContext());
//                    Toast.makeText(getApplicationContext(),userInfo[0] + " " + userInfo[1], Toast.LENGTH_LONG).show();
//                }
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SaveSharedPreference shared = new SaveSharedPreference();
//                try {
//                    shared.addUsernameAndPass("one", "two", getApplicationContext());
//                }
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

    }


}

