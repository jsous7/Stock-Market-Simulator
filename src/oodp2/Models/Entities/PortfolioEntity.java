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
    private String stock_share_id = null; //foreign key
    private int quantity = 0;
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getInvestor_Id() {
        return investor_Id;
    }

    public void setInvestor_Id(int investor_Id) {
        this.investor_Id = investor_Id;
    }

    public String getStock_share_id() {
        return stock_share_id;
    }

    public void setStock_share_id(String stock_share_id) {
        this.stock_share_id = stock_share_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  

  
    
}
