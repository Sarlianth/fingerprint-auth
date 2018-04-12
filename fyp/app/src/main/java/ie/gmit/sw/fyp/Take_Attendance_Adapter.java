package ie.gmit.sw.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Take_Attendance_Adapter extends ArrayAdapter<String> {

    String[] status;
    String[] sid, studentName;
    String date, std;
    Context con;
    int total = 0;
    TableLayout tableLayout;
    Boolean empty = false;

    public Take_Attendance_Adapter(Context context, String[] studentID, String studentName[], String date, String std) {
        super(context, R.layout.take_attendance_listitems, studentID);
        con = context;
        this.sid = studentID;
        this.studentName = studentName;
        this.date = date;
        this.std = std;
        total = studentID.length;
        status = new String[total];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View view = layoutInflater.inflate(R.layout.take_attendance_listitems, null, true);
        tableLayout = (TableLayout) view.findViewById(R.id.tablay);
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg);
        RadioButton p, a, s, l;
        p = (RadioButton) view.findViewById(R.id.present);
        a = (RadioButton) view.findViewById(R.id.absent);
        s = (RadioButton) view.findViewById(R.id.sick);
        l = (RadioButton) view.findViewById(R.id.late);

        try {
            if (status[position] == null) {
                // do nothing
            }

            else {
                if (status[position].compareTo("Absent") == 0) {
                    a.setChecked(true);
                } else if (status[position].compareTo("Sick") == 0) {
                    s.setChecked(true);
                } else if (status[position].compareTo("Late") == 0) {
                    l.setChecked(true);
                } else if (status[position].compareTo("Present") == 0) {
                    p.setChecked(true);
                }
            }
        } catch (Exception e) {
            Toast.makeText(con, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        TextView name = (TextView) view.findViewById(R.id.taname);
        name.setText(studentName[position]);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton r = (RadioButton) group.findViewById(checkedId);
                status[position] = r.getText().toString();

            }
        });

        return view;
    }

    public String check() {
        empty = false;
        String checked = "";
        int p = 0, a = 0, s = 0, l = 0;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == null) {
                checked = "" + i;
                return checked;
            } else if (status[i].compareTo("Absent") == 0) {
                a++;
            } else if (status[i].compareTo("Sick") == 0) {
                s++;
            } else if (status[i].compareTo("Late") == 0) {
                l++;
            } else if (status[i].compareTo("Present") == 0) {
                p++;
            }
        }
        checked = status.length + "*" + p + "*" + a + "*" + s + "*" + l;
        return checked;
    }

    public String getData() {
        String id = "", status = "";
        for (int i = 0; i < sid.length; i++) {
            if (sid.length > 1) {
                id += sid[i] + "*";
                status += this.status[i] + "*";
            } else {
                id = sid[i];
                status = this.status[i];
            }
        }
        return std + "#" + id + "#" + date + "#" + status;
    }
}
