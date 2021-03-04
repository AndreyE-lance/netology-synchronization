package task2;

public class Producer extends Thread {
    private static final long DELAY = (long) (Math.random() * 8000);
    private Autoshop shop;

    public Producer(Autoshop shop) {
        this.shop = shop;
    }

    protected void buildAuto() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shop.getCars().add(new Car("Toyota"));
        System.out.println("Производитель поставил одно новое авто в салон.");
        shop.notifier();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            buildAuto();
            i++;
        }
    }
}
