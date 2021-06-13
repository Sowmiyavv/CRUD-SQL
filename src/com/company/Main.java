package com.company;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.sql.*;
import java.util.*;
public class Main
{

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("WELCOME");
            System.out.println("1.TO ADD");
            System.out.println("2.TO EDIT");
            System.out.println("3.TO VIEW");
            System.out.println("4.SPECIFIC DATA");
            System.out.println("5.TO DELETE");
            System.out.println("6.TO EXIT");
            Scanner sc = new Scanner(System.in);
            System.out.println("enter your choice");
            int choice =  sc.nextInt();
            switch(choice)
            {
                case 1: Add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    view();
                    break;
                case 4:
                    specific_view();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    System.out.println("THANK YOU");
                    return;
                default:
                    System.out.println("ENTER VALID CHOICE");
                    break;
            }
        }
    }

    public static void Add()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Your userName ");
        String username = sc.nextLine();
        System.out.println("Enter Your password ");
        String password = sc.nextLine();
        System.out.println("Enter Your emailid ");
        String email = sc.nextLine();

        try
        {
            Connection con = Database.getConnection();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO users(username,password,email) values(?,?,?)");

            pstmt.setString(1, username);
            pstmt.setString(2,password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Inserted");
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void edit()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter userid");
        int userid = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Your userName ");
        String username = sc.nextLine();
        System.out.println("Enter Your password ");
        String password = sc.nextLine();
        System.out.println("Enter Your emailid ");
        String email = sc.nextLine();

        try
        {
            Connection con = Database.getConnection();

            PreparedStatement pstmt = con.prepareStatement("update users set username=?,password=?,email=? where userid=?");
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,email);
            pstmt.setInt(4,userid);
            pstmt.executeUpdate();

            System.out.println("DB UPDATED");
            con.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void view()
    {
        try
        {
            Connection con = Database.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            System.out.format("%-10s%-20s%-20s%-30s","USERID","USERNAME","PASSWORD","EMAIL");
            System.out.println();
            while(rs.next())
            {
                System.out.format("%-10s%-20s%-20s%-30s",rs.getInt(1),rs.getString(2),rs.getString(3)
                ,rs.getString(4));
                System.out.println();
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void  specific_view()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter userid");
        int userid = sc.nextInt();

        try
        {
            Connection con = Database.getConnection();

            PreparedStatement pstmt = con.prepareStatement("select * from users where userid=?");
            pstmt.setInt(1,userid);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                System.out.format("%-10s%-20s%-20s%-30s",rs.getInt(1),rs.getString(2),rs.getString(3)
                        ,rs.getString(4));
                System.out.println();
            }
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void delete()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter userid");
        int userid = sc.nextInt();

        try
        {
            Connection con = Database.getConnection();

            PreparedStatement pstmt = con.prepareStatement("DELETE from users where userid=?");
            pstmt.setInt(1,userid);
            pstmt.executeUpdate();

            System.out.println("DB DELETED");
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}
