package com.kce.ecommerce.main;

import com.kce.ecommerce.model.*;
import com.kce.ecommerce.service.Order;
import com.kce.ecommerce.util.IDGenerator;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Product> products = new HashMap<>();
    static Map<String, Customer> customers = new HashMap<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Product\n2. Add Customer\n3. Place Order\n4. Make Payment\n5. Ship Order\n6. Request Return\n7. Display Products\n8. Exit");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> addCustomer();
                case 3 -> placeOrder();
                case 4 -> makePayment();
                case 5 -> shipOrder();
                case 6 -> requestReturn();
                case 7 -> displayProducts();
                case 8 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addProduct() {
        String id = IDGenerator.generateID();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt(); sc.nextLine();
        products.put(id, new Product(id, name, price, stock));
        System.out.println("Product added with ID: " + id);
    }

    static void addCustomer() {
        String id = IDGenerator.generateID();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        customers.put(id, new Customer(id, name, email));
        System.out.println("Customer added with ID: " + id);
    }

    static void placeOrder() {
        String orderId = IDGenerator.generateID();
        System.out.print("Customer ID: ");
        String custId = sc.nextLine();
        Customer cust = customers.get(custId);
        if (cust == null) {
            System.out.println("Customer not found.");
            return;
        }

        Order order = new Order(orderId, cust);
        while (true) {
            System.out.print("Product ID (or 'done'): ");
            String pid = sc.nextLine();
            if (pid.equalsIgnoreCase("done")) break;
            Product p = products.get(pid);
            if (p == null) {
                System.out.println("Product not found.");
                continue;
            }
            System.out.print("Quantity: ");
            int qty = sc.nextInt(); sc.nextLine();
            order.addItem(p, qty);
        }
        orders.add(order);
        System.out.println("Order placed with ID: " + orderId);
    }

    static void makePayment() {
        System.out.print("Order ID: ");
        String oid = sc.nextLine();
        for (Order o : orders) {
            if (oid.equals(o.getOrderId())) {
                String pid = IDGenerator.generateID();
                o.makePayment(pid);
                System.out.println("Payment processed.");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    static void shipOrder() {
        System.out.print("Order ID: ");
        String oid = sc.nextLine();
        for (Order o : orders) {
            if (oid.equals(o.getOrderId())) {
                String sid = IDGenerator.generateID();
                o.shipOrder(sid);
                System.out.println("Order shipped.");
                return;
            }
        }
        System.out.println("Order not found.");
    }

    static void requestReturn() {
        System.out.print("Order ID: ");
        String oid = sc.nextLine();
        for (Order o : orders) {
            if (oid.equals(o.getOrderId())) {
                System.out.print("Reason for return: ");
                String reason = sc.nextLine();

                ReturnRequest request = new ReturnRequest(o, reason);

                if (o.getPayment() != null && o.getPayment().isSuccessful()
                        && o.getShipment() != null && "Shipped".equals(o.getShipment().getStatus())) {
                    request.approveReturn();
                    System.out.println("Return approved. Stock updated.");
                } else {
                    request.rejectReturn();
                    System.out.println("Return rejected. Order not eligible.");
                }

                System.out.println("Return Status: " + request.getStatus());
                return;
            }
        }
        System.out.println("Order not found.");
    }

    static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("Available Products:");
        for (Product p : products.values()) {
            System.out.println("ID: " + p.getId() +
                    ", Name: " + p.getName() +
                    ", Price: ₹" + p.getPrice() +
                    ", Stock: " + p.getStock());
        }
    }
}
