/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Factories;

import oodp2.Models.Entities.InvestorEntity;
import oodp2.Services.Builders.InvestorBuilder;
import oodp2.Models.Repositories.InvestorRepository;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class InvestorFactory {
    
    public InvestorEntity create(String[] data) throws Exception{
        String name = data[0];
        int budget = Integer.parseInt(data[1]);
        
        InvestorEntity investor = InvestorBuilder.build(0, name, budget);
        
        InvestorRepository investorRepository = new InvestorRepository();
        investor = investorRepository.saveOrUpdate(investor);
        
        return investor;
    }
}