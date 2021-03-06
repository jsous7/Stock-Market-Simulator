package oodp2.Services.Builders;

import oodp2.Models.Entities.CompanyEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CompanyBuilder {

    public static CompanyEntity build(int id, String name, String stockShareSymbol, int stockShareQuantity, int stockShareQuantitySold ){
        CompanyEntity companyEntity = new CompanyEntity();
        if (id != 0) {
            companyEntity.setId(id);
        }
        companyEntity.setName(name);
        companyEntity.setStockShareSymbol(stockShareSymbol);
        companyEntity.setStockShareQuantity(stockShareQuantity);
        companyEntity.setStockShareQuantitySold(stockShareQuantitySold);

        return companyEntity; 
    }
    
    
}
