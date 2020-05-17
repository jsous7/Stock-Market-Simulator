/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Builders;

import oodp2.Models.Entities.CompanyEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CompanyBuilder {
    
    public static CompanyEntity build(int id, String name, String stockShareSymbol, int stockShareQuantity, int stockShareQuantitySold ){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(id);
        companyEntity.setName(name);
        companyEntity.setStockShareSymbol(stockShareSymbol);
        companyEntity.setStockShareQuantity(stockShareQuantity);
        companyEntity.setStockShareQuantitySold(stockShareQuantitySold);

        return companyEntity; 
    }
}
