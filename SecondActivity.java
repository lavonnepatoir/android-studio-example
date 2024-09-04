package com.example.homework8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private double exchangeRate = 0.0;
    private String selectedCurrency;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText usdInput = findViewById(R.id.usdInput);
        EditText exchangeInput = findViewById(R.id.exchangeInput);
        EditText exchangeEditText = findViewById(R.id.exchangeEditText);
        Button fromUSDButton = findViewById(R.id.fromUSDButton);
        Button toUSDButton = findViewById(R.id.toUSDButton);
        Button returnButton = findViewById(R.id.returnButton);

        selectedCurrency = getIntent().getStringExtra("selectedCurrency");

        //switch case to convert to selected currency

        switch (selectedCurrency) {
            case "YEN":
                exchangeRate = 109.94;
                break;
            case "CAD":
                exchangeRate = 1.26;
                break;
            case "EUR":
                exchangeRate = 0.85;
                break;
            default:
                exchangeRate = 1.0;
                break;
        }

        fromUSDButton.setText("Convert to " + selectedCurrency);
        toUSDButton.setText("Convert to USD");

        fromUSDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usdAmountStr = usdInput.getText().toString();
                if (!usdAmountStr.isEmpty()) {
                    double usdAmount = Double.parseDouble(usdAmountStr);
                    double convertedAmount = usdAmount * exchangeRate;
                    String formattedAmount = decimalFormat.format(convertedAmount);
                    exchangeEditText.setText(String.valueOf(formattedAmount));
                }
                // Clear the other input field
                exchangeInput.setText("");
            }
        });

        toUSDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otherCurrencyAmountStr = exchangeInput.getText().toString();
                if (!otherCurrencyAmountStr.isEmpty()) {
                    double otherCurrencyAmount = Double.parseDouble(otherCurrencyAmountStr);
                    double convertedAmount;

                    // switch case to convert back to usd
                    switch (selectedCurrency) {
                        case "YEN":
                            convertedAmount = otherCurrencyAmount / 109.94;
                            break;
                        case "CAD":
                            convertedAmount = otherCurrencyAmount / 1.26;
                            break;
                        case "EUR":
                            convertedAmount = otherCurrencyAmount / 0.85;
                            break;
                        default:
                            convertedAmount = otherCurrencyAmount;
                            break;
                    }
                    String formattedAmount = decimalFormat.format(convertedAmount);
                    exchangeEditText.setText(String.valueOf(formattedAmount));
                }
                // clear the other text field
                usdInput.setText("");
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
