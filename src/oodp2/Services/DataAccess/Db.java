package oodp2.Services.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class using singleton pattern to allow only one DB instance to be used across the project
 * @author Juliana_Sousa <juliana.oli.sousa@gmail.com>
 */
public class Db {
    private static String dsn = "jdbc:mysql://127.0.0.1:3306/stock_share_market_db?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "juky4525.";
    
    private static Connection dbInstance = null;
    
    /**
     * Singleton pattern, returns only one instance of the DB
     * @return
     * @throws Exception 
     */
    public static Connection getInstance() throws Exception{
        if (dbInstance == null){
            try {
                dbInstance = DriverManager.getConnection(dsn, username, password);
            } catch(Exception e) {
                throw new Exception("Error on connecting to DB: " + e.getMessage());
            }
        }
        
        return dbInstance;
    }
}
