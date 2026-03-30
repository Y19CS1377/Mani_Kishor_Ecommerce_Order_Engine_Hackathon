# E-Commerce Order Engine

Hey there! Welcome to my E-Commerce Order Engine. This is a command-line application written in pure Java. It simulates a real-world e-commerce backend (like Amazon or Flipkart), all tucked into a lightweight console program without any external database dependencies.

I built this to bring together concepts like managing shopping carts, handling inventory, taking orders, and dealing with payment processing. It even mimics real-world scenarios like what happens when multiple people try to buy the last item at the exact same time!

## 🌟 What It Does

- **Product Catalog & Inventory**: Browse products, check stock, and see low-stock alerts.
- **Smart Shopping Cart**: Add and remove items. The system reserves stock instantly so nobody steals your items while you check out.
- **Order Processing**: When you place an order, it automatically processes payments (with simulated success/failure rates) and finalizes your purchase.
- **Coupons & Discounts**: Offers cool auto-discounts when you spend a certain amount, plus manual coupon codes (like `SAVE10` or `FLAT200`).
- **Cancellations & Returns**: If you change your mind, you can cancel an order or return items to get a refund and restock the inventory.
- **Concurrent Users Simulation**: We can simulate multiple users jumping in at once to test how perfectly the system locks and manages limited stock.

## How to Run It

It's super easy to get started. Just make sure you have **Java 17 or higher** installed on your machine. 

### If you're on Windows (PowerShell):
1. Open up PowerShell in the project folder.
2. Navigate to the source folder and compile all the Java files:
   ```powershell
   cd src
   javac -d ../out (Get-ChildItem -Recurse -Filter *.java).FullName
   ```
3. Run the application:
   ```powershell
   cd ../out
   java Main
   ```

### If you're on Linux / macOS (or using Git Bash):
There's a handy script ready for you! Just run:
```bash
./run.sh
```

## How to Play Around With It

When the app starts, you'll be greeted with a nice numbered menu. All you have to do is type the number of the action you want to perform and hit Enter.

**Things to try out:**
1. Hit **2** to check out what's currently in the store.
2. Hit **3** to throw a product in your cart.
3. Keep an eye out for discounts using **6**.
4. Ready? Press **7** to place your order!
5. Feel free to switch between different dummy users by typing `u 102` (or simply select different IDs like `101`, `103`, etc.) at the prompt to give them their own fresh shopping carts.

Have fun exploring the code and playing with the application!
