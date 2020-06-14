package com.example.talha.teznakliyee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Talha on 24.5.2017.
 */

public class firstScreen extends AppCompatActivity {
    Button btnAra, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
        btnAra = findViewById(R.id.btn_ara);
        btnVer = findViewById(R.id.btn_ver);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdverdActivity.class));
            }
        });
    }
}
