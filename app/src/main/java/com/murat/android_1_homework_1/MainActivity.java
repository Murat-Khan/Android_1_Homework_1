package com.murat.android_1_homework_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText email,them,sms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_send);
        email = findViewById(R.id.email);
        them = findViewById(R.id.them);
        sms = findViewById(R.id.sms);

        EditText[] ets = {email, them, sms};

        for (EditText et : ets){
            et.addTextChangedListener(new BaseTextChangeListener(){
                @Override
                public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                    super.onTextChanged(text, i, i1, i2);
                    button.setEnabled(isValid());
                }
            });
        }



        button.setOnClickListener(view -> {
            if (isValid()) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{String.valueOf(email.getText())});
                intent.putExtra(Intent.EXTRA_SUBJECT, them.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, sms.getText());
                intent.setPackage("com.google.android.gm");
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
            }

        });



    }
    private boolean isValid(){
        if (!email.getText().toString().equals("") && !them.getText().toString().equals("") && !sms.getText().toString().equals("")){
            return true;
        }

        return false;
    }
}