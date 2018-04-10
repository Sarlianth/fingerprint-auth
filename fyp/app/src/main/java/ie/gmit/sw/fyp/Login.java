package ie.gmit.sw.fyp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    CoordinatorLayout cord;
    EditText user, pass;
    Button submit;
    Dialog gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.btn);
        cord = (CoordinatorLayout) findViewById(R.id.coordinator);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().compareTo("")!=0 || pass.getText().toString().compareTo("")!=0)
                {
                    if(user.getText().toString().compareTo("")!=0)
                    {
                        if(pass.getText().toString().compareTo("")!=0)
                        {
                            new login().execute(user.getText().toString(),pass.getText().toString());
                        }
                        else
                        {
                            pass.setError("Enter password");
                            pass.requestFocus();
                        }
                    }
                    else
                    {
                        user.setError("Enter username");
                        user.requestFocus();
                    }
                }
                else
                {
                    Snackbar snackbar=Snackbar.make(cord,"Enter user ID & password to login!",Snackbar.LENGTH_SHORT);
                    View vs=snackbar.getView();
                    TextView txt= (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                    txt.setTextColor(Color.RED);
                    snackbar.show();
                    user.requestFocus();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        user.setText("");
        pass.setText("");
        user.requestFocus();
    }

    public class login extends AsyncTask<String,JSONObject,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog();
        }

        @Override
        protected String doInBackground(String... params) {
            String out = "back";
            RestAPI api = new RestAPI();
            try {
                JSONObject json = api.login(params[0],params[1]);
                JSONParse parsed = new JSONParse();
                out = parsed.mainparse(json);
            } catch (Exception e) {
                out = e.getMessage();
            }
            return out;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.compareTo("false")==0)
            {
                gd.cancel();
                pass.setText("");
                user.setText("");
                Snackbar snackbar=Snackbar.make(cord,"Invalid username or password",Snackbar.LENGTH_SHORT);
                View vs=snackbar.getView();
                TextView txt= (TextView) vs.findViewById(android.support.design.R.id.snackbar_text);
                txt.setTextColor(Color.RED);
                snackbar.show();
                user.requestFocus();
            }
            else
            {
                if(s.contains("*"))
                {
                    String temp[] = s.split("\\*");
                    Intent i=new Intent(Login.this,Take_Attendance.class);
                    i.putExtra("class",temp[1]);
                    startActivity(i);
                    gd.cancel();
                }
                else
                {
                    gd.cancel();
                    Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void dialog()
    {
        gd=new Dialog(Login.this,R.style.DTheme);
        gd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gd.setContentView(R.layout.loading_dialog);
        gd.setCancelable(false);
        gd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        gd.show();
    }
}
