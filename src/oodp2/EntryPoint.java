/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2;

import java.util.ArrayList;
import java.util.List;
import oodp2.Models.Entities.CompanyEntity;
import oodp2.Models.Entities.StockShareEntity;
import oodp2.Models.Repositories.StockShareRepository;
import oodp2.Services.Factories.CompanyFactory;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class EntryPoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CompanyFactory factory = new CompanyFactory();
        
        String[] companyData = {
            "1",
            "Adobe",
            "ADBE",
            "100",
            "0"
        };
        CompanyEntity company = factory.create(companyData);
        
        
        StockShareRepository stockShareRepository = new StockShareRepository();
        StockShareEntity stockShare = stockShareRepository.get(company.getStockShareSymbol());
        
        System.out.println(company.getName());
    }
}
