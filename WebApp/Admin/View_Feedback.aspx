<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Feedback.aspx.cs" Inherits="View_Feedback" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <table width="80%">
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center" class="hea">
                <asp:Label ID="Label1" runat="server" Text="View feedback"></asp:Label></td>
        </tr>
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="2" width="80%" align="center">
                <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False"
                    OnRowCommand="GridView1_RowCommand" CellPadding="4" ForeColor="#333333"
                    GridLines="None" Width="80%">
                    <AlternatingRowStyle BackColor="White" />
                    <Columns>

                        <asp:BoundField HeaderText="Parent Name" DataField="pname" />
                        <asp:BoundField HeaderText="Feedback" DataField="feedback" />
                        <asp:TemplateField>
                            <ItemTemplate>

                                <asp:LinkButton ID="yes" runat="server" CommandArgument='<%#Eval("fid")%>' Text="Delete" CommandName="yes"></asp:LinkButton>
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

    </table>

</asp:Content>
