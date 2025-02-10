
import java.util.Scanner;
public class Maze_Game {
    public static void main(String[] args) {

            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("####################");
                System.out.println("Welcome to Maze Game!");
                System.out.println("#####################");
                System.out.println("Please enter you choice: \nA) Start\nB) Exit");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("A")) {
                    System.out.println("Enter your character's name: ");
                    String player = sc.nextLine();
                    Character character  = new Character(player);
                    Maze maze = new Maze(character);
                    maze.placeMonsters(5);
                    maze.placeHealth(5);
                    maze.placePower(5);
                    maze.Start();
                } else if (choice.equalsIgnoreCase("B")) {
                    System.out.println("End of game!");
                    return;
                } else {
                    System.out.println("Invalid choice! , Please Enter again :");
                }

        }
    }
}