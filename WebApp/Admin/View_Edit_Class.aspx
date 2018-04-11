<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Edit_Class.aspx.cs" Inherits="View_Edit_Class" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <table width="80%">
            <tr><td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="2" class="hea">
                    <asp:Label ID="Label1" runat="server" Text="Edit class details"></asp:Label>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <asp:GridView ID="GridView1" runat="server" Width="80%" AutoGenerateColumns="False" CellPadding="4" ForeColor="#333333" GridLines="None" >
                        <AlternatingRowStyle BackColor="White" />
                        <FooterStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                        <HeaderStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="#FFCC66" ForeColor="#333333" HorizontalAlign="Center" />
                        <RowStyle BackColor="#FFFBD6" ForeColor="#333333" />
                        <SelectedRowStyle BackColor="#FFCC66" Font-Bold="True" ForeColor="Navy" />
                        <SortedAscendingCellStyle BackColor="#FDF5AC" />
                        <SortedAscendingHeaderStyle BackColor="#4D0000" />
                        <SortedDescendingCellStyle BackColor="#FCF6C0" />
                        <SortedDescendingHeaderStyle BackColor="#820000" />

                        <Columns>
                            <asp:BoundField HeaderText="Course name" DataField="course_name"/>
                            <asp:BoundField HeaderText="Semester" DataField="sem"/>
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton ID="yes" runat="server" CommandArgument='<%#Eval("cid")%>' Text ="Edit" CommandName ="yes" />
                                </ItemTemplate>
                            </asp:TemplateField>

                        </Columns>
                    </asp:GridView>
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