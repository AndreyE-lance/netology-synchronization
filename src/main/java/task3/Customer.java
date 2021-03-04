package task3;

public class Customer extends Thread {
    private String name;
    private Waiter waiter;
    private static final long DELAY = (long) (Math.random() * 5000);

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Customer(String name) {
        this.name = name;
        System.out.println(name + " занял столик.");
    }

    @Override
    public void run() {
        waiter.setCustomerName(this.name);
        waiter.start();
        try {
            waiter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " кушает.");
        try {
            sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " уходит.");
    }
}
