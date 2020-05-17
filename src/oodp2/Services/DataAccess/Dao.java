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
            this.db = Db.getInstance();
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
        keys = keys.substring(0, keys.length()-1);
        for (String element : dataValues){
            values = values + "\'" + element + "\'" + ",";
        }
        values = values.substring(0, values.length()-1);
        
        String query = "insert into " + table + "(" + keys + ")" + " values(" + values + ")";
        Statement stmt = this.db.createStatement();
        try {
            stmt.execute(query);
        } catch(Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
    }
    
    public void update(String table, String[] dataKeys, String[] dataValues){
        //TODO:
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
}
