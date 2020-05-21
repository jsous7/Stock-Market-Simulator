package oodp2.Services.Factories;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
               
        double price = ThreadLocalRandom.current().nextDouble(10.00, 100.00);
        String priceString = String.format("%.2f", price);
        priceString = priceString.replace(",", ".");
        double doublePrice = Double.parseDouble(priceString);
        
        StockShareEntity stockShare = StockShareBuilder.build(0, company.getId(), doublePrice);
        
        StockShareRepository stockShareRepository = new StockShareRepository();
        stockShare = stockShareRepository.saveOrUpdate(stockShare);

        return company;
    }
}