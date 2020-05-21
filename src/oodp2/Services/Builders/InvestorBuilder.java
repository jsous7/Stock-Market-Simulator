package oodp2.Services.Builders;

import oodp2.Models.Entities.InvestorEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class InvestorBuilder {
    
    public static InvestorEntity build(int id, String name, double budget){
        InvestorEntity investor = new InvestorEntity();
        if (id != 0) {
            investor.setId(id);
        }
        investor.setName(name);
        investor.setBudget(budget);
        
        return investor;
    }
}