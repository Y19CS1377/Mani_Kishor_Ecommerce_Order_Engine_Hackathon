package service;

import model.Order;
import model.OrderItem;
import model.enums.OrderStatus;
import repository.OrderRepository;
import repository.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdminService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public AdminService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void displayAnalyticsDashboard() {
        System.out.println("\n  ===============================================================");
        System.out.println("  ||                 ADMIN ANALYTICS DASHBOARD                 ||");
        System.out.println("  ===============================================================");

        Collection<Order> allOrders = orderRepository.findAll();
        long totalOrders = allOrders.size();
        
        long successfulOrders = allOrders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID || o.getStatus() == OrderStatus.DELIVERED || o.getStatus() == OrderStatus.SHIPPED)
                .count();

        double totalRevenue = allOrders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID || o.getStatus() == OrderStatus.DELIVERED || o.getStatus() == OrderStatus.SHIPPED)
                .mapToDouble(Order::getTotal)
                .sum();

        double averageOrderValue = successfulOrders > 0 ? (totalRevenue / successfulOrders) : 0.0;

        // Find the most popular product
        Map<String, Integer> productSales = new HashMap<>();
        for (Order order : allOrders) {
            if (order.getStatus() == OrderStatus.PAID || order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.SHIPPED) {
                for (OrderItem item : order.getItems()) {
                    productSales.put(item.getProductName(), productSales.getOrDefault(item.getProductName(), 0) + item.getActiveQuantity());
                }
            }
        }

        String topProduct = "None";
        int topSales = 0;
        for (Map.Entry<String, Integer> entry : productSales.entrySet()) {
            if (entry.getValue() > topSales) {
                topSales = entry.getValue();
                topProduct = entry.getKey();
            }
        }

        int totalUsers = userRepository.findAll().size();
        long flaggedUsers = userRepository.findAll().stream().filter(u -> u.isFlagged()).count();

        System.out.printf("   Total Revenue       : %.2f%n", totalRevenue);
        System.out.printf("   Total Orders Placed : %d%n", totalOrders);
        System.out.printf("   Successful Orders   : %d%n", successfulOrders);
        System.out.printf("   Average Order Value : %.2f%n", averageOrderValue);
        System.out.printf("   Top Selling Product : %s (%d units sold)%n", topProduct, topSales);
        System.out.println("  ---------------------------------------------------------------");
        System.out.printf("   Total Users         : %d%n", totalUsers);
        System.out.printf("   Fraud-Flagged Users : %d%n", flaggedUsers);
        System.out.println("  ===============================================================\n");
    }
}
