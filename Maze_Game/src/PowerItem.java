import java.util.Random;
public class PowerItem extends Item{
    private Random random;

    public PowerItem() {
        this.random = new Random();
    }
    public int UseEffect() {
        int powerInc = random.nextInt(10);
        return powerInc;
    }


}