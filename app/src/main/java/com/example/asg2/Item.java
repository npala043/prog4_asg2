package com.example.asg2;

public class Item {

    private int id;
    private String name;
    private int quantity;
    private double cost;
    private int suppId;

    public Item(int id, String name, int quantity, double cost, int suppId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.suppId = suppId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public int getSuppId() {
        return suppId;
    }
}
