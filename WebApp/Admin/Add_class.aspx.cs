using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Mail;
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
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Class successfully added')", true);
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

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype", "alert('Please select semester')", true);
        }
        else
        {
            SqlDataAdapter adapter;
            DataSet data = new DataSet();
            string query1 = "select top 1 cid from add_class order by  cid desc";
            adapter = new SqlDataAdapter(query1, connection);
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
                h1.Value = "100001";
            }

            SqlCommand command;
            connection.Open();
            string query2 = "insert into add_class values('" + h1.Value + "','" + TextBox1.Text + "','" + DropDownList1.SelectedItem.Text + "','" + DropDownList2.SelectedItem.Text + "','" + TextBox2.Text + "')";
            command = new SqlCommand(query2, connection);
            command.ExecuteNonQuery();
            connection.Close();

            SqlDataAdapter adapter1;
            DataSet ds1 = new DataSet();
            string query3 = "select email from teacher_details where name='" + DropDownList2.SelectedItem.Text + "'";
            adapter1 = new SqlDataAdapter(query3, connection);
            adapter1.Fill(ds1);
            string email = "";

            if (ds1.Tables[0].Rows.Count > 0)
            {
                email = ds1.Tables[0].Rows[0][0].ToString();
            }

            MailMessage mail = new MailMessage();
            SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");
            mail.From = new MailAddress("attendancegroup13@gmail.com");
            mail.To.Add(email);
            mail.Subject = "Attendance system registration";
            mail.Body = "Dear " + DropDownList2.SelectedItem.Text + "," +
                "\r\n\r\n You were successfuly registered. Please use the following credentials to login.\r\n\r\n Username: " 
                + DropDownList2.SelectedItem.Text + "\r\n\r\n Password: " + TextBox2.Text + " \r\n\r\n\r\n\r\n";

            SmtpServer.Port = 587;
            SmtpServer.Credentials = new System.Net.NetworkCredential("attendancegroup13@gmail.com", "attendance");
            SmtpServer.EnableSsl = true;
            SmtpServer.Send(mail);
            Session["add"] = "add";
            Response.Redirect("Add_class.aspx");
        }
    }
}