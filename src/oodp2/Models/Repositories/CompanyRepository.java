/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Models.Repositories;

import oodp2.Models.Entities.CompanyEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import oodp2.Services.DataAccess.Dao;
import java.sql.ResultSet;
/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CompanyRepository {
    
    private String tableName = "company";
    
    public void save(CompanyEntity company) throws IllegalArgumentException, IllegalAccessException, Exception{
        //String arrays required by the Dao
        String[] dataKeys = new String[30];
        String[] dataValues = new String[30];
              
        //Extract key:value pair from the attributes of the entity 'company' and populate the string 
        Field [] attributesKeys =  CompanyEntity.class.getDeclaredFields();
        int i = 0;
        for (Field key : attributesKeys) {
            String keys = key.getName();
            dataKeys[i] = keys.substring(0,1).toUpperCase() + keys.substring(1);
            String values = key.get(company).toString();
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
        
        //Calls Dao Passsing the required data for dynamic construction of the query
        Dao dao = new Dao();
        dao.insert(this.tableName, dataKeys, dataValues);
    }
    
//    public void update(CompanyEntity company){
//        
//    }
//    
//    public void delete(int id){
//        
//    }
    
    public CompanyEntity get(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        String stringId = String.valueOf(id);
        
        try {
            rs = dao.selectByField(this.tableName, "id", stringId);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        CompanyEntity company = new CompanyEntity();
        
        if (rs.next()){
            company.setId(rs.getString(1));
            company.setStockShareSymbol(rs.getString(2));
            company.setStockShareQuantity(rs.getString(3));
            company.setStockShareQuantitySold(rs.getString(4));
        } else {
            throw new Exception("Country not found");
        }
  
        return entityCountry;
        
    }
    
//    public ArrayList<CompanyEntity> getAll(){
//        
//        
//    }
}
