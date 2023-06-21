package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView problem;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problem = findViewById(R.id.problem_display);
        result = findViewById(R.id.result);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonBack = findViewById(R.id.buttonBackSpace);
        Button buttonAddition = findViewById(R.id.buttonAddition);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiplication = findViewById(R.id.buttonMultiplication);
        Button buttonDivision = findViewById(R.id.buttonDivision);
        Button buttonModulus = findViewById(R.id.buttonModulus);
        Button buttonPower = findViewById(R.id.buttonPower);
        Button buttonEqualTo = findViewById(R.id.buttonEqualTo);
        Button buttonDot = findViewById(R.id.buttonDot);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonModulus.setOnClickListener(this);
        buttonPower.setOnClickListener(this);
        buttonEqualTo.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public void btnClick(View view) {
        Button button = (Button) view;
        String existingText = problem.getText().toString();
        String btnText = button.getText().toString();

        if (btnText.equals("C")) {
            problem.setText("0");
            result.setText("");
        } else if (btnText.equals("=")) {
            String calculatedResult = calculation(existingText);
            problem.setText(calculatedResult);
            result.setText("");
        } else if (btnText.equals("b")) {
            if (existingText.length() > 0) {
                existingText = existingText.substring(0, existingText.length() - 1);
                problem.setText(existingText);
            }
        } else {
            if (existingText.equals("0")) {
                existingText = btnText;
            } else {
                existingText = existingText + btnText;
            }
            problem.setText(existingText);
            result.setText(calculation(existingText));
        }
    }


    public String calculation(String data) {
        ArrayList<String> tokens = new ArrayList<>();
        String[] arr = data.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        for (String str : arr) {
            if (!str.trim().isEmpty()) {
                tokens.add(str.trim());
            }
        }

        if (tokens.isEmpty()) {
            return "";
        }

        try {
            double result = Double.parseDouble(tokens.get(0));

            for (int i = 1; i < tokens.size(); i += 2) {
                String operator = tokens.get(i);
                double operand = Double.parseDouble(tokens.get(i + 1));

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        if (operand != 0) {
                            result /= operand;
                        } else {
                            return "Error: Division by zero";
                        }
                        break;
                    case "%":
                        result %= operand;
                        break;
                    case "^":
                        result = Math.pow(result, operand);
                        break;
                }
            }

            if (result == (int) result) {
                return String.valueOf((int) result);
            } else {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return "Error: Invalid expression";
        }
    }

    @Override
    public void onClick(View view) {
        btnClick(view);
    }
}
