<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="View_Individual_Report.aspx.cs" Inherits="View_Individual_Report" %>

<%@ Register Assembly="System.Web.DataVisualization, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35" Namespace="System.Web.UI.DataVisualization.Charting" TagPrefix="asp" %>

<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <table width="80%">
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center" class="hea">
                <asp:Label ID="Label1" runat="server" Text="Individual report"></asp:Label></td>
        </tr>
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td class="lab" width="40%">
                <asp:Label ID="Label2" runat="server" Text="Class"></asp:Label></td>
            <td class="tx">
                <asp:DropDownList ID="DropDownList1" runat="server" Width="40%"
                    AutoPostBack="True" OnSelectedIndexChanged="DropDownList1_SelectedIndexChanged">
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
            <td class="lab">
                <asp:Label ID="Label3" runat="server" Text="Student name"></asp:Label></td>
            <td class="tx">
                <asp:DropDownList ID="DropDownList2" runat="server" Width="40%">
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
            <td class="lab">
                <asp:Label ID="Label4" runat="server" Text="From date"></asp:Label></td>
            <td class="tx">
                <asp:TextBox ID="TextBox1" type="date" required="true" runat="server" Width="50%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td class="lab">
                <asp:Label ID="Label5" runat="server" Text="To date"></asp:Label></td>
            <td class="tx">
                <asp:TextBox ID="TextBox2" type="date" required="true" runat="server" Width="50%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <asp:Button ID="Button1" runat="server" Text="Submit" Height="50px" OnClick="Button1_Click" CssClass="button" Width="20%" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <br />
            </td>
        </tr>
    </table>
    <asp:Panel ID="Panel1" runat="server">

        <table width="80%">
            <tr>
                <td colspan="2" align="right">
                    <asp:Image ID="Image1" runat="server" ImageUrl="~/images/legend.png" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <asp:Chart ID="Chart2" runat="server" Width="1000px" Palette="EarthTones">
                        <Series>
                            <asp:Series ChartArea="ChartArea1" ChartType="Line" Name="Series1">
                            </asp:Series>
                        </Series>
                        <ChartAreas>
                            <asp:ChartArea Name="ChartArea1">
                                <AxisX IsStartedFromZero="True" Title="Date"
                                    TitleFont="Segoe UI, 10pt, style=Bold" IsLabelAutoFit="False" LineColor="Gray"
                                    Minimum="0" Maximum="20" Interval="1">
                                </AxisX>
                                <AxisY IsStartedFromZero="True" Title="Status"
                                    TitleFont="Segoe UI, 10pt, style=Bold" IsLabelAutoFit="False" LineColor="Gray"
                                    Minimum="0" Maximum="4">
                                </AxisY>
                            </asp:ChartArea>
                        </ChartAreas>

                    </asp:Chart>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br />
                </td>
            </tr>
        </table>
    </asp:Panel>
</asp:Content>