package com.example.votingapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class VotingActivity extends AppCompatActivity {

    private CardView candidate1, candidate2, candidate3;
    private LinearLayout selectedCandidate = null; // Stores the selected candidate view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting); // Ensure this matches your XML file name

        // Find Views
        candidate1 = findViewById(R.id.cardCandidate1);
        candidate2 = findViewById(R.id.cardCandidate2);
        candidate3 = findViewById(R.id.cardCandidate3);

        // Click Listeners for Candidates
        candidate1.setOnClickListener(view -> selectCandidate(view, "John Smith"));
        candidate2.setOnClickListener(view -> selectCandidate(view, "Emily Johnson"));
        candidate3.setOnClickListener(view -> selectCandidate(view, "Michael Lee"));
    }

    // Handles Candidate Selection and Shows Popup
    private void selectCandidate(View view, String candidateName) {
        if (selectedCandidate != null) {
            selectedCandidate.setBackgroundColor(getResources().getColor(android.R.color.white));
        }

        selectedCandidate = (LinearLayout) view;
        selectedCandidate.setBackgroundColor(getResources().getColor(R.color.light_gray));

        showVoteConfirmation(candidateName);
    }

    // Shows the Vote Confirmation Popup
    private void showVoteConfirmation(String candidateName) {
        new AlertDialog.Builder(this)
                .setTitle("Vote Confirmation")
                .setMessage("✅ You have successfully voted for " + candidateName)
                .setPositiveButton("OK", (dialog, which) -> Toast.makeText(VotingActivity.this, "Vote Recorded!", Toast.LENGTH_SHORT).show())
                .setCancelable(false)
                .show();
    }
}
