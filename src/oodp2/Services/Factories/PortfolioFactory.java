/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Factories;

import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Services.Builders.PortfolioBuilder;
import oodp2.Models.Repositories.PortfolioRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioFactory {
    
    public PortfolioEntity create(String objectName, String[] data) throws Exception{
         int id = Integer.parseInt(data[0]);
        int investor_Id = Integer.parseInt(data[1]);
        String stockShareSymbol = data[2];
        int quantity = Integer.parseInt(data[3]);

        PortfolioEntity portfolio = PortfolioBuilder.build(id, investor_Id, stockShareSymbol, quantity);
        
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.save(portfolio);
        
        return portfolio;
    }
}