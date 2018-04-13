using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

public partial class View_Attendance_Report : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            Table1.Visible = false;
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
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Please select class')", true);
        }
        else
        {
            string fromDate = TextBox1.Text;
            fromDate = convert(fromDate);
            DateTime fm = Convert.ToDateTime(fromDate);
            int f = fm.Month;

            string toDate = TextBox2.Text;
            toDate = convert(toDate);
            DateTime dateTime = Convert.ToDateTime(toDate);
            int time = dateTime.Month;
            int month = DateTime.Now.Month;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select sid,name from student_details where class='" + DropDownList1.SelectedItem.Text + "'";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);

            if (dataSet.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < dataSet.Tables[0].Rows.Count; i++)
                {
                    SqlDataAdapter adapter1;
                    DataSet dataSet1 = new DataSet();
                    string query1 = "select status from attendance where sid='" + dataSet.Tables[0].Rows[i][0].ToString() + "' and att_date>='" + fromDate + "' and att_date<='" + toDate + "'";
                    adapter1 = new SqlDataAdapter(query1, con);
                    adapter1.Fill(dataSet1);
                    if (dataSet1.Tables[0].Rows.Count > 0)
                    {
                        int count = 0;
                        for (int j = 0; j < dataSet1.Tables[0].Rows.Count; j++)
                        {
                            string str = dataSet1.Tables[0].Rows[j][0].ToString();
                            if (str == "Present" || str == "Late")
                            {
                                count = count + 1;
                            }
                        }
                        int totallec = dataSet1.Tables[0].Rows.Count;
                        // students percentage that attended lecture
                        double lecpercent = (count / Convert.ToDouble(totallec));
                        lecpercent = lecpercent * 100;
                        lecpercent = Math.Round(lecpercent);

                        TableRow tRow = new TableRow();
                        TableCell tcell = new TableCell();
                        TableCell tcell1 = new TableCell();

                        tcell.Text = dataSet.Tables[0].Rows[i][1].ToString();
                        tcell.CssClass = "gg";
                        tcell1.Text = Convert.ToString(lecpercent) + "%";
                        tcell1.CssClass = "gg";

                        tRow.Cells.Add(tcell);
                        tRow.Cells.Add(tcell1);
                        tRow.BackColor = System.Drawing.Color.LightGray;

                        Table1.Rows.Add(tRow);
                    }
                }

            }
            Table1.Visible = true;
        }
    }
    public string convert(string str)
    {
        DateTime dt = Convert.ToDateTime(str);
        string converted = dt.ToString("yyyy/MM/dd");
        return converted;
    }
}