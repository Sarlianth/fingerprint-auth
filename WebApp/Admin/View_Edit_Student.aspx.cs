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
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Student Details Updated !!!')", true);
            Session["update"] = "";
        }
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet dataSet = new DataSet();
            string ml = "select sid,name,class from student_details";
            adapter = new SqlDataAdapter(ml, con);
            adapter.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count > 0)
            {
                GridView1.DataSource = dataSet;
                GridView1.DataBind();
            }
            SqlDataAdapter adapter1;
            DataSet dataSet1 = new DataSet();
            string rf = "select course_name from add_class";
            adapter1 = new SqlDataAdapter(rf, con);

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
            SqlDataAdapter da;
            DataSet ds = new DataSet();
            string kl = "select class,phone,email,pemail from student_details where sid='" + i + "'";
            da = new SqlDataAdapter(kl, con);
            da.Fill(ds);
            if (ds.Tables[0].Rows.Count > 0)
            {
                TextBox1.Text = ds.Tables[0].Rows[0][1].ToString();
                foreach (ListItem word in DropDownList1.Items)
                {
                    if (word.Value == ds.Tables[0].Rows[0][0].ToString())
                    {
                        DropDownList1.SelectedValue = word.Value;
                    }
                }
                TextBox2.Text = ds.Tables[0].Rows[0][2].ToString();
                TextBox3.Text = ds.Tables[0].Rows[0][3].ToString();
                h1.Value = i;
                Panel1.Visible = true;

            }
        }
    }
}