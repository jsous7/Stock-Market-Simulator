package oodp2.Services.Factories;

import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Services.Builders.PortfolioBuilder;
import oodp2.Models.Repositories.PortfolioRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioFactory {
    
    public PortfolioEntity create(int investor_id, int stock_share_id, int quantity) throws Exception{

        PortfolioEntity portfolio = PortfolioBuilder.build(0, investor_id, stock_share_id, quantity);
        
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolio = portfolioRepository.saveOrUpdate(portfolio);
        
        return portfolio;
    }
}