<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Edit_Student.aspx.cs" Inherits="View_Edit_Student" %>

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
                <asp:Label ID="Label1" runat="server" Text="View / Edit Student Details" />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <asp:Panel ID="Panel1" runat="server">
                    <table width="80%">

                        <tr>
                            <td colspan="2">
                                <br />
                            </td>
                        </tr>

                        <tr>
                            <td align="left" class="lab" width="50%">
                                <asp:Label ID="Label3" runat="server" Text="Class"></asp:Label>
                            </td>

                            <td align="left" class="tx" width="40%">
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
                            <td align="left" class="lab">
                                <asp:Label ID="Label4" runat="server" Text="Phone"></asp:Label>
                            </td>

                            <td align="left" class="tx">
                                <asp:TextBox ID="TextBox1" runat="server" required Width="50%"></asp:TextBox>
                                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ControlToValidate="TextBox1" ErrorMessage="Invalid Phone Number" Font-Size="Small" ForeColor="Red" ValidationExpression="\d+"></asp:RegularExpressionValidator>
                            </td>
                        </tr> 

                        <tr>
                            <td colspan="2">
                                <br />
                            </td>
                        </tr>

                        <tr>
                            <td align="left" class="lab">
                                <asp:Label ID="Label2" runat="server" Text="Student e-mail"></asp:Label>
                            </td>

                            <td align="left" class="style2">
                                <asp:TextBox ID="TextBox2" runat="server" required Width="50%"></asp:TextBox>
                                <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" ControlToValidate="TextBox2" ErrorMessage="Invalid E-Maild ID" Font-Size="Small" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
                            </td>
                        </tr> 

                        <tr>
                            <td colspan="2">
                                <br />
                            </td>
                        </tr>

                        <tr>
                            <td align="left" class="lab">
                                <asp:Label ID="Label5" runat="server" Text="Parents e-mail"></asp:Label>
                            </td>

                            <td align="left" class="tx">
                                <asp:TextBox ID="TextBox3" runat="server" required Width="50%"></asp:TextBox>
                                <asp:RegularExpressionValidator ID="RegularExpressionValidator3" runat="server" ControlToValidate="TextBox3" ErrorMessage="Invalid E - Mail ID" Font-Size="Small" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
                            </td>

                        </tr> 

                        <tr>
                            <td colspan="2">
                                <asp:HiddenField ID="h1" runat="server" />
                                <br />
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <asp:Button ID="Button1" runat="server" Text="Update" class="button" Height="40px" Width="20%" />
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <br />
                            </td>
                        </tr>
                    </table>
                </asp:Panel>
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

<asp:Content ID="Content1" runat="server" contentplaceholderid="head">
    <style type="text/css">
        .style1
        {
        font-size: large;
        font-family: MS Reference Sans Serif;
        padding-left: 200px;
        text-align: left;
        height: 29px;
        }
        .style2
        {
        text-align: left;
        font-size: large;
        height: 29px;
        }
    </style>
</asp:Content>
