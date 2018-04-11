using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;

public partial class View_Edit_Teacher_Details : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["update"] == "update")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Student Details Updated !!!')", true);
            Session["update"] = "";
        }
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select tid,name,high_qualification from teacher_details";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                GridView1.DataSource = dataSet;
                GridView1.DataBind();
            }

        }
    }

    protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
    {
        if (e.CommandName == "yes")
        {
            string i = Convert.ToString(e.CommandArgument.ToString());
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select high_qualification,mob,email from teacher_details where tid='" + i + "'";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                TextBox3.Text = dataSet.Tables[0].Rows[0][0].ToString();
                TextBox1.Text = dataSet.Tables[0].Rows[0][1].ToString();

                TextBox2.Text = dataSet.Tables[0].Rows[0][2].ToString();

                h1.Value = i;
                Panel1.Visible = true;

            }
        }
    }
}