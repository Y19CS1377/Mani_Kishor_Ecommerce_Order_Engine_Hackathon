import cli.MenuHandler;
import model.User;
import repository.*;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Repositories
        ProductRepository productRepository = new ProductRepository();
        CartRepository cartRepository = new CartRepository();
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();

        // Default users
        userRepository.save(new User("101", "Kishor"));
        userRepository.save(new User("102", "Sai"));
        userRepository.save(new User("103", "Venkata"));
        userRepository.save(new User("104", "Mani"));
        userRepository.save(new User("105", "Ram"));

        // Services
        LoggingService loggingService = new LoggingService();
        FailureService failureService = new FailureService();
        InventoryService inventoryService = new InventoryService(productRepository, loggingService);
        EventService eventService = new EventService(loggingService);
        CouponService couponService = new CouponService(cartRepository, loggingService);
        PaymentService paymentService = new PaymentService(loggingService);
        FraudService fraudService = new FraudService(userRepository, loggingService);
        ProductService productService = new ProductService(productRepository, loggingService);
        CartService cartService = new CartService(cartRepository, productRepository, inventoryService, loggingService);
        OrderService orderService = new OrderService(
                orderRepository, cartRepository, userRepository,
                inventoryService, paymentService, couponService,
                fraudService, eventService, loggingService, failureService);

        AdminService adminService = new AdminService(orderRepository, userRepository);

        // Seed some products
        productService.addProduct("iqoo Z7 pro", 19999.00, 20);
        productService.addProduct("realme 12 pro", 24999.00, 5);
        productService.addProduct("Motrola edge 50 fusion", 22999.00, 11);
        productService.addProduct("Poco M6 pro", 12999.00, 10);
        productService.addProduct("Samsung Galaxy M34", 16999.00, 20);

        // CLI
        Scanner scanner = new Scanner(System.in);
        MenuHandler menu = new MenuHandler(scanner, productService, cartService, orderService,
                couponService, loggingService, eventService, failureService, userRepository, adminService);
        menu.run();
        scanner.close();
    }
}
