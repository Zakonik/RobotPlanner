package com.example.robotplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    EditText etFirstName,etLastName,etDate, etDescritption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_layout);
        etFirstName = (EditText) findViewById(R.id.editFirstName);
        etLastName = (EditText) findViewById(R.id.editLastName);
        etDate = (EditText) findViewById(R.id.editDate);
        etDescritption = (EditText) findViewById(R.id.editDescription);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fDate = etDate.getText().toString();
                String description = etDescritption.getText().toString();
                if(fName.length() != 0 && lName.length() != 0 && fDate.length() != 0 && description.length() != 0){
                    AddData(fName,lName, fDate, description);
                    etDescritption.setText("");
                    etDate.setText("");
                    etLastName.setText("");
                    etFirstName.setText("");
                }else{
                    Toast.makeText(AddDataActivity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDataActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String fName, String lName, String date, String description) {
        boolean insertData = mDatabaseHelper.addData(fName, lName, date, description);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
