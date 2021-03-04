package task3;

public class Cook {
    private static final long DELAY = (long) (Math.random() * 8000);

    protected void cookDish(){
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
