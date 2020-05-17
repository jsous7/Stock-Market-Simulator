/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Factories;

import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.InvestorEntity;
import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Services.Builders.CompanyBuilder;
import oodp2.Services.Builders.StockShareBuilder;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class ObjectFactory {
    
    ArrayList<Object> objectCreated;
    
    public ArrayList<Object> create(String objectName, String[] data){
        
        switch(objectName){
            case "Company":
                this.objectCreated.add(createCompany(data));
                break;
            case "Investor":
                this.objectCreated.add(createInvestor(data));
                break;
            case "Portfolio":
                this.objectCreated.add(createPortfolio(data));
            Default:
                break;
        }
        
        return objectCreated;
    }

    private CompanyEntity createCompany(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String stockShareSymbol = data[2];
        int stockShareQuantity = Integer.parseInt(data[3]);
        int stockShareQuantitySold = Integer.parseInt(data[4]);
        
        CompanyEntity company = CompanyBuilder.build(id, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
        
        Random random = new Random();
        int value = random.nextInt(100 - 10) + 10;
        
        StockShareEntity stockShare = StockShareBuilder.build(id, stockShareSymbol, value);
        
        CompanyRepository.save(company);
        StockShareRepository.save(stockShare);
        
        return company;
    }

    private InvestorEntity createInvestor(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        int budget = Integer.parseInt(data[2]);
        
        InvestorEntity investor = new InvestorEntity();
        investor.setId(id);
        investor.setName(name);
        investor.setBudget(budget);
        
        InvestorRepository.save(investor);
        
        return investor;
         
    }

    private PortfolioEntity createPortfolio(String[] data) {
        int id = Integer.parseInt(data[0]);
        int investor_Id = Integer.parseInt(data[1]);
        String stockShareSymbol = data[2];
        int quantity = Integer.parseInt(data[3]);

        PortfolioEntity portfolio = new PortfolioEntity();
        portfolio.setId(id);
        portfolio.setInvestor_Id(investor_Id);
        portfolio.setStockShareSymbol(stockShareSymbol);
        portfolio.setQuantity(quantity);
        
        PortfolioRepository.save(portfolio);
        
        return portfolio;
    }
    
}
