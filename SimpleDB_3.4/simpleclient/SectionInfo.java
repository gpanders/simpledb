package simpleclient;

import java.sql.*;
import java.util.Scanner;
import simpledb.jdbc.network.NetworkDriver;

class Result {
   int studentCount;
   int numberOfAs;

   public Result() {
      this.studentCount = 0;
      this.numberOfAs = 0;
   }
}

public class SectionInfo {
   public static void main(String[] args) {
      Driver d = new NetworkDriver();
      try (Connection conn = d.connect("jdbc:simpledb://localhost", null)) {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter a section number: ");
         String section = sc.nextLine().trim();
         sc.close();

         String prof = getProf(conn, section);
         if (prof == null) {
            return;
         }

         Result result = getStudentsAndNumberOfAs(conn, section);
         System.out.printf("Professor %s had %d students and gave %d A's.\n", prof, result.studentCount, result.numberOfAs);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private static String getProf(Connection conn, String section) throws SQLException {
      String q = "SELECT prof FROM section WHERE sectid = ";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(q + section);
      if (!rs.next()) {
         System.out.println("No such section.");
         return null;
      }

      String prof = rs.getString("prof");
      rs.close();
      return prof;
   }

   private static Result getStudentsAndNumberOfAs(Connection conn, String section) throws SQLException {
      Result result = new Result();
      String q = "SELECT grade FROM enroll WHERE sectionid = ";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(q + section);

      while (rs.next()) {
         result.studentCount++;
         String grade = rs.getString("grade");
         if ("A".equals(grade)) {
            result.numberOfAs++;
         }
      }
      rs.close();

      return result;
   }
}
