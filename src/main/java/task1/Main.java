package task1;

public class Main {
    private static final int MAX_AUTOS = 10;
    private static final long DELAY = (long) (Math.random() * 3000);

    public static void main(String[] args) {
        Autoshop autoshop = new Autoshop();
        Producer producer = new Producer(autoshop);
        new Thread(null, producer, " ").start();
        for (int i = 1; i <= MAX_AUTOS; i++) {
            new Thread(null, autoshop::buyCar, "Покупатель " + i).start();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
