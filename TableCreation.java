package studentmanagement;

import java.sql.Connection;
import java.sql.Statement;

public class TableCreation {

    public static void createTables() {

        Connection con = null;
        Statement stmt = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            // 1. Students Table
            String studentsTable = "CREATE TABLE IF NOT EXISTS students ("
                    + "student_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "email VARCHAR(100) UNIQUE,"
                    + "phone VARCHAR(15),"
                    + "gender VARCHAR(10),"
                    + "age INT"
                    + ")";

            stmt.executeUpdate(studentsTable);
 

            // 2. Courses Table
            String coursesTable = "CREATE TABLE IF NOT EXISTS courses ("
                    + "course_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "course_name VARCHAR(100) NOT NULL,"
                    + "duration VARCHAR(50),"
                    + "fee DECIMAL(10,2)"
                    + ")";

            stmt.executeUpdate(coursesTable);
    

            // 3. Enrollments Table
            String enrollmentsTable = "CREATE TABLE IF NOT EXISTS enrollments ("
                    + "enrollment_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "student_id INT,"
                    + "course_id INT,"
                    + "enrollment_date DATE,"
                    + "FOREIGN KEY (student_id) REFERENCES students(student_id),"
                    + "FOREIGN KEY (course_id) REFERENCES courses(course_id)"
                    + ")";

            stmt.executeUpdate(enrollmentsTable);
      

            // 4. Attendance Table
            String attendanceTable = "CREATE TABLE IF NOT EXISTS attendance ("
                    + "attendance_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "student_id INT,"
                    + "course_id INT,"
                    + "attendance_date DATE,"
                    + "status VARCHAR(10),"
                    + "FOREIGN KEY (student_id) REFERENCES students(student_id),"
                    + "FOREIGN KEY (course_id) REFERENCES courses(course_id)"
                    + ")";

            stmt.executeUpdate(attendanceTable);
            

            // 5. Marks Table
            String marksTable = "CREATE TABLE IF NOT EXISTS marks ("
                    + "mark_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "student_id INT,"
                    + "course_id INT,"
                    + "marks DECIMAL(5,2),"
                    + "FOREIGN KEY (student_id) REFERENCES students(student_id),"
                    + "FOREIGN KEY (course_id) REFERENCES courses(course_id)"
                    + ")";

            stmt.executeUpdate(marksTable);
     

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}