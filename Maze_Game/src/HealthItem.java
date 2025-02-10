import java.util.Random;
public class HealthItem extends Item{
    private Random random;

    public HealthItem() {
        this.random = new Random();
    }
    public int UseEffect() {
        int healthInc = random.nextInt(10) ;
        return healthInc;
    }
}
