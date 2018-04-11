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
                    <asp:Label ID="Label3" runat="server" Text="Course name"></asp:Label>
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

            <tr>
                <td class="lab">
                    <asp:Label ID="Label4" runat="server" Text="Semester"></asp:Label>
                </td>
                
                <td class="tx">
                    <asp:DropDownList ID="DropDownList1" runat="server" Width="40%">
                        <asp:ListItem>--Select--</asp:ListItem>
                        <asp:ListItem>1</asp:ListItem>
                        <asp:ListItem>2</asp:ListItem>
                        <asp:ListItem>3</asp:ListItem>
                        <asp:ListItem>4</asp:ListItem>
                        <asp:ListItem>5</asp:ListItem>
                        <asp:ListItem>6</asp:ListItem>
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
                    <asp:Label ID="Label2" runat="server" Text="Teacher"></asp:Label>
                </td>
                
                <td class="tx">
                    
                </td>
            </tr>
        </table>

        <asp:Panel ID="Panel1" runat="server">
            <table width="80%">
                <tr>
                    <td colspan="2">
                        <br />
                    </td>
                </tr>
            
                <tr>
                    <td class="lab" width="40%">
                        <asp:Label ID="Label5" runat="server" Text="Password"></asp:Label>
                    </td>

                    <td class="tx">
                        <asp:TextBox ID="TextBox2" runat="server" Width="40%"></asp:TextBox>
                    </td>
                </tr>
            </table>
        </asp:Panel>

        <table width="80%">
            <tr>
                <td colspan="2">
                    <asp:HiddenField ID="h1" runat="server" />
                    <br />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center" class="intabular">
                    <asp:Button ID="Button1" runat="server" Text="Submit" 
                        Height="40px" CssClass="button" Width="20%" />
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