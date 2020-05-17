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
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        int budget = Integer.parseInt(data[2]);
        
        InvestorEntity investor = InvestorBuilder.build(id, name, budget);
        
        InvestorRepository investorRepository = new InvestorRepository();
        investorRepository.save(investor);
        
        return investor;
    }
}