package oodp2.Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.InvestorEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Models.Repositories.CompanyRepository;
import oodp2.Models.Repositories.InvestorRepository;
import oodp2.Models.Repositories.StockShareRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class Simulation {
    
    public static void init(int dummyDataAmount) throws Exception{
        System.out.println("Starting Simulation...");
        Random rand = new Random();
        int randomNumber = 0;
        int[] lastStockShareOperated = new int[10];
        int brokingPerformed = 0;
        
        while (keepRunning()) {
            InvestorRepository investorReposity = new InvestorRepository();
            
            randomNumber = rand.nextInt((dummyDataAmount - 1) + 1) + 1;
                
            System.out.println("random investor number: "+randomNumber);
            InvestorEntity investor = investorReposity.get(randomNumber);
            System.out.println("Investor Selected: "+investor.getName());

            randomNumber = rand.nextInt((dummyDataAmount - 1) + 1) + 1;
            
            System.out.println("random company number: "+randomNumber);
            CompanyRepository companyRepository = new CompanyRepository();
            CompanyEntity company = companyRepository.get(randomNumber);
            System.out.println("Company selected: "+company.getName());

            StockShareRepository stockShareRepositor = new StockShareRepository();
            StockShareEntity stockShare = stockShareRepositor.getByCompanyId(company.getId());

            if(company.hasStockShare()) {
                System.out.println("Broker operation started...");
                Broker.purchaseStockShare(investor, company, stockShare);
                brokingPerformed++;
                lastStockShareOperated[brokingPerformed] = stockShare.getId();          
                if (brokingPerformed == 9 ) {
                    updateAllStockSharePrices(lastStockShareOperated);
                    brokingPerformed = 0;
                }              
            }
        }
        System.out.println("======================================================================");
        System.out.println("Investor run out of credit or companies have no available Stock Shares");
        System.out.println("======================================================================");
    }

    private static void updateAllStockSharePrices(int[] lastStockShareOperated) throws Exception {
        StockShareRepository stockShareRepository = new StockShareRepository();
        ArrayList<StockShareEntity> stockShareCollection = stockShareRepository.getAll();  
        
        

        for (int i=0; i<lastStockShareOperated.length; i++){
            if (stockShareCollection.get(i).getId() != lastStockShareOperated[i]) {
                double currentPrice = stockShareCollection.get(i).getPrice();
                
                double newPrice = currentPrice - ((currentPrice*2)/100);
                String priceString = String.format("%.2f", newPrice);
                priceString = priceString.replace(",", ".");
                double doublePrice = Double.parseDouble(priceString);
                
                stockShareCollection.get(i).setPrice(doublePrice);
                stockShareRepository.saveOrUpdate(stockShareCollection.get(i));
            }
        }
    }
    
    private static boolean keepRunning() throws Exception{
        InvestorRepository investorRepository = new InvestorRepository();
        ArrayList<InvestorEntity> investorCollection = investorRepository.getAll();
        
        StockShareRepository stockShareRepository = new StockShareRepository();
        ArrayList<StockShareEntity> stockShareCollection = stockShareRepository.getAll();
       
        InvestorEntity highestBudgetInvestor = investorCollection
        .stream()
        .max(Comparator.comparing(InvestorEntity::getBudget))
        .orElseThrow(NoSuchElementException::new);
        
        StockShareEntity cheapestStockShare = stockShareCollection
        .stream()
        .min(Comparator.comparing(StockShareEntity::getPrice))
        .orElseThrow(NoSuchElementException::new);
        
        if (highestBudgetInvestor.getBudget() > cheapestStockShare.getPrice()) {
            return true;
        }
        
        return false;
    }
}
