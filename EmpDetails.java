import java.sql.*;
import java.util.*;
class EmpDetails{
       static  String url="jdbc:mysql://localhost:3306/mits";
       static String user="root";
       static String pass="Jyothi@2006";
       static Scanner sc=new Scanner(System.in);
    public static void main(String args[]) throws SQLException{
        // try(Connection conn= DriverManager.getConnection(url,user,pass)) {
        // Statement s= conn.createStatement();
        //   int t=s.executeUpdate("create table emp2(emp_id int primary key,emp_name varchar(30),job_role varchar(20),hiredate varchar(20),salary int)");
        //    System.out.println("table created successfully");
        // }
        while(true){
          System.out.println("1. Add emp details");
          System.out.println("2. update Emp details");
          System.out.println("3. display details");
          System.out.println("4. delete ");
          System.out.println("5.display column");
          System.out.println("6. Exit");
          System.out.println("Enter the number(1-6)");
          System.out.println();
          int choice=sc.nextInt();
          switch(choice){
            case 1:
                addEmp();
                break;
            case 2:
                update();
                break;
            case 3:
                display();
                break;
            case 4:
                delete();
                break;
            case 5:
                displayCol();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Completed");
 }
            
        }
              
          }
       
    static void addEmp() throws SQLException{
          try(Connection c=DriverManager.getConnection(url,user,pass)){
            PreparedStatement s=c.prepareStatement("insert into emp2 values(?,?,?,?,?)");
           System.out.println("Enter id:");
           s.setInt(1,sc.nextInt());
           System.out.println("Enter name");
           s.setString(2,sc.next());
           System.out.println("Enter job role");
           s.setString(3,sc.next());
           System.out.println("Enter hire date");
           s.setString(4, sc.next());
           System.out.println("Enter salary");
           s.setInt(5,sc.nextInt());
           int rows = s.executeUpdate();

            if (rows > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }
    }

}
static void update() throws SQLException {

        try (Connection c = DriverManager.getConnection(url, user, pass)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            int salary = sc.nextInt();

            PreparedStatement ps =
                    c.prepareStatement("update emp2 set salary=? where emp_id=?");

            ps.setInt(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Employee Updated Successfully.");
            else
                System.out.println("Employee Not Found.");
            display();
        }
    }
static void display() throws SQLException{
    System.out.println("ID |Name |Job Role |Hire Date |salary ");
    System.out.println("-----------------------------");
    try(Connection c=DriverManager.getConnection(url,user,pass)){
        Statement st=c.createStatement();
        ResultSet rs= st.executeQuery("select *from emp2");
                while(rs.next()){
                    System.out.println(rs.getInt("emp_id")+" "+rs.getString("emp_name")+" "+rs.getString("job_role")+" "+(rs.getString("hiredate")+" "+rs.getInt("salary")));
                }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    System.out.println("--------------------------");
}
 static void delete() throws SQLException {

        try (Connection c = DriverManager.getConnection(url, user, pass)) {

            System.out.print("Enter Employee ID to Delete: ");
            int id = sc.nextInt();

            PreparedStatement ps =
                    c.prepareStatement("delete from emp2 where emp_id=?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Employee Deleted Successfully.");
            else
                System.out.println("Employee Not Found.");
        }
    }

static void displayCol() throws SQLException{

    System.out.println("Enter column name(emp_id,emp_name,job_role,hiredate,salary");
    String colName=sc.next();
   
     try (Connection c = DriverManager.getConnection(url, user, pass)) {

            String sql = "select " + colName + " from emp2";
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n" + colName);

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e) {
            System.out.println("Invalid Column Name.");
        }
    }
 

static void exit(){
    System.exit(0);
}
}