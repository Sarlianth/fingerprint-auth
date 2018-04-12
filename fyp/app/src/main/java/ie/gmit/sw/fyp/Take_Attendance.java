package ie.gmit.sw.fyp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Take_Attendance extends AppCompatActivity {
    RelativeLayout layout;
    Button submit, clear;
    ListView list;
    TextView classDiv, dateView;
    String std, date;
    String[] studentName;
    String[] sid;
    Take_Attendance_Adapter taa;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dt;
    TableRow tableRow;
    Dialog d, gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_attendance);
        getSupportActionBar().setTitle("Attendance Sheet");
        classDiv = (TextView) findViewById(R.id.classdiv);
        dateView = (TextView) findViewById(R.id.date);
        layout = (RelativeLayout) findViewById(R.id.taray);
        tableRow = (TableRow) findViewById(R.id.tbbtns);
        dt = new Date();
        date = sdf.format(dt.getTime());

        Intent i = getIntent();
        std = i.getStringExtra("class");

        String s = "<b>Class : " + "</b>" + std;
        classDiv.setText(Html.fromHtml(s));
        String s1 = "<b>Date : </b>" + date;
        dateView.setText(Html.fromHtml(s1));

        list = (ListView) findViewById(R.id.tattend_list);
        submit = (Button) findViewById(R.id.tasubmit);
        clear = (Button) findViewById(R.id.taclear);

        new getstudents().execute(std, date);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //suppose we return data from adapter
                final String check = taa.check();
                if (check.contains("*")) {
                    d = new Dialog(Take_Attendance.this);
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    d.setContentView(R.layout.attendance_confirm_dialog);
                    d.setCancelable(false);
                    TextView totalt = (TextView) d.findViewById(R.id.acd_tot);
                    TextView presentt = (TextView) d.findViewById(R.id.acd_present);
                    TextView absentt = (TextView) d.findViewById(R.id.acd_ab);
                    TextView sickt = (TextView) d.findViewById(R.id.acd_sick);
                    TextView latet = (TextView) d.findViewById(R.id.acd_late);

                    String temp[] = check.split("\\*");
                    String t = "<b>Total : </b>" + temp[0];
                    totalt.setText(Html.fromHtml(t));
                    presentt.setText(temp[1]);
                    absentt.setText(temp[2]);
                    sickt.setText(temp[3]);
                    latet.setText(temp[4]);

                    Button subbtn, canbtn;
                    subbtn = (Button) d.findViewById(R.id.acd_confirm);
                    canbtn = (Button) d.findViewById(R.id.acd_can);

                    subbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Suppose we return data from adapter
                            String ans = taa.getData();
                            String temp[] = ans.split("\\#");
                            new submitattendance().execute(temp[0], temp[1], temp[2], temp[3]);
                        }
                    });

                    canbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            d.cancel();
                        }
                    });
                    d.show();
                } else {
                    list.setSelection(Integer.parseInt(check));
                    Snackbar snackbar = Snackbar.make(layout, "Fill for all the students", Snackbar.LENGTH_SHORT);
                    View vs = snackbar.getView();
                    TextView txt = (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                    txt.setTextColor(Color.parseColor("#d66e0a"));
                    snackbar.show();
                }
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taa = new Take_Attendance_Adapter(Take_Attendance.this, sid, studentName, date, std);
                list.setAdapter(taa);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = new MenuInflater(Take_Attendance.this);
        mi.inflate(R.menu.menuoptions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class getstudents extends AsyncTask<String, JSONObject, String> {

        @Override
        protected String doInBackground(String... params) {
            String out = "back";
            RestAPI api = new RestAPI();
            try {
                JSONObject json = api.getstudents(params[0], params[1]);
                JSONParse parse = new JSONParse();
                out = parse.mainparse(json);
            } catch (Exception e) {
                out = e.getMessage();
            }
            return out;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.compareTo("no") == 0) {
                tableRow.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(layout, "No students added", Snackbar.LENGTH_SHORT);
                View vs = snackbar.getView();
                TextView txt = (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.parseColor("#FFFFFF"));
                snackbar.show();
            } else if (s.compareTo("already") == 0) {
                Snackbar snackbar = Snackbar.make(layout, "Attendance already taken", Snackbar.LENGTH_LONG);
                View vs = snackbar.getView();
                TextView txt = (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.GREEN);
                snackbar.show();
            } else {
                if (s.contains("*")) {
                    String temp[] = s.split("\\#");
                    sid = new String[temp.length];
                    studentName = new String[temp.length];
                    for (int i = 0; i < temp.length; i++) {
                        String temp1[] = temp[i].split("\\*");
                        sid[i] = temp1[0];
                        studentName[i] = temp1[1];
                    }
                    taa = new Take_Attendance_Adapter(Take_Attendance.this, sid, studentName, date, std);
                    list.setAdapter(taa);
                    tableRow.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(Take_Attendance.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class submitattendance extends AsyncTask<String, JSONObject, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            submit.setEnabled(false);
            clear.setEnabled(false);
            dailog();
            d.cancel();
        }

        @Override
        protected String doInBackground(String... params) {
            String a = "back";
            RestAPI api = new RestAPI();
            try {
                JSONObject json = api.attendence(params[0], params[1], params[2], params[3]);
                JSONParse jp = new JSONParse();
                a = jp.mainparse(json);
            } catch (Exception e) {
                a = e.getMessage();
            }
            return a;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.compareTo("true") == 0) {
                gd.cancel();
                Snackbar snack = Snackbar.make(list, "Attendance Taken..Thank You!", Snackbar.LENGTH_INDEFINITE);
                View vs = snack.getView();
                TextView txt = (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.GREEN);
                snack.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
            } else {
                gd.cancel();
                submit.setEnabled(true);
                clear.setEnabled(true);
                Toast.makeText(Take_Attendance.this, s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void dailog() {
        gd = new Dialog(Take_Attendance.this, R.style.DTheme);
        gd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gd.setContentView(R.layout.loading_dialog);
        gd.setCancelable(false);
        gd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        gd.show();
    }
}