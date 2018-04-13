using System.Data;

namespace JSONWebAPI
{
    public interface IServiceAPI
    {
        string login(string tname, string pass);
        string getstudents(string cname, string date);
        string attendence(string cname, string sid, string date, string status);
    }
}