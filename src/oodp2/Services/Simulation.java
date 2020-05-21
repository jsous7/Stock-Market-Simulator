package oodp2.Services;

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
    
    public static void init() throws Exception{
        boolean stopBrokerage = false;
        int quantityOfOperationFailed = 0;
        
        while (quantityOfOperationFailed <= 10) {
            InvestorRepository investorReposity = new InvestorRepository();
            InvestorEntity investor = new InvestorEntity();
            investor.setId(0);

            boolean investorFound = false;
            int numberOfInvestorSearches = 0;

            while (!investorFound && (numberOfInvestorSearches <= 10)) {
                int randomInvestor = ThreadLocalRandom.current().nextInt(1, 100);
                investor = investorReposity.get(randomInvestor);
                if (investor.hasBudget()) {
                    investorFound = true;
                }

                numberOfInvestorSearches++;
            }
            

            CompanyRepository companyRepository = new CompanyRepository();
            CompanyEntity company = new CompanyEntity();
            company.setId(0);
            
            boolean companyFound = false;
            int numberOfCompanySearches = 0;

            while (!companyFound && (numberOfCompanySearches <= 10)) {
                int randomCompany = ThreadLocalRandom.current().nextInt(1, 100);
                company = companyRepository.get(randomCompany);
                if(company.hasStockShare()){
                    companyFound = true;
                }

                numberOfCompanySearches++;
            }

            if (company.getId() == 0 || investor.getId() == 0){
                stopBrokerage = true;
            }

            StockShareRepository stockShareRepositor = new StockShareRepository();
            StockShareEntity stockShare = stockShareRepositor.getByCompanyId(company.getId());


            if(investor.getBudget() > stockShare.getPrice()) {
                Broker.purchaseStockShare(investor, company);
            }

            quantityOfOperationFailed++;           
        }
        
        Report.generate();
    }
    
}
