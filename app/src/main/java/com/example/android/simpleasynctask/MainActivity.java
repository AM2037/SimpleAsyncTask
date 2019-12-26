package com.example.android.simpleasynctask;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Key for saving state of TextView
    private static final String TEXT_STATE = "currentText";
    private static final String TEXT_PROGRESS = "currentSleep";

    // The TextView to show the results from background thread processes
    private TextView mTextView = null;
    private TextView mResults;

    /**
     * Initializes activity.
     *
     * @param savedInstanceState The current state data
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextView
        mTextView = findViewById(R.id.textView1);
        mResults = findViewById(R.id.textView2);

        // Restore TextView if there's a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    /**
     * Handles onClick for "Start Task" button. Launches AsyncTask which performs
     * work off the main UI thread.
     *
     * @param view The view (Button) that was clicked.
     */
    public void startTask(View view) {
        // Put message in the TextView
        mTextView.setText(R.string.napping);

        // Create instance of SimpleAsyncTask, passing mTextView to constructor
        // Start the AsyncTask which has callback to update TextView
        new SimpleAsyncTask(mTextView, mResults).execute();
    }

    /**
     * Saves the contents of the TextView to restore on configuration changes such as
     * rotating the device.
     *
     * @param outState The bundle in which the state of the activity is stored when it's
     *                 spontaneously destroyed.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state of TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS, mResults.getText().toString());
    }
}
