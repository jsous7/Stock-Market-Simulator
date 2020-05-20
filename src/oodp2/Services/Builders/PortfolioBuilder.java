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
    
    public static PortfolioEntity build(int id, int investor_Id, int stock_share_id, int quantity){
        PortfolioEntity portfolio = new PortfolioEntity();
        if (id != 0) {
            portfolio.setId(id);
        }
        portfolio.setInvestor_Id(investor_Id);
        portfolio.setStock_share_id(stock_share_id);
        portfolio.setQuantity(quantity);
        
        return portfolio;
    }
}