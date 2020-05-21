package oodp2.Services.Factories;

import oodp2.Models.Entities.PortfolioEntity;
import oodp2.Services.Builders.PortfolioBuilder;
import oodp2.Models.Repositories.PortfolioRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioFactory {
    
    public PortfolioEntity create(int[] data) throws Exception{
        int investor_id = data[0];
        int stock_share_id = data[1];
        int quantity = data[2];

        PortfolioEntity portfolio = PortfolioBuilder.build(0, investor_id, stock_share_id, quantity);
        
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolio = portfolioRepository.saveOrUpdate(portfolio);
        
        return portfolio;
    }
}