package com.yourpackage.votingapp;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FaceAuthActivity extends AppCompatActivity {

    private Button btnVerifyIdentity;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_auth_activity);

        btnVerifyIdentity = findViewById(R.id.btnVerifyIdentity);

        // Executor for authentication
        Executor executor = Executors.newSingleThreadExecutor();

        // Set up biometric prompt
        biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Face Authentication Required")
                .setSubtitle("Please scan your face to continue")
                .setDescription("For security, ensure proper lighting and alignment with the frame.")
                .setNegativeButton("Cancel", executor, (dialogInterface, i) ->
                        Toast.makeText(FaceAuthActivity.this, "Authentication Cancelled", Toast.LENGTH_SHORT).show()
                ).build();

        // Button click to start face authentication
        btnVerifyIdentity.setOnClickListener(view -> {
            biometricPrompt.authenticate(new BiometricPrompt.CryptoObject(null), new BiometricPrompt.CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(FaceAuthActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();

                    // Navigate to Voting Page
                    startActivity(new Intent(FaceAuthActivity.this, VotingActivity.class));
                    finish();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(FaceAuthActivity.this, "Authentication Failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
