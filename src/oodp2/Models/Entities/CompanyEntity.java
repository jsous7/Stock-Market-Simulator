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
public class CompanyEntity {
    private int id = 0;
    private String name = null;
    private String stockShareSymbol = null;
    private int stockShareQuantity = 0;
    private int stockShareQuantitySold = 0;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockShareSymbol() {
        return stockShareSymbol;
    }

    public void setStockShareSymbol(String stockShareSymbol) {
        this.stockShareSymbol = stockShareSymbol;
    }

    public int getStockShareQuantity() {
        return stockShareQuantity;
    }

    public void setStockShareQuantity(int stockShareQuantity) {
        this.stockShareQuantity = stockShareQuantity;
    }

    public int getStockShareQuantitySold() {
        return stockShareQuantitySold;
    }

    public void setStockShareQuantitySold(int stockShareQuantitySold) {
        this.stockShareQuantitySold = stockShareQuantitySold;
    }
}
