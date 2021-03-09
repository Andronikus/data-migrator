package pt.andronikus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBHandler {
    public static void getConnection(){
        try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.112.97.223:1521/sldesdp","PTIN_ADMIN", "PTIN_ADMIN")) {
            if(conn != null){
                System.out.println("Oh yes! I am connected do DB!");
            }else {
                System.out.println("Kaput! Connection not available");
            }
        }catch (SQLException e){
            System.err.format("Sql state: %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
