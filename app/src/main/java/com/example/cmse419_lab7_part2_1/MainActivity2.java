package com.example.cmse419_lab7_part2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    Button buttonUpdate;
    String filename="people.txt";
    FileOutputStream fos= null;
    ArrayAdapter<String> adapter;
    ArrayList<String> people=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        lv = findViewById(R.id.lv);
        buttonUpdate = findViewById(R.id.buttonUpdate);




        //reading text from file
        try {
            String text="";
            FileInputStream fis=openFileInput(filename);
            int size=fis.available();
            byte[] buffer=new byte[size];
            fis.read(buffer);
            fis.close();
            text=new String(buffer);
            people = new ArrayList<String>(Arrays.asList(text.split("\n")));



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,people);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                people.remove(position);
                adapter.notifyDataSetChanged();


            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add-write text into file
                try {
                    FileOutputStream fos=openFileOutput(filename, Context.MODE_PRIVATE);

                    for(int i=0;i<people.size();i++){
                        String text=people.get(i)+"\n";
                        fos.write(text.getBytes());
                    }

                    fos.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}