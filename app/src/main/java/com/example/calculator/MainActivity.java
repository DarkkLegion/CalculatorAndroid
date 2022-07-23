package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private boolean ifEquals = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MainActivity.this.getString(R.string.textbox).equals(display.getText().toString())) {
                    display.setText("");
                }
                return false;
            }
        });
    }

    //update the text when value is entered, includes inserting values anywhere in the text
    public void updateText(String strToAdd){
        String oldText = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftstr = oldText.substring(0, cursorPos);
        String rigthstr = oldText.substring(cursorPos);
        if(getString(R.string.textbox).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftstr,strToAdd,rigthstr));
        }
        display.setSelection(cursorPos+1);
    }
    //buttons 1-9 and other
    public void zeroBTN(View view){updateText("0");}
    public void oneBTN(View view){updateText("1");}
    public void twoBTN(View view){updateText("2");}
    public void threeBTN(View view){updateText("3");}
    public void fourBTN(View view){
        updateText("4");
    }
    public void fiveBTN(View view){
        updateText("5");
    }
    public void sixBTN(View view){
        updateText("6");
    }
    public void sevenBTN(View view){
        updateText("7");
    }
    public void eightBTN(View view){
        updateText("8");
    }
    public void nineBTN(View view){
        updateText("9");
    }
    public void dotBTN(View view){
        updateText(".");
    }
    public void backBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }
    public void divBTN(View view){
        updateText("÷");
    }
    public void mulBTN(View view){
        updateText("×");
    }
    public void addBTN(View view){
        updateText("+");
    }
    public void subBTN(View view){
        updateText("-");
    }
    public void powBTN(View view){
        updateText("^");
    }
    public void paraBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for(int i=0; i<cursorPos; i++){
            if(display.getText().toString().charAt(i) == '('){
                openPar+=1;
            }
            if(display.getText().toString().charAt(i) == ')'){
                closedPar+=1;
            }
        }
        if(openPar==closedPar || display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
            display.setSelection(cursorPos+1);
        }
        else if(closedPar < openPar && display.getText().toString().charAt(textLen - 1) != '('){
            updateText(")");
            display.setSelection(cursorPos+1);
        }
    }
    public void acBTN(View view){
        display.setText("");
    }//all clear
    //equals button links to computeFunctions.java
    public void equalBTN(View view){
        String equation = display.getText().toString();
        equation = equation.replace("×","*");
        equation = equation.replace("÷","/");
        double result = computeFunctions.evaluate(equation);
        String s = Double.toString(result);
        display.setText(s);
        display.setSelection(s.length());
        ifEquals=true;
    }
}