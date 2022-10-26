package inventory;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author (name:maha    studentid:202055182) 
 * @version (a version number or a date)
 */
public class StockManager implements StockManagerInterface
{
    // A list of the products.
    private ArrayList<Product> stock;
    private ArrayList<Product> onOrder;
    private Product test;

    /**
     * Initialise the stock manager.
     */
    public StockManager()
    {
        stock = new ArrayList<>();
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {
        //System.out.println(stock);
        stock.add(item);
        //System.out.println(stock);
        
    }
    
    /**
     * Receive a delivery of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param id The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    public void delivery(int id, int amount)
    {
        for(Product p : stock){
            if(p.getID() == id){
                p.increaseQuantity(amount);
            }
        }
        
    }
    
    /**
     * Try to find a product in the stock with the given id.
     * @return The identified product, or null if there is none
     *         with a matching ID.
     */
    public Product findProduct(int id)
    {
        /*Iterator it = stock.iterator();
        System.out.println(stock.size());
        for(int i=0;i < stock.size(); i++){
            if(id == stock.get(i).getID()){
                System.out.println(stock.get(i));
                return stock.get(i);
            
            }
            else{
                return null;
            
            }
        }*/
        
        /*while(it.hasNext()){
            System.out.println(it.next());
        }*/
        
        
        //System.out.print(stock.size());
        
        for(int n = 0; n < stock.size(); n++){
            if(id == stock.get(n).getID()){
                //System.out.println(stock.get(n));
                return stock.get(n);
            
            };
        }
        /*id = 132;
        for(Product p : stock){
            
            if(p.getID() == id){
                System.out.println(p);
                return p;
            
            }
             
        }*/
        
        return null;
    }
    
    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    public int numberInStock(int id)
    {
        for(Product p : stock){
            if(p.getID()==id){
                p.getQuantity();
            }
            
        }
        return 0;
    }

    /**
     * Print details of all the products.
     */
    public void printProductDetails()
    {
        for(Product p : stock){
            p.toString();
        }
    }
    
   /**
     * Return the number of different types of stock items
     * in stock list. Added by E Brown.
     */
    public int productCount() {
        return stock.size();
    }
    public Product findOrder(String name){
        for(Product p : onOrder){
            //System.out.println(p);
            
            if(p.getName().equals(name)){
                return p;
            }
        
        }
        return null;
    }
    public void loadSampleData(String[][] data){
        
        /*String[][] trail = {{"3","toy","45"},{"56","car","5"}};
        int first = 0;
        int second = 0;*/
        
        for(int n = 0; n < data.length; n++){
            
            //first++;
            for(int j = 0; j < 1; j++){
               // second++;
                                
                Product test = new Product(Integer.parseInt(data[n][0]), data[n][1]);            
                test.increaseQuantity(Integer.parseInt(data[n][2]));
                stock.add(test);
            }
            
            
        }
        
        //System.out.println(second+"scond");

    
    }
  
    public ArrayList<String> lowStockProducts(int upperLimit){
        ArrayList<String> listOfLowStock = new ArrayList<>();
        for(int n = 0; n < stock.size(); n++){
            //System.out.println(stock.get(n).getQuantity()+" -- ");
            if(stock.get(n).getQuantity() < upperLimit){
                //System.out.println(stock.get(n));
                listOfLowStock.add(stock.get(n).getName());
                
            
            };
        }
        //System.out.println(listOfLowStock);
        return listOfLowStock;
        
    }
      public void orderStockProducts(int stockTarget){
          ArrayList<String> orderTest = lowStockProducts(stockTarget);
          ArrayList<Product> testInOrder = new ArrayList<>();
          //System.out.println(onOrder);
          
          for(Product p : stock){
              
              if(orderTest.contains(p.getName())){
                  int currentQuantity = p.getQuantity();
                  int difference = stockTarget - currentQuantity;
                  Product x = new Product(p.getID(),p.getName());
                  x.increaseQuantity(difference);                
                  testInOrder.add(x);
                
                }
            
            
            }
            
           onOrder = testInOrder;
           
    }
    public Product findProduct(String name){
        for(int n = 0; n < stock.size(); n++){
            if(name == stock.get(n).getName()){
                return stock.get(n);
            
            };
        }
        return null;
    }
}
    
    
    
