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
    SqlConnection connection = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["update"] == "update")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Class successfully updated')", true);
            Session["update"] = "";
        }
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select cid,course_name,sem from add_class";
            adapter = new SqlDataAdapter(query, connection);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                GridView1.DataSource = dataSet;
                GridView1.DataBind();
            }

            SqlDataAdapter adapter1;
            DataSet dataSet1 = new DataSet();
            string query1 = "select name from teacher_details";
            adapter1 = new SqlDataAdapter(query1, connection);
            adapter1.Fill(dataSet1);
            if (dataSet1.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < dataSet1.Tables[0].Rows.Count; i++)
                {
                    DropDownList2.Items.Add(dataSet1.Tables[0].Rows[i][0].ToString());
                }
            }

        }
    }

    protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
    {
        if (e.CommandName == "yes")
        {
            string courseID = Convert.ToString(e.CommandArgument.ToString());
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select course_name,sem,teacher from add_class where cid='" + courseID + "'";
            adapter = new SqlDataAdapter(query, connection);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                TextBox1.Text = dataSet.Tables[0].Rows[0][0].ToString();
                foreach (ListItem word in DropDownList1.Items)
                {
                    if (word.Value == dataSet.Tables[0].Rows[0][1].ToString())
                    {
                        DropDownList1.SelectedValue = word.Value;
                    }
                }
                foreach (ListItem word in DropDownList2.Items)
                {
                    if (word.Value == dataSet.Tables[0].Rows[0][2].ToString())
                    {
                        DropDownList2.SelectedValue = word.Value;
                    }
                }
                h1.Value = courseID;
                Panel1.Visible = true;
            }
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Please select semester')", true);
        }
        else
        {
            SqlCommand command;
            connection.Open();
            string query = "update add_class set course_name='" + TextBox1.Text + "',sem='" + DropDownList1.SelectedItem.Text + "',teacher='" + DropDownList2.SelectedItem.Text + "' where cid='" + h1.Value + "'";
            command = new SqlCommand(query, connection);
            command.ExecuteNonQuery();
            connection.Close();
            Session["update"] = "update";
            Response.Redirect("View_Edit_Class.aspx");
        }

    }
}