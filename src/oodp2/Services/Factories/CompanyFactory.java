/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Factories;

import java.util.Random;
import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Models.Repositories.CompanyRepository;
import oodp2.Models.Repositories.StockShareRepository;
import oodp2.Services.Builders.CompanyBuilder;
import oodp2.Services.Builders.StockShareBuilder;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class CompanyFactory {

    public CompanyEntity create(String[] data) throws Exception{
        String name = data[0];
        String stockShareSymbol = data[1];
        int stockShareQuantity = Integer.parseInt(data[2]);
        int stockShareQuantitySold = Integer.parseInt(data[3]);
        
        CompanyEntity company = CompanyBuilder.build(0, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
        CompanyRepository companyRepository = new CompanyRepository();
        company = companyRepository.saveOrUpdate(company);
               
        Random random = new Random();
        int price = random.nextInt(100 - 10) + 10;
        
        StockShareEntity stockShare = StockShareBuilder.build(0, company.getId(), price);
        
        StockShareRepository stockShareRepository = new StockShareRepository();
        stockShare = stockShareRepository.saveOrUpdate(stockShare);

        return company;
    }
}