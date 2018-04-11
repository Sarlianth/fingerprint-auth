using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;

public partial class View_Edit_Class : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {
        SqlDataAdapter adapter;
        DataSet dataSet = new DataSet();
        string query = "select cid, course_name, sem from add_class";
        adapter = new SqlDataAdapter(query, con);
        adapter.Fill(dataSet);
        if (dataSet.Tables[0].Rows.Count > 0)
        {
            GridView1.DataSource = dataSet;
            GridView1.DataBind();
        }
    }
}