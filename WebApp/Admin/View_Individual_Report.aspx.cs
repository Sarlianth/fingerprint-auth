using System;
using System.Web.UI;
using System.Data.SqlClient;
using System.Data;

public partial class View_Individual_Report : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select course_name from add_class";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < dataSet.Tables[0].Rows.Count; i++)
                {
                    DropDownList1.Items.Add(dataSet.Tables[0].Rows[i][0].ToString());
                }
            }
        }

    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (DropDownList2.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Please select student')", true);
        }
        else
        {
            string fromDate = TextBox1.Text;
            fromDate = convert(fromDate);
            DateTime from = Convert.ToDateTime(fromDate);
            int fromMonth = from.Month;

            string toDate = TextBox2.Text;
            toDate = convert(toDate);
            DateTime to = Convert.ToDateTime(toDate);
            int toMonth = to.Month;
            int month_now = DateTime.Now.Month;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select sid from student_details where class='" + DropDownList1.SelectedItem.Text + "' and name='" + DropDownList2.SelectedItem.Text + "'";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);

            if (dataSet.Tables[0].Rows.Count > 0)
            {
                SqlDataAdapter adapter1;
                DataSet dataSet1 = new DataSet();
                string query1 = "select att_date,status from attendance where sid='" + dataSet.Tables[0].Rows[0][0].ToString() + "' and att_date>='" + fromDate + "' and att_date<='" + toDate + "'";
                adapter1 = new SqlDataAdapter(query1, con);
                adapter1.Fill(dataSet1);
                DataTable ChartData = dataSet1.Tables[0];

                // storing total rows count to loop on each record  
                string[] XPointMember = new string[dataSet1.Tables[0].Rows.Count];
                int[] YPointMember = new int[dataSet1.Tables[0].Rows.Count];

                for (int count = 0; count < ChartData.Rows.Count; count++)
                {
                    // values for X axis  
                    XPointMember[count] = dataSet1.Tables[0].Rows[count][0].ToString();
                    // vlues for Y Axis  
                    string status = dataSet1.Tables[0].Rows[count][1].ToString();
                    if (status == "Present")
                    {
                        YPointMember[count] = 4;
                    }
                    else if (status == "Absent")
                    {
                        YPointMember[count] = 1;

                    }
                    else if (status == "Late")
                    {
                        YPointMember[count] = 3;

                    }
                    else if (status == "Sick")
                    {
                        YPointMember[count] = 2;

                    }
                }
                // binding chart control  
                Chart2.Series[0].Points.DataBindXY(XPointMember, YPointMember);
                // setting width of line  
                Chart2.Series[0].BorderWidth = 1;
                // setting Chart type  
                Chart2.ChartAreas["ChartArea1"].AxisX.MajorGrid.Enabled = false;
                Chart2.ChartAreas["ChartArea1"].AxisY.MajorGrid.Enabled = false;
                Panel1.Visible = true;
            }
        }
    }

    public string convert(string str)
    {
        DateTime dateTime = Convert.ToDateTime(str);
        string converted = dateTime.ToString("yyyy/MM/dd");
        return converted;
    }

    protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
    {
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Select Student Name !!!')", true);
        }
        else
        {
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select name from student_details where class='" + DropDownList1.SelectedItem.Text + "'";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            DropDownList2.Items.Clear();
            DropDownList2.Items.Add("--Select--");
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < dataSet.Tables[0].Rows.Count; i++)
                {
                    DropDownList2.Items.Add(dataSet.Tables[0].Rows[i][0].ToString());
                }
            }
        }
    }
}