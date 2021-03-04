package task3;

public class Kitchen {
    private final Cook cook = new Cook();

    public Kitchen() {
        System.out.println("Кухня открыта. Повар готов к работе.");
    }

    protected void executeOrder(String waiter) {
        synchronized (cook) {
            System.out.println("Повар готовит блюдо для " + waiter);
            cook.cookDish();
            System.out.println("Блюдо для " + waiter + " готово.");
        }
    }

}
