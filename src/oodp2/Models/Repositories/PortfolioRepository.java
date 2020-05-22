package oodp2.Models.Repositories;

import oodp2.Models.Entities.PortfolioEntity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import oodp2.Services.DataAccess.Dao;
import java.sql.ResultSet;
import oodp2.Services.Builders.PortfolioBuilder;
/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioRepository {
    
    private String tableName = "portfolio";
    
    public PortfolioEntity saveOrUpdate(PortfolioEntity portfolio) throws IllegalArgumentException, IllegalAccessException, Exception{
        Dao dao = new Dao();
        
        //String arrays required by the Dao
        String[] dataKeys = new String[30];
        String[] dataValues = new String[30];
              
        //Extract key:value pair from the attributes of the entity 'company' and populate the string 
        Field [] attributesKeys =  PortfolioEntity.class.getDeclaredFields();
        int i = 0;
        for (Field key : attributesKeys) {
            key.setAccessible(true);
            String keys = key.getName();
            dataKeys[i] = keys.substring(0,1).toUpperCase() + keys.substring(1);
            String values = key.get(portfolio).toString();
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
        PortfolioEntity portfolioFound;
        try {
            portfolioFound = this.get(portfolio.getId());
            dao.update(this.tableName, dataKeys, dataValues, String.valueOf(portfolioFound.getId()));
            portfolioFound = this.get(portfolioFound.getId());
        }catch(Exception e){
            if (e.getMessage() == "Portfolio not found") {
                try {
                    dao.create(this.tableName, dataKeys, dataValues);
                    portfolioFound = this.getByInvestorId(portfolio.getInvestor_Id());
                }catch(Exception e2){
                    //Throw error if any problem happens while creating company
                    throw new Exception(e.getMessage());
                }
            } else {
                //Throw error if any problem happens while getting company
                throw new Exception(e.getMessage());
            }        
        }  
        
        return portfolioFound; 
    }
    
    public void delete(int id) throws Exception{
        Dao dao = new Dao();
        dao.delete(this.tableName, String.valueOf(id));
    }
    
    public PortfolioEntity get(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "id", String.valueOf(id));
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        PortfolioEntity portfolio = null;     
        if (rs.next()){
            id = Integer.parseInt(rs.getString(1));
            int investor_id = Integer.parseInt(rs.getString(2));
            int stock_share_id = Integer.parseInt(rs.getString(3));
            int quantity = Integer.parseInt(rs.getString(4));

            portfolio = PortfolioBuilder.build(id, investor_id, stock_share_id, quantity);
        } else {
            throw new Exception("Portfolio not found");
        }
  
        return portfolio;
        
    }
    
    public ArrayList<PortfolioEntity> getAll() throws Exception, Exception{
        Dao daoLayer = new Dao();
        ResultSet rs = null;
        
        ArrayList<PortfolioEntity> portfolios = new ArrayList<PortfolioEntity>();
        
        try {
            rs = daoLayer.getAll(this.tableName);
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        PortfolioEntity portfolio = null;
        if (rs.next()){
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                int investor_Id = Integer.parseInt(rs.getString(2));
                int stock_share_id = Integer.parseInt(rs.getString(3));
                int quantity = Integer.parseInt(rs.getString(4));

                portfolio = PortfolioBuilder.build(id, investor_Id, stock_share_id, quantity);
            
                portfolios.add(portfolio);
            }
        } else {
            throw new Exception("Portfolio not found!");
        }

        return portfolios;
    }
    
    public PortfolioEntity getByInvestorId(int id) throws Exception{
        Dao dao = new Dao();
        ResultSet rs = null;
        
        try {
            rs = dao.getByField(this.tableName, "investor_id", String.valueOf(id));
        } catch (Exception e) {
            throw new Exception("Error while executing query: " + e.getMessage());
        }
        
        PortfolioEntity portfolio = null;
        
        if (rs.next()){
            id = Integer.parseInt(rs.getString(1));
            int investor_id = Integer.parseInt(rs.getString(2));
            int stock_share_id = Integer.parseInt(rs.getString(3));
            int quantity = Integer.parseInt(rs.getString(4));
            
            portfolio = PortfolioBuilder.build(id, investor_id, stock_share_id, quantity);
        } else {
            throw new Exception("Portfolio not found");
        }
  
        return portfolio;
        
    }
}
