<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Attendance_Report.aspx.cs" Inherits="View_Attendance_Report" %>

<asp:Content ID="head" ContentPlaceHolderID="head" runat="server">
    <style>
        .gg {
            text-align: center;
            font-size: large;
        }
    </style>

</asp:Content>
<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <table width="80%">
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            <tr>
                <td colspan="2" class="hea">
                    <asp:Label ID="Label1" runat="server" Text="View Attendance Report"></asp:Label></td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            <tr>
                <td class="lab" width="40%">
                    <asp:Label ID="Label2" runat="server" Text="Class"></asp:Label></td>
                <td class="tx" width="40%">
                    <asp:DropDownList ID="DropDownList1" runat="server" Width="50%">
                        <asp:ListItem>--Select--</asp:ListItem>
                    </asp:DropDownList>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            <tr>
                <td class="lab">
                    <asp:Label ID="Label3" runat="server" Text="From date"></asp:Label></td>
                <td class="tx">
                    <asp:TextBox ID="TextBox1" type="date" required="true" runat="server" Width="50%"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            <tr>
                <td class="lab">
                    <asp:Label ID="Label4" runat="server" Text="To date"></asp:Label></td>
                <td class="tx">
                    <asp:TextBox ID="TextBox2" type="date" required="true" runat="server" Width="50%"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <asp:Button ID="Button1" runat="server" Text="Submit" OnClick="Button1_Click" Height="50px" Width="20%" CssClass="intabular" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>


        </table>
    </div>

    <asp:Table ID="Table1" runat="server" Width="80%" BorderStyle="Solid" BorderWidth="1" GridLines="Both">
        <asp:TableHeaderRow BackColor="Black" ForeColor="White">
            <asp:TableHeaderCell>Student name</asp:TableHeaderCell>
            <asp:TableHeaderCell>Attendance</asp:TableHeaderCell>
           
        </asp:TableHeaderRow>
    </asp:Table>

    <br />
    <br />
    <br />
    <br />

</asp:Content>
