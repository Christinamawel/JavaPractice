import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        int die = random.nextInt(6)+1;
        double y = random.nextDouble();
        boolean coinFlip = random.nextBoolean();

        System.out.println(die);
        System.out.println(y);
        System.out.println(coinFlip);
    }
}
