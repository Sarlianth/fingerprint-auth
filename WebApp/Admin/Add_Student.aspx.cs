using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Mail;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Add_Student : System.Web.UI.Page
{
    SqlConnection connection = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["add"] == "add")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Student successfully added. Parents login credentials sent to email')", true);
            Session["add"] = "";
        }

        SqlDataAdapter adapter;
        DataSet data = new DataSet();
        string query = "select course_name from add_class";
        adapter = new SqlDataAdapter(query, connection);
        adapter.Fill(data);
        if (data.Tables[0].Rows.Count > 0)
        {
            for (int i = 0; i < data.Tables[0].Rows.Count; i++)
            {
                DropDownList1.Items.Add(data.Tables[0].Rows[i][0].ToString());
            }
        }
    }

    int GetRandomNumber()
    {
        Random random = new Random();
        return random.Next(500, 1000);
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (DropDownList1.SelectedItem.Text == "--Select--")
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Please select class')", true);
        }
        else
        {
            SqlDataAdapter adapter;
            DataSet data = new DataSet();
            string query = "select top 1 sid from student_details order by  sid desc";
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
                h1.Value = "8001";
            }

            int id = GetRandomNumber();

            h2.Value = Convert.ToString(id) + h1.Value;
            SqlCommand cmd;
            connection.Open();
            string query1 = "insert into student_details values('" + h1.Value + "','" + TextBox1.Text + "','" + DropDownList1.SelectedItem.Text + "','" + TextBox2.Text + "','" + TextBox3.Text + "','" + TextBox4.Text + "','" + h2.Value + "','" + TextBox5.Text + "')";
            cmd = new SqlCommand(query1, connection);
            cmd.ExecuteNonQuery();
            connection.Close();

            MailMessage mail = new MailMessage();
            SmtpClient SmtpServer = new SmtpClient("smtp.gmail.com");
            mail.From = new MailAddress("attendancegroup13@gmail.com");
            mail.To.Add(TextBox5.Text);
            mail.Subject = "Attendance system registration";
            mail.Body = "Dear " + TextBox1.Text + 
                ",\r\n\r\n You were successfuly registered. Please use your parent credentials provided below to authenticate into the system.\r\n\r\n E-mail : " 
                + TextBox5.Text + "\r\n\r\n Password : " + h2.Value + " \r\n\r\n\r\n\r\n";

            SmtpServer.Port = 587;
            SmtpServer.Credentials = new System.Net.NetworkCredential("attendancegroup13@gmail.com", "attendance");
            SmtpServer.EnableSsl = true;
            SmtpServer.Send(mail);
            Page.ClientScript.RegisterStartupScript(GetType(), "msgtype()", "alert('Student successfully added. Parents login credentials sent to email')" + h2.Value, true);
            Session["add"] = "add";
            Response.Redirect("Add_Student.aspx");
        }

    }

}