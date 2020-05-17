/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Builders;

import oodp2.Models.Entities.PortfolioEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioBuilder {
    
    public static PortfolioEntity build(int id, int investor_Id, String stockShareSymbol, int quantity){
        PortfolioEntity portfolio = new PortfolioEntity();
        portfolio.setId(id);
        portfolio.setInvestor_Id(investor_Id);
        portfolio.setStockShareSymbol(stockShareSymbol);
        portfolio.setQuantity(quantity);
        
        return portfolio;
    }
}