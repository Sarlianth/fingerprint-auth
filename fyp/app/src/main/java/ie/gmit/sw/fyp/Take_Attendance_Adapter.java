package ie.gmit.sw.fyp;

import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

public class Take_Attendance_Adapter extends ArrayAdapter<String> {

    String[] status;
    String[] sid,sname;
    String date,std;
    Context con;
    int total = 0;
    TableLayout tablay;
    Boolean empty=false;
    Dialog gd;

    public Take_Attendance_Adapter(Context context, String[] sid, String sname[], String date, String std)
    {
        super(context, R.layout.take_attendance_listitems,sid);
        con=context;
        this.sid=sid;
        this.sname=sname;
        this.date=date;
        this.std=std;
        total=sid.length;
        status=new String[total];
    }
}
