package com.example.nekfh.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class student_query extends Activity {
    private studentDatabase studentDB2;
    TextView View_text;
    EditText txtID;

    Button View,sort_gpa,delet_btn,update_btn;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_query);
        View = (Button) findViewById(R.id.View_btn2);
        sort_gpa = (Button) findViewById(R.id.sort);
        delet_btn = (Button) findViewById(R.id.deletebtn);
        update_btn = (Button) findViewById(R.id.updatebtn);
        View_text = (TextView) findViewById(R.id.result);
        txtID = (EditText) findViewById(R.id.txtid);
        studentDB2 = new studentDatabase(this); // most important line on the whole proje

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor result = studentDB2.getData();
                showresult(result);



            }
        });

        sort_gpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = studentDB2.sortByGpa();
                showresult(result);
            }
        });

        delet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtID.getText().toString();
                String showtxt;
                if(id.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show();
                    return;
                }
                //int intID=Integer.parseInt(id);
                showtxt=studentDB2.delTsk(id);
                Toast.makeText(getApplicationContext(),showtxt,Toast.LENGTH_LONG).show();
                txtID.setText("");

                Cursor result = studentDB2.getData();
                showresult(result);

            }
        });





    }


    public void moveToUpdate(View v){
        String id = txtID.getText().toString();
        Intent i = new Intent(this, update_student.class);
       i.putExtra("key",id);
        startActivity(i);
    }

    public void showresult(Cursor res){

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            String gpa = df2.format(Double.parseDouble(res.getString(2)));


            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("GPA: " + gpa + "\n");
            buffer.append("Hours: " + res.getString(3) + "\n\n");
        }


        // Toast.makeText(getApplicationContext(),buffer.toString(),Toast.LENGTH_LONG).show();
        View_text.setMovementMethod(new ScrollingMovementMethod());
        View_text.setText("");

        View_text.setText(buffer.toString());



    }
}
