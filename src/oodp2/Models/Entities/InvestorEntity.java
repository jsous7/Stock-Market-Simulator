package oodp2.Models.Entities;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class InvestorEntity {
    private int id = 0;
    private String name = null;
    private double budget = 0;
    
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    
}
