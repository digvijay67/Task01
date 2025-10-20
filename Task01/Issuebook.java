package Task01;

import java.sql.*;
import java.util.Scanner;

public class Issuebook {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "Root";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            if (!recordExists(con, "Student", "student_id", studentId)) {
                System.out.println("❌ Student not found!");
                return;
            }

            if (!recordExists(con, "Book", "book_id", bookId)) {
                System.out.println("❌ Book not found!");
                return;
            }
            String sql = "INSERT INTO Book_Issue (student_id, book_id) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();

            System.out.println("✅ Book issued successfully!");

            String query = """
                    SELECT bi.issue_id, s.student_name, b.book_name, bi.issue_datetime
                    FROM Book_Issue bi
                    JOIN Student s ON bi.student_id = s.student_id
                    JOIN Book b ON bi.book_id = b.book_id
                    ORDER BY bi.issue_datetime DESC
                    """;

            ResultSet rs = con.createStatement().executeQuery(query);
            System.out.println("\n--- All Issued Books ---");
            System.out.printf("%-5s %-20s %-30s %-20s%n", "ID", "Student Name", "Book Name", "Issue Date/Time");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-30s %-20s%n",
                        rs.getInt("issue_id"),
                        rs.getString("student_name"),
                        rs.getString("book_name"),
                        rs.getTimestamp("issue_datetime").toString());
            }

            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }

        sc.close();
    }

    private static boolean recordExists(Connection con, String table, String column, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }
}
