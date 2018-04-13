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

public class update_student extends Activity {
    private studentDatabase studentDB2;
    String id;
    Button calcu, Save;
    EditText grad, hour;
    TextView showGPA, data_btn;

    int hours = 0;
    int fHours=0;
    String grade = "";
    double calcGrades = 0.00, GPA = 0.00;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("key");



        calcu = (Button) findViewById(R.id.Calcu);
        Save = (Button) findViewById(R.id.savebtn);
        grad = (EditText) findViewById(R.id.gradetxt);
        hour = (EditText) findViewById(R.id.hourstxt);
        showGPA = (TextView) findViewById(R.id.gpaview);
        data_btn = (TextView) findViewById(R.id.databtn);
        Save = (Button) findViewById(R.id.savebtn);
        studentDB2 = new studentDatabase(this);

        String rgm=id;
        Cursor result = studentDB2.getGPA(rgm);
        // Cursor result = studentDB2.getData();
        showresult(result);



        calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                boolean correct = true;

                correct = true;
                try {
                    hours = Integer.parseInt(hour.getText().toString());
                } catch (Exception e) {
                    correct = false;

                    Toast.makeText(getApplicationContext(), "Enter integers!", Toast.LENGTH_LONG).show();


                }
                grade = grad.getText().toString();
                double grading = calulator(grade);
                if (grading == 0.00) correct = false;

                if (correct == true) {
                    calcGrades += grading * hours;
                    fHours += hours;
                    GPA = calcGrades / fHours;
                    showGPA.setText(Double.toString(GPA));
                    hour.setText("");
                    grad.setText("");
                    Toast.makeText(getApplicationContext(), "enter next grades!", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "wrong inputs!", Toast.LENGTH_LONG).show();
                }

            }

        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                studentDB2.updateRecord(id,GPA,fHours,calcGrades);


                // String dbString = studentDB.showResults();
                Toast.makeText(getApplicationContext(),"Record is Addedd!",Toast.LENGTH_LONG).show();



                finish();
            }
        });

    }


    public double calulator(String g) {
        double finalGrade = 0.00;
        switch (g) {
            case "A":
            case "a":
                finalGrade = 4.75;
                break;
            case "B":
            case "b":
                finalGrade = 4.00;
                break;
            case "C":
            case "c":
                finalGrade = 3.00;
                break;
            case "D":
            case "d":
                finalGrade = 2.00;
                break;
            default:
                Toast.makeText(getApplicationContext(), "wrong input enter A,B or C or D for grades", Toast.LENGTH_LONG).show();


        }
        return finalGrade;
    }

    public void showresult(Cursor res) {

        StringBuffer buffer = new StringBuffer();

       while (res.moveToNext()) {
            String gpa = df2.format(Double.parseDouble(res.getString(2)));


            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("GPA: " + gpa + "\n");
            buffer.append("Hours: " + res.getString(3) + "\n\n");

            calcGrades=Double.parseDouble(res.getString(4));
            fHours=Integer.parseInt(res.getString(3));


        }



        //Toast.makeText(getApplicationContext(),buffer.toString(),Toast.LENGTH_LONG).show();
        data_btn.setMovementMethod(new ScrollingMovementMethod());
       data_btn.setText("");

        data_btn.setText(buffer.toString());


    }




}
