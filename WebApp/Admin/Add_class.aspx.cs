using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Add_class : System.Web.UI.Page
{
    SqlConnection connection = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["add"] == "add")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Added new class details')", true);
            Session["add"] = "";
        }
        if (!IsPostBack)
        {
            Panel1.Visible = false;
            SqlDataAdapter adapter;
            DataSet data = new DataSet();
            string query = "select name from teacher_details";
            adapter = new SqlDataAdapter(query, connection);
            adapter.Fill(data);
            if (data.Tables[0].Rows.Count > 0)
            {
                for (int i = 0; i < data.Tables[0].Rows.Count; i++)
                {
                    DropDownList2.Items.Add(data.Tables[0].Rows[i][0].ToString());
                }
            }
        }
    }

    protected void DropDownList2_SelectedIndexChanged(object sender, EventArgs e)
    {
        Random random = new Random();
        int iterations = 0;
        iterations = random.Next(1000, 10000);
        TextBox2.Text = Convert.ToString(iterations);
        Panel1.Visible = true;
    }
}