/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Services.Builders;

import oodp2.Models.Entities.StockShareEntity;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class StockShareBuilder {
    
    public static StockShareEntity build(int id, String stockShareSymbol, int price ){
        StockShareEntity stockShare = new StockShareEntity();
        stockShare.setId(id);
        stockShare.setSymbol(stockShareSymbol);
        stockShare.setPrice(price);
        
        return stockShare;
        
    }
}
