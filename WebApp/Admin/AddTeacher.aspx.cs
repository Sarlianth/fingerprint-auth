using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class AddTeacher : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=182.50.133.110;Initial Catalog=AAttendance;User ID=AAttendance;Password=H0vru@85");

    protected void Page_Load(object sender, EventArgs e)
    {

    }
}