using System;
using System.Data;
using System.Data.SqlClient;

namespace JSONWebAPI
{

    public class ServiceAPI : IServiceAPI
    {
        SqlConnection con;
        SqlCommand cmd;
        SqlDataReader dr;
        public ServiceAPI()
        {
            con = DBConnect.getConnection();
        }

        public string login(string tname, string pass)
        {
            string ans = "";
            try
            {
                if (con.State.ToString() == "Closed")
                {
                    con.Open();
                }

                cmd = new SqlCommand("select cid,course_name,sem from add_class where teacher='" + tname + "' AND pass='" + pass + "'", con);
                dr = cmd.ExecuteReader();
                if (dr.HasRows)
                {
                    dr.Read();
                    ans = dr[0].ToString() + "*" + dr[1].ToString() + "*" + dr[2].ToString();
                }
                else
                {
                    ans = "false";
                }

                con.Close();
            }
            catch (Exception e)
            {
                ans = e.Message;
            }
            return ans;
        }

        public string getstudents(string cname, string date)
        {
            string ans = "";
            try
            {
                if (con.State.ToString() == "Closed")
                {
                    con.Open();
                }

                cmd = new SqlCommand("select * from attendance where cname='" + cname + "' AND att_date='" + date + "'", con);
                dr = cmd.ExecuteReader();
                if (dr.HasRows)
                {
                    ans = "already";
                }
                else
                {
                    con.Close();

                    cmd = new SqlCommand("select sid,name from student_details where class='" + cname + "' Order by name", con);
                    con.Open();
                    dr = cmd.ExecuteReader();
                    if (dr.HasRows)
                    {
                        while (dr.Read())
                        {
                            ans += dr[0].ToString() + "*" + dr[1].ToString() + "#";
                        }
                    }
                    else
                    {
                        ans = "no";
                    }
                }


                con.Close();
            }
            catch (Exception e)
            {
                ans = e.Message;
            }
            return ans;
        }

        public string attendence(string cname, string sid, string date, string status)
        {
            string ans = "false";
            try
            {

                if (sid.Contains("*"))
                {
                    string[] id = sid.Split('*');
                    string[] stats = status.Split('*');

                    for (int i = 0; i < id.Length - 1; i++)
                    {
                        cmd = new SqlCommand("insert into attendance values('" + cname + "','" + id[i] + "','" + date + "','" + stats[i] + "')", con);
                        con.Open();
                        cmd.ExecuteNonQuery();
                        con.Close();
                    }
                    ans = "true";
                }
                else
                {
                    cmd = new SqlCommand("insert into attendance values('" + cname + "','" + sid + "','" + date + "','" + status + "')", con);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                    ans = "true";
                }

            }
            catch (Exception e)
            {
                ans = e.Message;
            }

            return ans;
        }

    }
}