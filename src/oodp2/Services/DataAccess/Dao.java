package oodp2.Services.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class used to separate low level data accessing operations from high level business services
 * @author Juliana_Sousa <juliana.oli.sousa@gmail.com>
 */
public class Dao {
    
    private Connection db;

    public Dao() throws Exception {
        try {
            this.db = SingletonDb.getInstance();
        } catch (Exception e) {
            throw new Exception("error on getting Db singleton instance: " + e.getMessage());
        }
    }
    
    /**
     * Method to create the insert query dynamically based on the data provided
     * @param table
     * @param dataKeys
     * @param dataValues
     * @throws SQLException
     * @throws Exception 
     */
    public void create(String table, String[] dataKeys, String[] dataValues) throws SQLException, Exception{
        String keys = "";
        String values = "";
        
        for (String element : dataKeys){
            keys = keys + element + ",";        
        }
        //remove the last coma
        keys = keys.substring(0, keys.length()-1);
        //remove the Id field
        keys = keys.substring(3, keys.length());
        
        for (String element : dataValues){
            values = values + "\'" + element + "\'" + ",";
        }
        //remove the last coma
        values = values.substring(0, values.length()-1);
        //remove the Id value
        values = values.substring(4, values.length());
        
        String query = "insert into " + table + "(" + keys + ")" + " values(" + values + ")";

        Statement stmt = this.db.createStatement();
        
        try {
            stmt.execute(query);
        } catch(Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
    }
    
    public void update(String table, String[] dataKeys, String[] dataValues, String id) throws SQLException, Exception{
        String fields = "";

        int i=0;
        for (String element : dataKeys){
            if (element.equals("Id")){
                dataValues[i] = id;
            }
            fields = fields + element + " = \"" + dataValues[i] + "\", ";  
            i++;
        }
        //remove the Id value
        fields = fields.substring(0, fields.length()-2);

        String query = "update " + table + " set " + fields + " where id = " + id;
        
        Statement stmt = this.db.createStatement();

        try {
            stmt.execute(query);
        } catch(SQLException e) {
            throw new Exception("Error while trying to update: " + table + " : " + e.getMessage());
        }
    }
    
    public void delete(String table, String id) throws Exception {
        String query = "delete from " + table + "where id = " + id;
        Statement stmt = this.db.createStatement();
        try {
            stmt.execute(query);
        } catch(Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        } 
    }
    
    /**
     * Method to create the select all query dynamically based on the data provided
     * @param table
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ResultSet getAll(String table) throws SQLException, Exception{
        ResultSet result = null;
        Statement stmt = this.db.createStatement();
        String query = "select * from " + table + " limit 1000";
        try {
            result = stmt.executeQuery(query);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Method to create a query with filters dynamically based on the data provided
     * @param table
     * @param field
     * @param value
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public ResultSet getByField(String table, String field, String value) throws SQLException, Exception{
        ResultSet result = null;
        Statement stmt = this.db.createStatement();
        String query = "select * from " + table + " where " + field + " = \"" + value + "\"";
        try {
            result = stmt.executeQuery(query);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        return result;
    }
    
    public void truncateTable(String table) throws Exception{
        Statement stmt = this.db.createStatement();
        String query = "truncate table " + table;
        try {
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate(query);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
        } catch (Exception e) {
            throw new Exception("Error while truncating table: " + e.getMessage());
        }
    }
}
