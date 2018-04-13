<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="AddTeacher.aspx.cs" Inherits="AddTeacher" %>

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
                    <asp:Label ID="Label1" runat="server" Text="Teacher details"></asp:Label>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td class="lab" width="40%">
                    <asp:Label ID="Label3" runat="server" Text="Name"></asp:Label>
                </td>

                <td class="tx" width="40%">
                    <asp:TextBox ID="TextBox1" required="true" runat="server" Width="40%"></asp:TextBox>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
            
            <tr>
                <td class="lab">
                    <asp:Label ID="Label2" runat="server" Text="Mobile number"></asp:Label>
                </td>
                    
                <td class="tx">
                    <asp:TextBox ID="TextBox2" required="true" runat="server" Width="40%"></asp:TextBox>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" 
                        ControlToValidate="TextBox2" ErrorMessage="Invalid Phone Number" 
                        Font-Size="Small" ForeColor="Red" ValidationExpression="\d+">
                    </asp:RegularExpressionValidator>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td class="lab">
                    <asp:Label ID="Label5" runat="server" Text="Teacher email"></asp:Label>
                </td>
                    
                <td class="tx">
                    <asp:TextBox ID="TextBox3" required="true" runat="server" Width="40%"></asp:TextBox>
                    <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" 
                        ControlToValidate="TextBox3" ErrorMessage=" " Font-Size="Small" ForeColor="Red" 
                        ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">Invalid Email ID
                    </asp:RegularExpressionValidator>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td class="lab">
                    <asp:Label ID="Label4" runat="server" Text="Highest qualification"></asp:Label>
                </td>
                    
                <td class="tx">
                    <asp:TextBox ID="TextBox4" required runat="server" Width="40%"></asp:TextBox>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <asp:HiddenField ID="h1" runat="server" />
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center" class="intabular">
                    <asp:Button ID="Button1" runat="server" Text="Submit" onclick="Button1_Click"
                        Height="40px" CssClass="button"  Width="20%" />
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