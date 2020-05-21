/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Models.Repositories;

import oodp2.Models.Entities.StockShareEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import oodp2.Services.DataAccess.Dao;
import java.sql.ResultSet;
import oodp2.Models.Entities.CompanyEntity;
import oodp2.Services.Builders.CompanyBuilder;
import oodp2.Services.Builders.StockShareBuilder;
/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class StockShareRepository {
    
    private String tableName = "stock_share";
    
    public StockShareEntity saveOrUpdate(StockShareEntity stockShare) throws IllegalArgumentException, IllegalAccessException, Exception{
        Dao dao = new Dao();
        
        //String arrays required by the Dao
        String[] dataKeys = new String[30];
        String[] dataValues = new String[30];
              
        //Extract key:value pair from the attributes of the entity 'company' and populate the string 
        Field [] attributesKeys =  StockShareEntity.class.getDeclaredFields();
        int i = 0;
        for (Field key : attributesKeys) {
            key.setAccessible(true);
            String keys = key.getName();
            dataKeys[i] = keys.substring(0,1).toUpperCase() + keys.substring(1);
            String values = key.get(stockShare).toString();
            dataValues[i] = values.substring(0,1).toUpperCase() + values.substring(1);
            i++;
        }
        
        //Remove the null values from the string arrays (snipet based on stackoverflow)
        dataValues = Arrays.stream(dataValues)
            .filter(new_string -> (new_string != null && new_string.length() > 0))
            .toArray(String[]::new);
        
        dataKeys = Arrays.stream(dataKeys)
            .filter(new_string -> (new_string != null && new_string.length() > 0))
            .toArray(String[]::new);
        
        //Check if the entity already exists, if yes, just update it
        StockShareEntity stockShareFound;
        try {
            stockShareFound = this.getByCompanyId(stockShare.getCompany_id());
            dao.update(this.tableName, dataKeys, dataValues, String.valueOf(stockShareFound.getId()));
            stockShareFound = this.get(stockShareFound.getId());
        }catch(Exception e){
            if (e.getMessage() == "StockShare not found") {
                try {
                    dao.create(this.tableName, dataKeys, dataValues);
                    stockShareFound = this.getByCompanyId(stockShare.getCompany_id());
                }catch(Exception e2){
                    //Throw error if any problem happens while creating company
                    throw new Exception(e.getMessage());
                }
            } else {
                //Throw error if any problem happens while getting company
                throw new Exception(e.getMessage());
            }        
        }  
        
        return stockShareFound; 
    }
    
    public void delete(int id) throws Exception{
        Dao dao = new Dao();
        dao.delete(this.tableName, String.valueOf(id));
    }
    
    public StockShareEntity get(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "id", String.valueOf(id));
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        StockShareEntity stockShare = null;     
        if (rs.next()){
            id = Integer.parseInt(rs.getString(1));
            int company_id = Integer.parseInt(rs.getString(2));
            int price = Integer.parseInt(rs.getString(3));
            
            stockShare = StockShareBuilder.build(id, company_id, price);
        } else {
            throw new Exception("StockShare not found");
        }
  
        return stockShare;
        
    }
    
    public StockShareEntity getByCompanyId(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "company_id", String.valueOf(id));
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        StockShareEntity stockShare = null;
        
        if (rs.next()){
            id = Integer.parseInt(rs.getString(1));
            int company_id = Integer.parseInt(rs.getString(2));
            int price = Integer.parseInt(rs.getString(3));
            
            stockShare = StockShareBuilder.build(id, company_id, price);
        } else {
            throw new Exception("StockShare not found");
        }
  
        return stockShare;
        
    }
    
    public ArrayList<StockShareEntity> getAll() throws Exception, Exception{
        Dao daoLayer = new Dao();
        ResultSet rs = null;
        
        ArrayList<StockShareEntity> stockShares = new ArrayList<StockShareEntity>();
        
        try {
            rs = daoLayer.getAll(this.tableName);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        StockShareEntity stockShare = null;
        if (rs.next()){
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                int company_id = Integer.parseInt(rs.getString(2));
                int price = Integer.parseInt(rs.getString(3));

                stockShare = StockShareBuilder.build(id, company_id, price);
            
                stockShares.add(stockShare);
            }
        } else {
            throw new Exception("No Stock Shares found!");
        }

        return stockShares;
    }
}
