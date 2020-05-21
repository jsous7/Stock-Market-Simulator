package oodp2.Services;

import java.util.Random;
import java.util.UUID;
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
            Random random = new Random();
            String uuid = UUID.randomUUID().toString().substring(0, 4);
            String[] companyData = {
            "Company_" + uuid,
            "Share_" + uuid,
            String.valueOf(random.nextInt(1000 - 500) + 500),
            "0"
        };
        
        CompanyFactory factory = new CompanyFactory();
        factory.create(companyData);
        } 
    }

    private static void generateInvestor(int quantity) throws Exception {
        for(int i=0; i<quantity; i++){
            Random random = new Random();
            String uuid = UUID.randomUUID().toString().substring(0, 4);
            String[] investorData = {
                "Investor_"+uuid,
                String.valueOf(random.nextInt(10000 - 1000) + 1000)
            };

            InvestorFactory investorFactory = new InvestorFactory();
            investorFactory.create(investorData);        
        }
    }
    
    private static void cleanDbData() throws Exception {
        CleanDbDataRepository cleanDbData = new CleanDbDataRepository();
        cleanDbData.cleanAll();
    }
}
