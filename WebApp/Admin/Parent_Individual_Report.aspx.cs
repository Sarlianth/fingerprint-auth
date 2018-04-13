using System;
using System.Data.SqlClient;
using System.Data;

public partial class Parent_Individual_Report : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            Panel1.Visible = false;
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        string sid = Session["id"].ToString();

        string fromDate = TextBox1.Text;
        fromDate = convert(fromDate);
        DateTime fromMonth = Convert.ToDateTime(fromDate);
        int from = fromMonth.Month;

        string toDate = TextBox2.Text;
        toDate = convert(toDate);
        DateTime toMonth = Convert.ToDateTime(toDate);
        int to_month = toMonth.Month;
        int to = DateTime.Now.Month;

        SqlDataAdapter adapter;
        DataSet dataSet = new DataSet();
        string query = "select att_date,status from attendance where sid='" + sid + "' and att_date>='" + fromDate + "' and att_date<='" + toDate + "'";
        adapter = new SqlDataAdapter(query, con);
        adapter.Fill(dataSet);
        DataTable ChartData = dataSet.Tables[0];

        //storing total rows count to loop on each record  
        string[] XPointMember = new string[dataSet.Tables[0].Rows.Count];
        int[] YPointMember = new int[dataSet.Tables[0].Rows.Count];

        for (int count = 0; count < ChartData.Rows.Count; count++)
        {
            // values for X axis  
            XPointMember[count] = dataSet.Tables[0].Rows[count][0].ToString();
            // values for Y Axis  
            string status = dataSet.Tables[0].Rows[count][1].ToString();
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

    public string convert(string str)
    {
        DateTime dateTime = Convert.ToDateTime(str);
        string converted = dateTime.ToString("yyyy/MM/dd");
        return converted;
    }
}