package com.example.cmse419_lab7_part2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextSurname, editTextNumber;

    Button buttonAdd, buttonList;


    String filename="people.txt";
    FileOutputStream fos= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextNumber = findViewById(R.id.editTextNumber);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonList = findViewById(R.id.buttonList);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !editTextName.getText().toString().isEmpty() && !editTextSurname.getText().toString().isEmpty() && !editTextNumber.getText().toString().isEmpty() ){

                    String person = editTextName.getText().toString() + " " + editTextSurname.getText().toString() + " " + editTextNumber.getText().toString();


                    try {
                        fos = openFileOutput(filename, Context.MODE_APPEND);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // add-write text into file
                    try {


                        String text=editTextName.getText().toString()+" "+editTextSurname.getText().toString()+" "+editTextNumber.getText().toString()+"\n";
                        fos.write(text.getBytes());


                        //display file saved message
                        Toast.makeText(getBaseContext(), "Person added successfully!", Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editTextName.setText("");
                    editTextSurname.setText("");
                    editTextNumber.setText("");


                }
                else {

                    Toast.makeText(MainActivity.this, "All areas must be filled", Toast.LENGTH_SHORT).show();
                }

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent mainintent=new Intent(MainActivity.this,MainActivity2.class);

                startActivity(mainintent);



            }
        });



    }
}