<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Login_parent.aspx.cs" Inherits="Login_parent" %>

<asp:Content ID="Body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <table width="80%">
            <tr>
            <td colspan="2"><br />

            </td></tr>
            <tr>
                <td colspan="2" align="center">
                    <asp:Label ID="Label1" CssClass="header" runat="server" Text="Parent login" Font-Size="28px" />
                </td>
            </tr>

            <tr>
                <td colspan="2"><br />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <asp:TextBox ID="TextBox1" required="true" Placeholder="Username" runat="server" Font-Size="13px" />
                </td>
            </tr>

            
            <tr>
                <td colspan="2" align="center">
                    <asp:TextBox ID="TextBox2" required="true" Placeholder="Password" runat="server" Font-Size="13px" TextMode="Password"/>
                </td>
            </tr>

            <tr>
                <td colspan="2"><br />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center" class="intabular">
                    <asp:Button ID="Button1" runat="server" onClick="Button1_Click" Text="Login" Height="40px" CssClass="button"  Width="20%" />
                </td>
            </tr>
            <tr>
                <td colspan="2"><br />
                </td>
            </tr>
            <tr>
                <td colspan="2"><br />
                </td>
            </tr>
        </table>
    </div>
</asp:Content>