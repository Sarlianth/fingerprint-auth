using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Add_Student : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["add"] == "add")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Added new student and parent received authentication credentials by email.", true);
            Session["add"] = "";
        }

        SqlDataAdapter adapter;
        DataSet data = new DataSet();
        string query = "select course_name from add_class";
        adapter = new SqlDataAdapter(query, con);
        adapter.Fill(data);
        if (data.Tables[0].Rows.Count > 0)
        {
            for (int i = 0; i < data.Tables[0].Rows.Count; i++)
            {
                DropDownList1.Items.Add(data.Tables[0].Rows[i][0].ToString());
            }
        }
    }

    
}