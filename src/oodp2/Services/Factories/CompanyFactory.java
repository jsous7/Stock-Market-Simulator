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
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String stockShareSymbol = data[2];
        int stockShareQuantity = Integer.parseInt(data[3]);
        int stockShareQuantitySold = Integer.parseInt(data[4]);
        
        CompanyEntity company = CompanyBuilder.build(id, name, stockShareSymbol, stockShareQuantity, stockShareQuantitySold);
        
        Random random = new Random();
        int value = random.nextInt(100 - 10) + 10;
        
        StockShareEntity stockShare = StockShareBuilder.build(id, stockShareSymbol, value);

        CompanyRepository companyRepository = new CompanyRepository();
//        companyRepository.save(company);
        
        StockShareRepository stockShareRepository = new StockShareRepository();
//        stockShareRepository.save(stockShare);

        return company;
    }
}