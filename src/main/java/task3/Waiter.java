package task3;


public class Waiter extends Thread {
    private String name;
    private Kitchen kitchen;
    private Restaurant restaurant;
    private String customerName;
    private static final long DELAY = (long) (Math.random() * 5000);

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Waiter(String name, Kitchen kitchen, Restaurant restaurant) {
        this.name = name;
        this.kitchen = kitchen;
        this.restaurant = restaurant;
        System.out.println(this.name + " готов к работе.");
    }

    public String getWaiterName() {
        return name;
    }

    @Override
    public void run() {
        try {
            sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " берет заказ у " + customerName);
        kitchen.executeOrder(this.name);
        System.out.println(name + " несет блюдо для " + customerName);
        restaurant.addWaiter(this);
    }
}
