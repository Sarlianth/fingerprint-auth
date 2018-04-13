<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Home.aspx.cs" Inherits="Home" %>

<asp:Content ID="Body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <br />
        <br />
            <asp:Label ID="Label1" CssClass="header" runat="server" Text="Home"></asp:Label>
        <br />
        <br />
        <p class="tx" style="text-align:center; font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; color:black; font-size:x-large">We have seen over the years that the process of manual attendance has been carried out across almost all educational institutions. The process is not only time consuming but also sometimes inefficient resulting in the false marking of attendance. These days we do not need to use pen and paper to take attendace. Following this thought, we have proposed an attendance monitoring system based on the concept of web services which are implemented as an Android mobile application that communicates with the database residing on a remote server.
        </p>
        <p class="tx" style="text-align:center; font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; color:black; font-size:x-large">Our mobile application would require connecting to the database through restful api. Our project is an efficient and user friendly android application for attendance taking. The application will be installed on the users' smartphone - in this case teachers' smartphone. It provides very easy to understand user interface to take attendance of class listing names of all students that are registered for certain class. The application provides strong user authentication and efficient database access via the web api.
        </p>
        <p class="tx" style="text-align:center; font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; color:black; font-size:x-large">The second part of our project is the web application that has been designed for parents and admin. Our web application allows parents to easily give feedback or leave notes for teachers. Parents are also able to check their chiild attendance. Administrator is able to add teachers into the system, as well as classes and students. Admin can also see if there is any feedback written by parents.
        </p>
        <br />
        <br />
    </div>
</asp:Content>