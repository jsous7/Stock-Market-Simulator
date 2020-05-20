/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2;

import java.util.ArrayList;
import java.util.List;
import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.InvestorEntity;
import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Models.Repositories.StockShareRepository;
import oodp2.Services.Factories.CompanyFactory;
import oodp2.Services.Factories.InvestorFactory;
import oodp2.Services.Factories.PortfolioFactory;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class EntryPoint {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        CompanyFactory factory = new CompanyFactory();
        
        String[] companyData = {"Adobe", "ADBE", "100", "0"};
        CompanyEntity company = factory.create(companyData);
        
        
        StockShareRepository stockShareRepository = new StockShareRepository();
        StockShareEntity stockShare = stockShareRepository.getByCompanyId(company.getId());
        
        InvestorFactory investorFactory = new InvestorFactory();
        String[] investorData = {"Juliana", "299"};
        InvestorEntity investor = investorFactory.create(investorData);
        
        PortfolioFactory portfolioFactory = new PortfolioFactory();
        int[] portfolioData = {investor.getId(), stockShare.getId(), 299};
        PortfolioEntity portfolio = portfolioFactory.create(portfolioData);
        
        System.out.println(company.getId());
        System.out.println(company.getName());
        System.out.println(stockShare.getId());
        System.out.println(stockShare.getCompany_id());
        System.out.println(stockShare.getPrice());
        System.out.println("-");
        System.out.println(investor.getId());
        System.out.println(investor.getName());
        System.out.println(investor.getBudget());
        System.out.println("-");
        System.out.println(portfolio.getId());
        System.out.println(portfolio.getInvestor_Id());
        System.out.println(portfolio.getStock_share_id());
        System.out.println(portfolio.getQuantity());
    }
}
