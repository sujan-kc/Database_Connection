package com.Mahesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adduser extends AppCompatActivity {
    EditText edname, edcontact, edpassword, edconfirmpassword;
    private String name, contact, password, confirmpassword;
    Button save;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        setTitle("Add User Here");
        dbHelper = new DBHelper(getApplicationContext());

        edname = findViewById(R.id.edname);
        edcontact = findViewById(R.id.edcontact);
        edpassword = findViewById(R.id.edpassword);
        edconfirmpassword = findViewById(R.id.edconfirmpassword);
        save = findViewById(R.id.btnsave);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateinput();

            }
        });
    }

    private void validateinput() {
        name = edname.getText().toString().trim();
        password = edpassword.getText().toString();
        confirmpassword = edconfirmpassword.getText().toString();
        contact = edcontact.getText().toString();

        if (password.equals(confirmpassword)) {
            //saveindatabase(name,contact,password);

        } else {
            edconfirmpassword.setError("Password Does not Match");
        }
        if (name.equals("") || password.equals("") || confirmpassword.equals("") || contact.equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            saveindatabase(name, contact, password);
        }
        edname.setText("");
        edpassword.setText("");
        edconfirmpassword.setText("");
        edcontact.setText("");
    }
    private void saveindatabase(String name, String contact, String password) {
        Model model = new Model(-1,name,contact,password);
        dbHelper.adduser(model);

    }


    public void view(View view) {
        startActivity(new Intent(Adduser.this, DisplayData.class));
    }
}