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
public class InvestorEntity {
    private int id = 0;
    private String name = null;
    private int budget = 0;
    
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
    
}
