package com.Mahesh;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText edname, edcontact, edpassword, edconfirmpassword;
    private String name, contact, password, confirmpassword;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Model data = (Model) getIntent().getExtras().getSerializable("user");
        id = data.getId();

        edname = findViewById(R.id.edname);
        edcontact = findViewById(R.id.edcontact);
        edpassword = findViewById(R.id.edpassword);
        edconfirmpassword = findViewById(R.id.edconfirmpassword);

        edname.setText(data.getName());
        edcontact.setText(data.getContact());
        edpassword.setText(data.getPassword());
        //edconfirmpassword.setText(data.getConfirmpassword());



    }

    public void update(View view) {
        name = edname.getText().toString().trim();
        password = edpassword.getText().toString();
       // confirmpassword = edconfirmpassword.getText().toString();
        contact = edcontact.getText().toString();

        Model m = new Model(id,name,contact,password);
        DBHelper dbHelper = new DBHelper(this);
        int result=  dbHelper.updateUser(m);
        if (result>0){
            Toast.makeText(this, "Update Successfully " +result, Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "Failed" +result, Toast.LENGTH_SHORT).show();
        }
    }

}