using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Add_Feedback : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");
    protected void Page_Load(object sender, EventArgs e)
    {

        if (Session["add"] == "add")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Feedback successfully sent')", true);
            Session["add"] = "";
        }
    }
    protected void Button1_Click(object sender, EventArgs e)
    {

        string id = Session["id"].ToString();
        string name = Session["pname"].ToString();
        SqlDataAdapter da;
        DataSet ds = new DataSet();
        string s = "select top 1 fid from feedback order by fid desc";
        da = new SqlDataAdapter(s, con);
        da.Fill(ds);
        int newID = 0;
        if (ds.Tables[0].Rows.Count > 0)
        {
            newID = Convert.ToInt32(ds.Tables[0].Rows[0][0].ToString());
            newID++;
        }
        else
        {
            newID = 90001;
        }
        SqlCommand cmd;
        con.Open();
        string op = "insert into feedback values('" + newID + "','" + id + "','" + TextBox1.Text + "','" + name + "')";
        cmd = new SqlCommand(op, con);
        cmd.ExecuteNonQuery();
        con.Close();
        Session["add"] = "add";
        Response.Redirect("Add_Feedback.aspx");
    }
}