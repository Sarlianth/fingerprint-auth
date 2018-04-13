using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class AddTeacher : System.Web.UI.Page
{
    SqlConnection connection = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["add"] == "add")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Teacher successfully added')", true);
            Session["add"] = "";
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        SqlDataAdapter adapter;
        DataSet data = new DataSet();
        string query = "select top 1 tid from teacher_details order by tid desc";
        adapter = new SqlDataAdapter(query, connection);
        adapter.Fill(data);
        int count = 0;
        if (data.Tables[0].Rows.Count > 0)
        {
            count = Convert.ToInt32(data.Tables[0].Rows[0][0].ToString());
            count = count + 1;
            h1.Value = Convert.ToString(count);
        }
        else
        {
            h1.Value = "101";
        }

        SqlCommand command;
        connection.Open();
        string query1 = "insert into teacher_details values('" + h1.Value + "','" + TextBox1.Text + "','" + TextBox4.Text + "','" + TextBox2.Text + "','" + TextBox3.Text + "')";
        command = new SqlCommand(query1, connection);
        command.ExecuteNonQuery();
        connection.Close();
        Session["add"] = "add";
        Response.Redirect("AddTeacher.aspx");
    }
}