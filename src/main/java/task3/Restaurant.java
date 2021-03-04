package task3;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    protected static List<Waiter> waitersPool = new ArrayList<Waiter>();
    protected static Kitchen kitchen = new Kitchen();

    protected synchronized void addWaiter(Waiter waiter) {
        waitersPool.add(new Waiter(waiter.getWaiterName(), kitchen, this));
        notifyAll();
    }


    public void initial() {
        Waiter waiter1 = new Waiter("Официант 1", kitchen, this);
        Waiter waiter2 = new Waiter("Официант 2", kitchen, this);
        Waiter waiter3 = new Waiter("Официант 3", kitchen, this);
        waitersPool.add(waiter1);
        waitersPool.add(waiter2);
        waitersPool.add(waiter3);

        int i = 1;
        while (i < 6) {
            Customer customer = new Customer("Посетитель " + i);
            maintenance(customer);
            try {
                Thread.sleep((long) (Math.random() * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private synchronized void maintenance(Customer customer) {

        while (waitersPool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        customer.setWaiter(waitersPool.get(0));
        customer.start();
        waitersPool.remove(0);
    }
}
