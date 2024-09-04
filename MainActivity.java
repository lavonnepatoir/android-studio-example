package com.example.homework8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.mainRadioGroup);
        next = findViewById(R.id.nextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedCurrency = selectedRadioButton.getText().toString();

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("selectedCurrency", selectedCurrency);
                    startActivity(intent);
                }
            }
        });
    }
}
