<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Main.aspx.cs" Inherits="Main" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <table width="80%">
        <tr>
            <td colspan="2"><br /><br />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <asp:Label ID="Label1" CssClass="header" runat="server" Text="Who are you?" Font-Size="28px" /><br /><br />
            </td>
        </tr>
        <tr>
            <td align="right">
                <asp:Button ID="Button" runat="server" Text="Admin" PostBackUrl="~/Login.aspx" Height="40px" CssClass="button" Width="40%" />
            </td>
            
            <td align="left">
                <asp:Button ID="Button1" runat="server" Text="Parent" PostBackUrl="~/Login_parent.aspx" Height="40px" CssClass="button" Width="40%" />
            </td>
        </tr>
        <tr>
            <td colspan="2"><br /><br /><br /><br />
            </td>
        </tr>
    </table>

</asp:Content>