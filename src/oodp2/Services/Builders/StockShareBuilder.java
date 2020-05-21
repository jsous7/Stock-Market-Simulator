package oodp2.Services.Builders;

import oodp2.Models.Entities.StockShareEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class StockShareBuilder {
    
    public static StockShareEntity build(int id, int company_id, int price){
        StockShareEntity stockShare = new StockShareEntity();
        if (id != 0) {
            stockShare.setId(id);
        }
        stockShare.setCompany_id(company_id);
        stockShare.setPrice(price);
        
        return stockShare; 
    }
}
