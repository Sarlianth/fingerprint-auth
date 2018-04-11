<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Add_class.aspx.cs" Inherits="Add_class" %>

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
                    <asp:Label ID="Label1" runat="server" Text="Add new class" Font-Bold="False"></asp:Label>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td class="lab" width="40%">
                    <asp:Label ID="Label3" runat="server" Text="Course Name"></asp:Label>
                </td>

                <td class="tx">
                    <asp:TextBox ID="TextBox1" required="true" runat="server" Width="40%"></asp:TextBox>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            
        </table>
    </div>
</asp:Content>