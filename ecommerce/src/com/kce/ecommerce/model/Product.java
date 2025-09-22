package com.kce.ecommerce.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public boolean isInStock(int quantity) {
        return quantity > 0 && stock >= quantity;
    }

    public void reduceStock(int quantity) {
        if (quantity > 0 && stock >= quantity) {
            stock -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock or invalid quantity.");
        }
    }

    public void increaseStock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
        } else {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Price: ₹%.2f, Stock: %d", id, name, price, stock);
    }
}
