package Task01;

import java.sql.*;
import java.util.Scanner;

public class AddStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Root";

        Scanner sc = new Scanner(System.in);

        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            String sql = "INSERT INTO Student (student_name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.executeUpdate();

            System.out.println("Student added successfully!");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + " | " +
                                   rs.getString("student_name") + " | " +
                                   rs.getString("email") + " | " +
                                   rs.getString("phone"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        sc.close();
    }
}

