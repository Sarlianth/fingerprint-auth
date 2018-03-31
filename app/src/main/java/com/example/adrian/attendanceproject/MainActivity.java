package com.example.adrian.attendanceproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    private TextView mResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not done yet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mResult = (TextView) findViewById(R.id.tv_result);

        // GET request test
        //new getDataTask().execute("http://192.168.8.103:1000/api/status");

        // POST request
        //new postDataTask().execute("http://192.168.8.103:1000/api/status");

        // PUT request
        new putDataTask().execute("http://192.168.8.103:1000/api/status/5abfc61275fd281c10c6a313");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class getDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading data..");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                return getData(params[0]);
            }catch(IOException ex){
                return "Network error!";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // set response to textview
            mResult.setText(result);

            // get rid of the progress dialog
            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }

        private String getData(String urlPath) throws IOException{
            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;

            try{
                //Initializze and config request, and connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000); // in milliseconds
                urlConnection.setConnectTimeout(10000); // in milliseconds
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json"); // header
                urlConnection.connect();

                // Read back response from the server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result.append(line).append("\n");
                }


            }  finally {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }

            return result.toString();
        }
    }

    class postDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Inserting data..");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                return postData(params[0]);
            }catch(IOException ex){
                return "Network error";
            }catch(JSONException ex){
                return "Data invalid";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }

        private String postData(String urlPath) throws IOException, JSONException{

            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;

            try{
                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("fbname", "Mariancki");
                dataToSend.put("content", "Please work..");
                dataToSend.put("likes", 2);
                dataToSend.put("comments", 4);


                //Initialize and config request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000); // in milliseconds
                urlConnection.setConnectTimeout(10000); // in milliseconds
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true); //enable output
                urlConnection.setRequestProperty("Content-Type", "application/json"); // header
                urlConnection.connect();

                //Data to server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Read response from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    result.append(line).append(("\n"));
                }
            }finally{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }
            return result.toString();
        }
    }

    class putDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Updating data");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                return putData(params[0]);
            }catch(IOException ex){
                return "Network Error";
            }catch(JSONException ex){
                return "Data error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if(progressDialog != null){
                progressDialog.dismiss();
            }
        }

        private String putData(String urlPath) throws IOException, JSONException {

            String result = null;
            BufferedWriter bufferedWriter = null;

            try{

                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("fbname", "Mariancki CHANGED");
                dataToSend.put("content", "Please work.. CHANGE!!");
                dataToSend.put("likes", 12);
                dataToSend.put("comments", 43);

                //Initialize and config request
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000); // in milliseconds
                urlConnection.setConnectTimeout(10000); // in milliseconds
                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoOutput(true); //enable output
                urlConnection.setRequestProperty("Content-Type", "application/json"); // header
                urlConnection.connect();

                //Data to server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Check if update is successful
                if(urlConnection.getResponseCode() == 200){
                    return "Update successfully";
                }
                else{
                    return "Update failed";
                }
            }finally{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }
        }
    }
}
