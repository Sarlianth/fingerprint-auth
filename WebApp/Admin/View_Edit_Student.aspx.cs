using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class View_Edit_Student : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["update"] == "update")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Student successfully updated')", true);
            Session["update"] = "";
        }
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string query = "select sid,name,class from student_details";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                GridView1.DataSource = dataSet;
                GridView1.DataBind();
            }
            SqlDataAdapter adapter1;
            DataSet dataSet1 = new DataSet();
            string query1 = "select course_name from add_class";
            adapter1 = new SqlDataAdapter(query1, con);

            adapter1.Fill(dataSet1);
            if (dataSet1.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < dataSet1.Tables[0].Rows.Count; i++)
                {
                    DropDownList1.Items.Add(dataSet1.Tables[0].Rows[i][0].ToString());
                }
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
            string query = "select class,phone,email,pemail from student_details where sid='" + i + "'";
            adapter = new SqlDataAdapter(query, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                TextBox1.Text = dataSet.Tables[0].Rows[0][1].ToString();
                foreach (ListItem word in DropDownList1.Items)
                {
                    if (word.Value == dataSet.Tables[0].Rows[0][0].ToString())
                    {
                        DropDownList1.SelectedValue = word.Value;
                    }
                }
                TextBox2.Text = dataSet.Tables[0].Rows[0][2].ToString();
                TextBox3.Text = dataSet.Tables[0].Rows[0][3].ToString();
                h1.Value = i;
                Panel1.Visible = true;

            }
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Please select a class')", true);
        }
        else
        {
            SqlCommand cmd;
            con.Open();
            string op = "update student_details set class='" + DropDownList1.SelectedItem.Text + "',phone='" + TextBox1.Text + "',email='" + TextBox2.Text + "',pemail='" + TextBox3.Text + "' where sid='" + h1.Value + "'";
            cmd = new SqlCommand(op, con);
            cmd.ExecuteNonQuery();
            con.Close();
            Session["update"] = "update";
            Response.Redirect("View_Edit_Student.aspx");
        }

    }
}