using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;

public partial class Login : System.Web.UI.Page
{
    SqlConnection connection = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        SqlDataAdapter adapter;
        DataSet dataSet = new DataSet();
        string query = "select pass from admin where id='" + TextBox1.Text + "'";
        adapter = new SqlDataAdapter(query, connection);
        adapter.Fill(dataSet);

        if (dataSet.Tables[0].Rows.Count > 0)
        {
            string pass = dataSet.Tables[0].Rows[0][0].ToString();
            if (TextBox2.Text == pass)
            {
                Session["type"] = "admin";
                Response.Redirect("Add_class.aspx");
            }
            else
            {
                Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Invalid password')", true);
            }
        }

        else
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Invalid username')", true);
        }
    }
}