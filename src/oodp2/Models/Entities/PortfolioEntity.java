/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodp2.Models.Entities;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class PortfolioEntity {
    private int id = 0;
    private int investor_Id = 0; //foreign key 
    private String stockShareSymbol = null; //foreign key
    private int quantity = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvestor_Id() {
        return investor_Id;
    }

    public void setInvestor_Id(int investor_Id) {
        this.investor_Id = investor_Id;
    }

    public String getStockShareSymbol() {
        return stockShareSymbol;
    }

    public void setStockShareSymbol(String stockShareSymbol) {
        this.stockShareSymbol = stockShareSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  
    
}
