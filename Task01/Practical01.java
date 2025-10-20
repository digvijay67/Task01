package Task01;
import java.sql.*;
import java.util.Scanner;

public class Practical01 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db"; 
        String user = "root";
        String password = "Root";

        Scanner sc = new Scanner(System.in);

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println(" Connected to MySQL successfully!");

            
            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();

            System.out.print("Enter Student Name: ");
            String studentName = sc.nextLine();

            System.out.print("Enter Book Name: ");
            String bookName = sc.nextLine();

            String query = "INSERT INTO book_issue(student_id, student_name, book_name) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, studentId);
            pstmt.setString(2, studentName);
            pstmt.setString(3, bookName);
            pstmt.executeUpdate();

            System.out.println(" Book issue record inserted successfully!");

            
            System.out.println("\n All Book Issue Records:");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book_issue");

            System.out.printf("%-5s %-10s %-20s %-30s %-20s%n",
                "ID", "Stud_ID", "Student Name", "Book Name", "Issue Date/Time");
            System.out.println("Bookes Datails: ");
            sc.nextLine();

            while (rs.next()) {
                System.out.printf("%-5d %-10s %-20s %-30s %-20s%n",
                    rs.getInt("issue_id"),
                    rs.getString("student_id"),
                    rs.getString("student_name"),
                    rs.getString("book_name"),
                    rs.getTimestamp("issue_datatime").toString());
            }

            con.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        sc.close();
    }
}
