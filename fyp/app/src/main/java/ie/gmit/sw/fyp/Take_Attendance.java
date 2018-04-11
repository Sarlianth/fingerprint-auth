package ie.gmit.sw.fyp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Take_Attendance extends AppCompatActivity {

    RelativeLayout ray;
    String[] sid;
    String[] sname;
    String date,std;
    ListView list;
    TableRow tableRow;
    Take_Attendance_Adapter taa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_attendance);
        getSupportActionBar().setTitle("Attendence Sheet");
    }

    public class getstudents extends AsyncTask<String,JSONObject,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String out = "back";
            RestAPI api = new RestAPI();
            try {
                JSONObject json = api.getstudents(params[0],params[1]);
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
            if(s.compareTo("no")==0)
            {
                tableRow.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(ray,"No students added",Snackbar.LENGTH_SHORT);
                View vs = snackbar.getView();
                TextView txt = (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.parseColor("#FFFFFF"));
                snackbar.show();
            }
            else if(s.compareTo("already")==0)
            {
                Snackbar snackbar=Snackbar.make(ray,"Attendance already taken",Snackbar.LENGTH_LONG);
                View vs=snackbar.getView();
                TextView txt= (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.GREEN);
                snackbar.show();
            }
            else
            {
                if(s.contains("*"))
                {
                    String temp[] = s.split("\\#");
                    sid = new String[temp.length];
                    sname = new String[temp.length];
                    for(int i = 0; i < temp.length; i++)
                    {
                        String temp1[] = temp[i].split("\\*");
                        sid[i] = temp1[0];
                        sname[i] = temp1[1];
                    }
                    taa=new Take_Attendance_Adapter(Take_Attendance.this,sid,sname,date,std);
                    list.setAdapter(taa);
                    tableRow.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(Take_Attendance.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}