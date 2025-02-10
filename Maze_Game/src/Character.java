import java.util.Random;
public class Character {
    Random random = new Random();
    int[] position;
    int health;
    int power;
    int inventory;
    char symbol;
    public Character(String name){
        position = new int[]{0,0};
        health = 100;
        power = 100;
        this.symbol = name.charAt(0);
    }
    public char getSymbol(){
        return symbol;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public void setPosition(int row , int col) {
        this.position[0] = row;
        this.position[1] = col;
    }
    public int[] getPosition() {
        return position;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getPower() {
        return power;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public int getInventory() {
        return inventory;
    }

}
