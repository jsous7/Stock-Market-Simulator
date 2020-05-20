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
import oodp2.Services.Builders.CompanyBuilder;
/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CompanyRepository {
    
    private String tableName = "company";
    
    public CompanyEntity saveOrUpdate(CompanyEntity company) throws IllegalArgumentException, IllegalAccessException, Exception{
        Dao dao = new Dao();
        
        //String arrays required by the Dao
        String[] dataKeys = new String[30];
        String[] dataValues = new String[30];
              
        //Extract key:value pair from the attributes of the entity 'company' and populate the string 
        Field [] attributesKeys =  CompanyEntity.class.getDeclaredFields();
        
        int i = 0;
        for (Field key : attributesKeys) {
            key.setAccessible(true);
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
        
        //Check if the entity already exists, if yes, just update it
        CompanyEntity companyFound;
        try {
            companyFound = this.getByName(company.getName());
            dao.update(this.tableName, dataKeys, dataValues);
        }catch(Exception e){
            if (e.getMessage() == "Company not found") {
                try {
                    dao.create(this.tableName, dataKeys, dataValues);
                    companyFound = this.getByName(company.getName());
                }catch(Exception e2){
                    //Throw error if any problem happens while creating company
                    throw new Exception(e.getMessage());
                }
            } else {
                //Throw error if any problem happens while getting company
                throw new Exception(e.getMessage());
            }        
        }  
        
        return companyFound;
    }
    
    public void delete(int id) throws Exception{
        Dao dao = new Dao();
        dao.delete(this.tableName, String.valueOf(id));
    }
    
    public CompanyEntity get(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "id", String.valueOf(id));
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        CompanyEntity company = null;
        
        if (rs.next()){
            id = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String stockShareSymbol = rs.getString(3);
            int stockShareQuantity = Integer.parseInt(rs.getString(4));
            int stockShareQuantitySold = Integer.parseInt(rs.getString(5));
            
            company = CompanyBuilder.build(id, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
        } else {
            throw new Exception("Company not found");
        }
  
        return company;
        
    }
    
    public CompanyEntity getByName(String name) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "name", name);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        CompanyEntity company = null;
        
        if (rs.next()){
            int id = Integer.parseInt(rs.getString(1));
            name = rs.getString(2);
            String stockShareSymbol = rs.getString(3);
            int stockShareQuantity = Integer.parseInt(rs.getString(4));
            int stockShareQuantitySold = Integer.parseInt(rs.getString(5));
            
            company = CompanyBuilder.build(id, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
        } else {
            throw new Exception("Company not found");
        }
  
        return company;
        
    }
    
    public ArrayList<CompanyEntity> getAll() throws Exception, Exception{
        Dao daoLayer = new Dao();
        ResultSet rs = null;
        
        ArrayList<CompanyEntity> companies = new ArrayList<CompanyEntity>();
        
        try {
            rs = daoLayer.getAll(this.tableName);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        if (rs.next()){
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String stockShareSymbol = rs.getString(3);
                int stockShareQuantity = Integer.parseInt(rs.getString(4));
                int stockShareQuantitySold = Integer.parseInt(rs.getString(5));
                
                CompanyEntity company = CompanyBuilder.build(id, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
                companies.add(company);
            }
        } else {
            throw new Exception("No countries found!");
        }

        return companies;
    }
}
