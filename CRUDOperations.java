package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDOperations {

    static Scanner sc = new Scanner(System.in);

    // =========================
    // ADD STUDENT
    // =========================
    public static void addStudent() {

        String sql = "INSERT INTO students (name, email, phone, gender, age) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, gender);
            ps.setInt(5, age);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================
    // VIEW STUDENTS
    // =========================
    public static void viewStudents() {

        String sql = "SELECT * FROM students";

        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n========== STUDENT DETAILS ==========");

            while (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("student_id")
                    + " | Name: " + rs.getString("name")
                    + " | Email: " + rs.getString("email")
                    + " | Phone: " + rs.getString("phone")
                    + " | Gender: " + rs.getString("gender")
                    + " | Age: " + rs.getInt("age")
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================
    // UPDATE STUDENT
    // =========================
    public static void updateStudent() {

        String sql = "UPDATE students SET name=?, email=?, phone=?, gender=?, age=? "
                   + "WHERE student_id=?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Email: ");
            String email = sc.nextLine();

            System.out.print("Enter New Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter New Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter New Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, gender);
            ps.setInt(5, age);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================
    // DELETE STUDENT
    // =========================
    public static void deleteStudent() {

        String sql = "DELETE FROM students WHERE student_id=?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student ID not found.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}