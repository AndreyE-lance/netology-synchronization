package task1;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Autoshop {
    private BlockingQueue<Car> cars = new ArrayBlockingQueue<>(10, false);

    public BlockingQueue<Car> getCars() {
        return cars;
    }

    protected synchronized void buyCar() {
        System.out.println(Thread.currentThread().getName() + " пришел в салон.");
        while (cars.isEmpty()) {
            try {
                System.out.println("Машин нет.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            cars.take();
            System.out.println(Thread.currentThread().getName() + " купил и уехал.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void addCar() {
        Producer producer = new Producer(this);
        producer.start();
    }

    protected synchronized void notifier() {
        notify();
    }


}
