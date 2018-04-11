<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Edit_Teacher_Details.aspx.cs" Inherits="View_Edit_Teacher_Details" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <table width="80%">
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="20" class="hea">
                    <asp:Label ID="Label1" runat="server" Text="View / Edit Teacher Details" />    
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <asp:GridView ID="GridView1" runat="server" Width="80%" AutoGenerateColumns="False" OnRowCommand="GridView1_RowCommand" CellPadding="4" ForeColor="#333333" GridLines="None" >
                        <AlternatingRowStyle BackColor="White" />
                            <Columns>
                            <asp:BoundField HeaderText="Teacher Name" DataField="name"/>
                            <asp:BoundField HeaderText="Class" DataField="high_qualification"/>
                                <asp:TemplateField>
                                    <ItemTemplate>
                                        <asp:LinkButton ID="yes" runat ="server" CommandArgument='<%#Eval("tid")%>' Text ="View" CommandName ="yes" ></asp:LinkButton>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>

                        <FooterStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                        <HeaderStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#FFCC66" ForeColor="#333333" HorizontalAlign="Center" />
                        <RowStyle BackColor="#FFFBD6" ForeColor="#333333" />
                        <SelectedRowStyle BackColor="#FFCC66" Font-Bold="True" ForeColor="Navy" />
                        <SortedAscendingCellStyle BackColor="#FDF5AC" />
                        <SortedAscendingHeaderStyle BackColor="#4D0000" />
                        <SortedDescendingCellStyle BackColor="#FCF6C0" />
                        <SortedDescendingHeaderStyle BackColor="#820000" />

                    </asp:GridView>
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
                                    <asp:Label ID="Label3" runat="server" Text="Highest Qualification"></asp:Label>
                                </td>

                                <td align="left" class="tx" width="30%">
                                    <asp:TextBox ID="TextBox3" required runat="server" Width="40%"></asp:TextBox>
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
                                    <asp:TextBox ID="TextBox1" runat="server" required Width="40%"></asp:TextBox>
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
                                    <asp:Label ID="Label2" runat="server" Text="Email-ID"></asp:Label>
                                </td>

                                <td align="left" class="tx">
                                    <asp:TextBox ID="TextBox2" runat="server" required Width="40%"></asp:TextBox>
                                    <asp:RegularExpressionValidator ID="RegularExpressionValidator2" runat="server" ControlToValidate="TextBox2" ErrorMessage="Invalid E-Maild ID" Font-Size="Small" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
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
                                <td colspan="2" align="center">
                                    <asp:Button ID="Button1" runat="server" Text="Update" CssClass="intabular" Height="40px" Width="20%" />
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