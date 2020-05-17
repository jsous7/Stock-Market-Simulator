/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Builders;

import oodp2.Models.Entities.InvestorEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class InvestorBuilder {
    
    public static InvestorEntity build(int id, String name, int budget){
        InvestorEntity investor = new InvestorEntity();
        investor.setId(id);
        investor.setName(name);
        investor.setBudget(budget);
        
        return investor;
    }
}