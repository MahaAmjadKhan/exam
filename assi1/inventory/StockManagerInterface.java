package inventory;

import java.util.ArrayList;

public interface StockManagerInterface {
    /**
     * Receive a delivery of a particular product.
     * Increase the quantity of the product by the given amount.
     *
     * @param id     The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    void delivery(int id, int amount);

    /**
     * Try to find a product in the stock with the given id.
     *
     * @return The identified product, or null if there is none
     * with a matching ID.
     */
    Product findProduct(int id);

    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     *
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    int numberInStock(int id);

    /**
     * Add a product to the list.
     *
     * @param item The item to be added.
     */
    void addProduct(Product item);

    /**
     * Try to find a product in the stock with the given name.
     *
     * @return The identified product, or null if there is none
     * with a matching name.
     */
    Product findProduct(String name);

    /**
     * Return arraylist of all the products which has stock
     * levels below the given amount
     */
    ArrayList<String> lowStockProducts(int upperLimit);

    /**
     * Add the products which has stock
     * levels below the given amount to the onOrder list, but
     * only if they are not already on the onOrder list.
     * Change the quantity field in onOrder list to the difference between the current
     * number on hand and the stockTarget
     */
    void orderStockProducts(int stockTarget);


    void loadSampleData(String[][] data);

    /**
     * Return the number of different types of stock items
     * in stock list.
     */
    int productCount();

    /**
     * Try to find a product in the orders with the given name.
     *
     * @return The identified product, or null if there is none
     * with a matching name.
     */
    Product findOrder(String name);
}
