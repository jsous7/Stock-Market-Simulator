package oodp2.Services.Factories;

import oodp2.Models.Entities.InvestorEntity;
import oodp2.Services.Builders.InvestorBuilder;
import oodp2.Models.Repositories.InvestorRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class InvestorFactory {
    
    public InvestorEntity create(String name, double budget) throws Exception{
        InvestorEntity investor = InvestorBuilder.build(0, name, budget);
        
        InvestorRepository investorRepository = new InvestorRepository();
        investor = investorRepository.saveOrUpdate(investor);
        
        return investor;
    }
}