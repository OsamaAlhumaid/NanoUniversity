package com.example.nekfh.androidproject;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class new_student extends Activity {


    studentDatabase studentDB;

    EditText stdID,stdName,grade1,grade2,grade3,hours1,hours2,hours3;

    TextView showGPA;

    Button Calc,Save,View;
    int hours= 0;
    //int []hours= new int[3];
    int fHours;
    String grade="";
    //String []grade= new String[3];
    double calcGrades=0.00,GPA=0.00;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        Calc= (Button) findViewById(R.id.Clc);
        Save=(Button) findViewById(R.id.save);
        stdID= (EditText) findViewById(R.id.stdID);
        stdName=(EditText) findViewById(R.id.stdName);
        grade1= (EditText) findViewById(R.id.grade1);
        //grade2= (EditText) findViewById(R.id.grade2);
       // grade3= (EditText) findViewById(R.id.grade3);
        hours1= (EditText) findViewById(R.id.hours1);
        //hours2= (EditText) findViewById(R.id.hours2);
        //hours3= (EditText) findViewById(R.id.hours3);
        showGPA=(TextView) findViewById(R.id.txtGPA);
        studentDB = new studentDatabase(this);


        Calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                boolean correct=true;

                    correct=true;
                    try {
                        hours = Integer.parseInt(hours1.getText().toString());
                    }catch(Exception e) {
                        correct=false;

                        Toast.makeText(getApplicationContext(),"Enter integers!",Toast.LENGTH_LONG).show();


                    }
                    grade=grade1.getText().toString();
                    double grading=calulator(grade);
                    if(grading==0.00) correct=false;

                    if(correct==true){
                        calcGrades+=grading*hours;
                        fHours+=hours;
                        GPA=calcGrades/fHours;
                        showGPA.setText(Double.toString(GPA));
                        hours1.setText("");
                        grade1.setText("");
                        Toast.makeText(getApplicationContext(),"enter next grades!",Toast.LENGTH_LONG).show();
                    }
                    else {

                        Toast.makeText(getApplicationContext(),"wrong inputs!",Toast.LENGTH_LONG).show();
                    }

            }

        });

        /*Calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                for (int i=0;i<10;i++){
                    hours[0]= Integer.parseInt(hours1.getText().toString());
                //hours[1]= Integer.parseInt(hours2.getText().toString());
               // hours[2]= Integer.parseInt(hours3.getText().toString());

                grade[0]= grade1.getText().toString();
                //grade[1]= grade2.getText().toString();
                //grade[2]= grade3.getText().toString();

                calcGrades=0.00;
                for(int i=0;i<3;i++) {
                    calcGrades += (calulator(grade[i])*hours[i]);

                }
                fHours=0;
                for(int i=0;i<3;i++)
                    fHours+=hours[i];


                GPA=calcGrades/fHours;

                showGPA.setText(Double.toString(GPA));}




            }

        });*/
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stdid = Integer.parseInt(stdID.getText().toString());
                String stdname = stdName.getText().toString();


                studentDB.addRecord(stdid,stdname,GPA,fHours,calcGrades);


                // String dbString = studentDB.showResults();
                Toast.makeText(getApplicationContext(),"Record is Addedd!",Toast.LENGTH_LONG).show();



            }
        });



    }

    public double calulator(String g){
        double finalGrade=0.00;
        switch (g){
            case "A":
            case "a":
                finalGrade=4.75;
                break;
            case "B":
            case "b":
                finalGrade=4.00;
                break;
            case "C":
            case "c":
                finalGrade=3.00;
                break;
            case "D":
            case "d":
                finalGrade=2.00;
                break;
            default:
                Toast.makeText(getApplicationContext(),"wrong input enter A,B or C or D for grades",Toast.LENGTH_LONG).show();


        }
        return finalGrade;
    }

    public void moveToDB(View v){
        Intent i = new Intent(this, student_query.class);
        startActivity(i);
        finish();
    }

}