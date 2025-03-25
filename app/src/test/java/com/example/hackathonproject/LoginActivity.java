package com.example.hackathonproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText aadharEditText, otpEditText;
    private Button requestOtpButton, verifyOtpButton;

    private String generatedOtp; // Stores the OTP after request

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // ✅ Ensure XML file exists

        // Initialize UI components
        aadharEditText = findViewById(R.id.aadharEditText);
        otpEditText = findViewById(R.id.otpEditText);
        requestOtpButton = findViewById(R.id.requestOtpButton);
        verifyOtpButton = findViewById(R.id.verifyOtpButton);

        // Request OTP Button Click Event
        requestOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aadharNumber = aadharEditText.getText().toString().trim();

                if (!isValidAadhar(aadharNumber)) {
                    Toast.makeText(LoginActivity.this, "Enter a valid Aadhar number", Toast.LENGTH_SHORT).show();
                } else {
                    // Simulate OTP generation (In a real app, OTP should be sent via API)
                    generatedOtp = generateOtp();
                    Toast.makeText(LoginActivity.this, "OTP Sent: " + generatedOtp, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Verify OTP Button Click Event
        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = otpEditText.getText().toString().trim();

                if (generatedOtp == null) {
                    Toast.makeText(LoginActivity.this, "Request OTP first", Toast.LENGTH_SHORT).show();
                } else if (!enteredOtp.equals(generatedOtp)) {
                    Toast.makeText(LoginActivity.this, "Invalid OTP. Try again!", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to MainActivity after OTP verification
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    // Function to validate Aadhar number format
    private boolean isValidAadhar(String aadhar) {
        return aadhar.length() == 12 && aadhar.matches("\\d{12}");
    }

    // Function to generate a 6-digit OTP
    private String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000; // Generates a random 6-digit number
        return String.valueOf(otp);
    }
}
