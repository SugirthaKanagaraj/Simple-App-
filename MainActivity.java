package com.example.calcapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView dis;
    double memory = 0;
    String input = "";
    String operator = "";
    double fnum=0;
    double grandTotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        dis=(TextView) findViewById(R.id.display);

        setNumberClick(R.id.btn0,"0");
        setNumberClick(R.id.btn00,"00");
        setNumberClick(R.id.btn1,"1");
        setNumberClick(R.id.btn2,"2");
        setNumberClick(R.id.btn3,"3");
        setNumberClick(R.id.btn4,"4");
        setNumberClick(R.id.btn5,"5");
        setNumberClick(R.id.btn6,"6");
        setNumberClick(R.id.btn7,"7");
        setNumberClick(R.id.btn8,"8");
        setNumberClick(R.id.btn9,"9");

        setOperatorClick(R.id.btnPlus,"+");
        setOperatorClick(R.id.btnMinus,"-");
        setOperatorClick(R.id.btnMul,"*");
        setOperatorClick(R.id.btnDiv,"/");
        setOperatorClick(R.id.btnRem,"%");
        setOperatorClick(R.id.btnPow,"^");

        Button btnEqual = (Button) findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(v->calculate());

        Button btnAC = (Button) findViewById(R.id.btnAC);
        btnAC.setOnClickListener(v->{
            input="";
            operator="";
            fnum=0;
            dis.setText(" ");
        });
        Button btnCE = (Button)findViewById(R.id.btnCE);
        btnCE.setOnClickListener(v->{
            if(input.length()>0){
                input=input.substring(0,input.length()-1);
                dis.setText(input);
            }
        });

    //Grand Total - GT
        Button btnGT = (Button) findViewById(R.id.btnGT);
        btnGT.setOnClickListener(v->{
            input=String.valueOf(grandTotal);
            dis.setText(input);
        });
    //Mark Up - MU
        Button btnMU = (Button) findViewById(R.id.btnMU);
        btnMU.setOnClickListener(v->{
            if (!input.isEmpty()){
                double val = Double.parseDouble(input);
                val = val + (val * 0.10);
                input = String.valueOf(val);
                dis.setText(input);
            }
        });
    //Memory Plus - M+
        Button btnMPlus = (Button) findViewById(R.id.btnMPlus);
        btnMPlus.setOnClickListener(v->{
            if(!input.isEmpty()){
                memory+= Double.parseDouble(input);
            }
        });
    //Memory Minus - M-
        Button btnMMinus = (Button)findViewById(R.id.btnMMinus);
        btnMMinus.setOnClickListener(v->{
            if(!input.isEmpty()){
                memory-=Double.parseDouble(input);
            }
        });
    //MRC
        Button btnMRC = (Button) findViewById(R.id.btnMRC);
        btnMRC.setOnClickListener(v->{
            input = String.valueOf(memory);
            dis.setText(input);
        });
    //Backspace - (->)
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            if(!input.isEmpty()){
                input = input.substring(0,input.length()-1);
                dis.setText(input);
            }
        });
    //Dot - (.)
        Button btnDot = (Button)findViewById(R.id.btnDot);
        btnDot.setOnClickListener(v->{
            if(!input.contains(".")){
                if(!input.isEmpty()){
                    input = "0.";
                }else{
                    input = ".";
                }
                dis.setText(input);
            }
        });
    }
    private void setNumberClick(int btnId, String num) {
        Button btn = findViewById(btnId);
        btn.setOnClickListener(v->{
            input+=num;
            dis.setText(input);
        });
    }
    private void setOperatorClick(int btnId, String op) {
        Button btn = findViewById(btnId);
        btn.setOnClickListener(v->{
            if (!input.isEmpty()){
                fnum=Double.parseDouble(input);
                operator=op;
                input="";
                dis.setText(op);
            }
        });
    }
    private void calculate() {
        if(!input.isEmpty() || !operator.isEmpty()){
            double snum = Double.parseDouble(input);
            double result = 0;
            switch (operator){
                case "+":result=fnum+snum; break;
                case "-":result=fnum-snum; break;
                case "*":result=fnum*snum; break;
                case "/":result=snum!=0 ? fnum/snum : 0; break;
                case "%":result=fnum%snum; break;
                case "^":result=Math.pow(fnum,snum); break;
            }
            grandTotal+=result;
            input=String.valueOf(result);
            dis.setText(input);
        }
    }

}