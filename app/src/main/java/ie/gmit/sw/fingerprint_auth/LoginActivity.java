package ie.gmit.sw.fingerprint_auth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView myResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        // Show the result in the TextView
        myResult = (TextView) findViewById(R.id.tv_results);

        // Make GET request
        // The "http://172.22.205.209" IP Address should be changed accordingly to the server public IP Address
        // The current IP address is of a local machine where the test is carried out
        //new getDataTask().execute("http://172.22.205.209:1000/api/status");

        // Make POST request
        //new postDataTask().execute("http://172.22.205.209:1000/api/status");

        // Make PUT request
        new putDataTask().execute("http://172.22.205.209:1000/api/status/5aa8542a571f4b0ccc1c1e0d");
    }// End of onCreate

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
            Intent i = new Intent(this, PostActivity.class);
            startActivity(i);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    //
    class getDataTask extends AsyncTask<String, Void, String>{
        // Local variable
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Lading data...");
            progressDialog.show();
        }// End of onPreExecute

        @Override
        protected String doInBackground(String... params) {
            // Try to execute/ do work
            try{
                // Return result string
                return getData(params[0]);
            }// End of try

            // Catch the exception
            catch(IOException ex){
                // Return informative error message
                return "Network Error!";
            }// End of catch
        }// End of doInBackground

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Set data response to TextView
            myResult.setText(result);

            // Cancel progress dialog
            if(progressDialog != null){
                progressDialog.dismiss();
            }// End of if
        }// End of onPostExecute

        private String getData(String urlPath) throws IOException{
            // Declaration of result string builder
            StringBuilder result = new StringBuilder();
            BufferedReader br = null;

            // Try to execute/ do work
            try{
                // Initialize and config request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                // Set read & connection timeout in milliseconds
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("GET");
                // Set header
                urlConnection.setRequestProperty("Content-Type", "application/jason");
                // Connect to the server
                urlConnection.connect();

                // Read data response from server
                InputStream inputStream = urlConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                // While input stream is not empty
                while((line = br.readLine()) != null){
                    // Append the result string
                    result.append(line).append("\n");
                }// End of while
            }// End of try

            // Finally when work is done
            finally{
                // And buffered reader is not empty
                if(br != null){
                    // Close the resource stream
                    br.close();
                }// End of if
            }// End of finally

            // Return result string
            return myResult.toString();
        }// End of getData
    }// End of GetDataTask

    class postDataTask extends AsyncTask<String, Void, String>{
        // Local variable
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Inserting data...");
            progressDialog.show();
        }// End of onPreExecute

        @Override
        protected String doInBackground(String... params) {
            // Try to execute/ do work
            try{
                // Return result string
                return postData(params[0]);
            }// End of try

            // Catch the exceptions
            catch(IOException ex){
                // Return informative error message
                return "Network Error!";
            }// End of catch
            catch(JSONException ex){
                return "Invalid data!";
            }// End of catch
        }// End of doInBackground

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Set data response to TextView
            myResult.setText(result);

            // Cancel progress dialog
            if(progressDialog != null){
                progressDialog.dismiss();
            }// End of if
        }// End of onPostExecute

        private String postData(String urlPath) throws IOException, JSONException{
            // Declaration of result string builder
            StringBuilder result = new StringBuilder();
            BufferedWriter bw = null;
            BufferedReader br = null;

            // Try to execute/ do work
            try {
                // Creation of data to send to the server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("fbname", "Testing SEND");
                dataToSend.put("content", "Sending JSON object");
                dataToSend.put("likes", 222);
                dataToSend.put("comments", 2);

                // Initialize and config request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                // Set read & connection timeout in milliseconds
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("POST");
                // Enable output (Body data)
                urlConnection.setDoOutput(true);
                // Set header
                urlConnection.setRequestProperty("Content-Type", "application/jason");
                // Connect to the server
                urlConnection.connect();

                // Write data onto the server
                OutputStream outputStream = urlConnection.getOutputStream();
                bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                // Write data into a string
                bw.write(dataToSend.toString());
                // Flush the output stream
                bw.flush();

                // Read data response from the server
                InputStream inputStream = urlConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                // While input stream is not empty
                while ((line = br.readLine()) != null) {
                    // Append the result string
                    result.append(line).append("\n");
                }// End of while
            }// End of try

            // Finally when work is done
            finally{
                // And buffered readers are not empty
                if(bw != null){
                    // Close the resource stream
                    bw.close();
                }// End of if

                if(br != null){
                    // Close the resource stream
                    br.close();
                }// End of if
            }// End of finally

            // Return result string
            return result.toString();
        }// End of postData
    }// End of postDataTask

    class putDataTask extends AsyncTask<String, Void, String>{
        // Local variable
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Updating data...");
            progressDialog.show();
        }// End of onPreExecute

        @Override
        protected String doInBackground(String... params) {
            // Try to execute/ do work
            try{
                // Return result string
                return putData(params[0]);
            }// End of try

            // Catch the exceptions
            catch(IOException ex){
                // Return informative error message
                return "Network Error!";
            }// End of catch
            catch(JSONException ex){
                return "Invalid data!";
            }// End of catch
        }// End of doInBackground

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Set data response to TextView
            myResult.setText(result);

            // Cancel progress dialog
            if(progressDialog != null){
                progressDialog.dismiss();
            }// End of if
        }// End of onPostExecute

        private String putData(String urlPath) throws IOException, JSONException{
            // Declaration of result string builder
            String result = null;
            BufferedWriter bw = null;

            // Try to execute/ do work
            try{
                // Create data to update
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("fbname", "Testing PUT");
                dataToSend.put("content", "Updating content");
                dataToSend.put("likes", 333);
                dataToSend.put("comments", 3);

                // Initialize and config request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                // Set read & connection timeout in milliseconds
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod("PUT");
                // Enable output (Body data)
                urlConnection.setDoOutput(true);
                // Set header
                urlConnection.setRequestProperty("Content-Type", "application/jason");
                // Connect to the server
                urlConnection.connect();

                // Write data onto the server
                OutputStream outputStream = urlConnection.getOutputStream();
                bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                // Write data into a string
                bw.write(dataToSend.toString());
                // Flush the output stream
                bw.flush();

                // When connection successful
                if(urlConnection.getResponseCode() == 200){
                    // Return successful message
                    return "Update Successful!";
                }// End of if

                // When connection unsuccessful
                else{
                    // Return unsuccessful message
                    return "Update Unsuccessful!";
                }// End of else
            }// End of try

            // Finally when work is done
            finally{
                // And buffered readers are not empty
                if(bw != null){
                    // Close the resource stream
                    bw.close();
                }// End of if
            }// End of finally
        }// End of putData
    }// End of putDataTask

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

