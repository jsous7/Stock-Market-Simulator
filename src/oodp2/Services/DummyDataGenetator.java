package oodp2.Services;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import oodp2.Models.Repositories.CleanDbDataRepository;
import oodp2.Services.Factories.CompanyFactory;
import oodp2.Services.Factories.InvestorFactory;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class DummyDataGenetator {

    public static void generate() throws Exception {
        cleanDbData();
        generateCompany(100);
        generateInvestor(100);
    }

    private static void generateCompany(int quantity) throws Exception {
        for(int i=0; i<quantity; i++){
            String uuid = UUID.randomUUID().toString().substring(0, 4);
            String[] companyData = {
            "Company_" + uuid,
            "Share_" + uuid,
            String.valueOf(ThreadLocalRandom.current().nextInt(500, 1000)),
            "0"
        };
        
        CompanyFactory factory = new CompanyFactory();
        factory.create(companyData);
        } 
    }

    private static void generateInvestor(int quantity) throws Exception {
        for(int i=0; i<quantity; i++){
            String uuid = UUID.randomUUID().toString().substring(0, 4);
            
            double budget = ThreadLocalRandom.current().nextDouble(1000.00, 10000.00);
            String budgetString = String.format("%.2f", budget);
            budgetString = budgetString.replace(",", ".");
            double doubleBudget = Double.parseDouble(budgetString);

            InvestorFactory investorFactory = new InvestorFactory();
            investorFactory.create("Investor_"+uuid, doubleBudget);        
        }
    }
    
    private static void cleanDbData() throws Exception {
        CleanDbDataRepository cleanDbData = new CleanDbDataRepository();
        cleanDbData.cleanAll();
    }
}
