package oodp2.Services;

import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.InvestorEntity;
import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Models.Repositories.CompanyRepository;
import oodp2.Models.Repositories.InvestorRepository;
import oodp2.Models.Repositories.PortfolioRepository;
import oodp2.Services.Factories.PortfolioFactory;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
class Broker {

    static void purchaseStockShare(InvestorEntity investor, CompanyEntity company, StockShareEntity stockShare) throws Exception {
        
        if(investor.getBudget() < stockShare.getPrice()){
            return;
        }
                
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        PortfolioEntity portfolioEntity;
        try{
            portfolioEntity  = portfolioRepository.getByInvestorId(investor.getId());
            int newQuantity = portfolioEntity.getQuantity() + 1;
            portfolioEntity.setQuantity(newQuantity);
            portfolioRepository.saveOrUpdate(portfolioEntity);
            System.out.println("Investor already has this portfolio, increasing the quantity...");
            System.out.println("");
        }catch(Exception e){
            if (e.getMessage() == "Portfolio not found") {
                PortfolioFactory portfolioFactory = new PortfolioFactory();
                PortfolioEntity portfolio = portfolioFactory.create(investor.getId(), stockShare.getId(), 1);
                System.out.println("New Portfolio created: "+portfolio.getId());
            } else {
                throw new Exception("Error when creating portfolio: " + e.getMessage());
            }
        }
        
        System.out.println("Updating Quantity of stockShares available by the company "+company.getName());
        
        double newBudget = investor.getBudget() - stockShare.getPrice();
        String budgetString = String.format("%.2f", newBudget);
        budgetString = budgetString.replace(",", ".");
        double doubleBudget = Double.parseDouble(budgetString);
        investor.setBudget(doubleBudget);
        
        InvestorRepository investorRepository = new InvestorRepository();
        investorRepository.saveOrUpdate(investor);
        
        int newStockShareQuantitySold = company.getStockShareQuantitySold() + 1;
        company.setStockShareQuantitySold(newStockShareQuantitySold);
        int newStockShareQuantity = company.getStockShareQuantity() - 1;        
        company.setStockShareQuantity(newStockShareQuantity);
        company.updatePrice();
        
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.saveOrUpdate(company);
    }  
}
