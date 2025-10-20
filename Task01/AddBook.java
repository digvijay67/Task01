package Task01;

import java.sql.*;
import java.util.Scanner;

public class AddBook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Root";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Book Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            String sql = "INSERT INTO Book (book_name, author, price) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, author);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();

            System.out.println(" Book added successfully!");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Book");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        sc.close();
    }
}
