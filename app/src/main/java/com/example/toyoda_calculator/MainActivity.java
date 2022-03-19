package com.example.toyoda_calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //EditText
        input = findViewById(R.id.input);
        input.setShowSoftInputOnFocus(false);

        //OnClickListener
        input.setOnClickListener(view -> {
            if (getString(R.string.input).equals(input.getText().toString())) {
                input.setText("");
            }
        });
    }

    private void display(String newString) {
        String oldString = input.getText().toString();
        int pos = input.getSelectionStart();
        String left = oldString.substring(0, pos);
        String right = oldString.substring(pos);
        if (getString(R.string.input).equals(input.getText().toString())) {
            input.setText(newString);
        }
        else {
            input.setText(String.format("%s%s%s", left, newString, right));
        }
        input.setSelection(pos + 1);
    }

    // display number
    public void zero(View view) {
        display("0");
    }

    public void one(View view) {
        display("1");
    }

    public void two(View view) {
        display("2");
    }

    public void three(View view) {
        display("3");
    }

    public void four(View view) {
        display("4");
    }

    public void five(View view) {
        display("5");
    }

    public void six(View view) {
        display("6");
    }

    public void seven(View view) {
        display("7");
    }

    public void eight(View view) {
        display("8");
    }

    public void nine(View view) {
        display("9");
    }

    public void decimal(View view) {
        display(".");
    }

    public void equal(View view) {
        String xp = input.getText().toString();

        xp = xp.replaceAll("÷", "/"); // replaces symbol to what java can understand
        xp = xp.replaceAll("×", "*"); // replaces symbol to what java can understand

        Expression exp = new Expression(xp);

        String answer = String.valueOf(exp.calculate()); //uses mx parser to calculate

        input.setText(answer);
        input.setSelection(answer.length());
    }

    public void add(View view) {
        display("+");
    }

    public void subtract(View view) {
        display("-");
    }

    public void multiply(View view) {
        display("×");
    }

    public void divide(View view) {
        display("÷");
    }

    public void openParenthesis(View view) {
        display("(");
    }

    public void closeParenthesis(View view) {
        display(")");
    }

    public void percent(View view) {
        display("%");
    }

    public void clear(View view) {
        input.setText("");
    }

    public void backspace(View view) {
        int pos = input.getSelectionStart();
        int length = input.getText().length();

        if (pos != 0 && length != 0) { //checks if there is any symbols to delete
            SpannableStringBuilder selection = (SpannableStringBuilder) input.getText();
            selection.replace(pos - 1, pos, "");
            input.setText(selection);
            input.setSelection(pos - 1);
        }
    }
}