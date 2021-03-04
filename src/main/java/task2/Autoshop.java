package task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Autoshop {
    private BlockingQueue<Car> cars = new ArrayBlockingQueue<>(10, false);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public BlockingQueue<Car> getCars() {
        return cars;
    }

    protected void buyCar() {
        System.out.println(Thread.currentThread().getName() + " пришел в салон.");
        try {
            lock.lock();
            while (cars.isEmpty()) {
                System.out.println("Машин нет.");
                condition.await();
            }
            try {
                cars.take();
                System.out.println(Thread.currentThread().getName() + " купил и уехал.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    protected void addCar() {
        Producer producer = new Producer(this);
        producer.start();
    }

    protected synchronized void notifier() {
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


}
