import java.sql.*;
import java.util.Scanner;

public class Jdbc {
    public static void main(String[] args) throws SQLException {
        int menu, empid, k;
        boolean choice = true;
        String ename, mob, email;
        String jdbcUrl = "jdbc:mysql://localhost:3306/Practical";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        Statement result = connection.createStatement();
        Scanner scan = new Scanner(System.in);
        while (choice) {
            System.out.println(" 1.Press 1 to add new record " +
                    "\n 2.Press 2 to update record by EMP_ID" +
                    "\n 3.Press 3 to view record by EMP_ID " +
                    "\n 4.Press 4 to show all record " +
                    "\n 5.Press 5 to exit");
            menu = scan.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("Enter the number of data you want to insert");
                    int n = scan.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter Employee_Id : ");
                        empid = scan.nextInt();
                        System.out.println("Enter Emp_Name : ");
                        ename = scan.next();
                        System.out.println("Enter Mobile Number : ");
                        mob = scan.next();
                        System.out.println("Enter email : ");
                        email = scan.next();
                        //String command = "insert into Student(rollNo,StuName,sec,Cpi) values(rln,stnm,sec,Scpi)";
                        k = result.executeUpdate("insert into EMPLOYEE values(" + empid + ",\"" + ename + "\",\"" + mob + "\",\"" + email + "\")");
                        //k = result.executeUpdate(command);
                        if (k > 0) {
                            System.out.println("Record Inserted Successfully................");
                        }
                    }
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("Enter the ID of the student on which you want to perform update operation : ");
                    empid = scan.nextInt();
                    System.out.println("""
                            Enter the name of the field you want to update :\s
                             1 for name
                             2 for mob\s
                             3 for email""");
                    k = scan.nextInt();
                    if (k == 1) {
                        System.out.println("Enter the new name : ");
                        var k1 = scan.next();
                        k = result.executeUpdate("UPDATE EMPLOYEE SET ENAME = \"" + k1 + "\"  WHERE EMP_ID =" + empid);
                    }
                    if (k == 2) {
                        System.out.println("Enter the new mobile : ");
                        var k1 = scan.next();
                        k = result.executeUpdate("UPDATE EMPLOYEE SET MOBILE = \"" + k1 + "\"  WHERE EMP_ID =" + empid);
                    }
                    if (k == 3) {
                        System.out.println("Enter the new email : ");
                        var k1 = scan.next();
                        k = result.executeUpdate("UPDATE EMPLOYEE SET EMAIL = " + k1 + "  WHERE EMP_ID =" + empid);
                    }
                    if (k == 1)
                        System.out.println("\nUpdated Successfully\n");
                    System.out.println("______________________");
                    break;


                case 3:
                    System.out.println("Enter EMP_ID to view details");
                    empid = scan.nextInt();
                    ResultSet resultSet = result.executeQuery("SELECT * from EMPLOYEE where EMP_ID=" + empid);
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1) + "\t    " + resultSet.getString(2) + "\t  " + resultSet.getString(3) + "\t      " + resultSet.getString(4));
                    }
                    System.out.println(".............................................");
                    break;


                case 4:
                    System.out.println(" EMP_ID    |    NAME  |   MOBILE  |   EMAIL ");
                    ResultSet resultSet1 = result.executeQuery("select * from EMPLOYEE");
                    while (resultSet1.next()) {
                        System.out.println(resultSet1.getInt(1) + "\t    " + resultSet1.getString(2) + "\t  " + resultSet1.getString(3) + "\t      " + resultSet1.getString(4));
                    }
                    System.out.println(".............................................");
                    break;

                case 5:
                    choice = false;
                    System.out.println(".............No more operations, Process exited..............");
                    break;
            }
        }
    }
}
