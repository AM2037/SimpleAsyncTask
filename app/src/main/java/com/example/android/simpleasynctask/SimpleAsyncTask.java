package com.example.android.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    // Use WeakReference to prevent memory leak
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResults;

    SimpleAsyncTask(TextView tv, TextView time) {
        mTextView = new WeakReference<>(tv);
        mResults = new WeakReference<>(time);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make task take long enough to rotate the phone while running
        int s = n * 200;
        publishProgress(s);

        // Sleep for random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mResults.get().setText("Current time elapsed: " + values[0] + " ms");
    }

    /**
     * Takes a string argument and displays that in a TextView -- updates UI on Main Thread.
     *
     * @param result the TextView displaying the String argument.
     *               3rd parameter in AsyncTask method/what doInBackground() returns.
     *               <p>
     *               Need to use get() since mTextView is a WeakReference.
     */
    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
