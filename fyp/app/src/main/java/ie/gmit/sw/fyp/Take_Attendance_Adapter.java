package ie.gmit.sw.fyp;

import android.content.Context;
import android.widget.ArrayAdapter;

public class Take_Attendance_Adapter extends ArrayAdapter<String> {

    String[] status;
    String[] sid, studentName;
    String date, std;
    Context con;
    int total = 0;

    public Take_Attendance_Adapter(Context context, String[] sid, String studentName[], String date, String std) {
        super(context, R.layout.take_attendance_listitems, sid);
        con = context;
        this.sid = sid;
        this.studentName = studentName;
        this.date = date;
        this.std = std;
        total = sid.length;
        status = new String[total];
    }

    public String getData() {
        return null;
    }

    public String check() {
        return null;
    }
}
