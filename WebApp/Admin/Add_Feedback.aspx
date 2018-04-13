<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Add_Feedback.aspx.cs" Inherits="Add_Feedback" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <table width="80%">
        <tr> 
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr> 
            <td colspan="2" class="hea">
                <asp:Label ID="Label1" runat="server" Text="Add feedback"/>
            </td>
        </tr>
        <tr> 
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td align="left" width="40%" class="lab" valign="top">
                <asp:Label ID="Label2" runat="server" Text="Feedback"/>
            </td>
            <td class="tx">
                <asp:TextBox ID="TextBox1" required="true" runat="server" TextMode="MultiLine" Width="67%" Height="114px"/>
            </td>
        </tr>
        <tr> 
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr> 
            <td colspan="2" align="center" class="intabular">
                <asp:Button ID="Button1" onClick="Button1_Click" runat="server" Text="Submit" Height="40px" CssClass="intabular" Width="20%" />
            </td>
        </tr>
        <tr> 
            <td colspan="2">
                <br />
            </td>
        </tr>
    </table>
</asp:Content>