import java.util.Scanner;
import java.util.Random;

public class Maze {
    private char[][] maze;
    private int[] position;
    private Random random;
    private int remainingMonsters = 0;
    private Character character;
    private int health ;
    private int power;

    public Maze(Character character) {
        maze = new char[10][10];
        position = new int[]{0, 0};
        random = new Random();
        this.character = character;
        this.health = character.getHealth();
        this.power = character.getPower();

        // Initialize the maze with '*'
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                maze[i][j] = '*';
            }
        }

        // Place the character at the starting point
        maze[position[0]][position[1]] = character.getSymbol();
    }

    public void printMaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void placeMonsters(int num_monsters) {
        remainingMonsters = num_monsters;
        for (int i = 0; i < num_monsters; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (maze[x][y] != '*') {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            maze[x][y] = 'M';
        }
    }

    public void placeHealth(int num_health) {
        for (int i = 0; i < num_health; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (maze[x][y] != '*') {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            maze[x][y] = 'H';
        }
    }

    public void placePower(int numberOfPowers) {
        for (int i = 0; i < numberOfPowers; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (maze[x][y] != '*') {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            maze[x][y] = 'P';
        }
    }

    public char getItem(int row, int col) {
        return maze[row][col];
    }

    public void updatePosition(int row, int col) {
        maze[position[0]][position[1]] = '*'; // Clear old position
        position[0] = row;
        position[1] = col;
        maze[position[0]][position[1]] = character.getSymbol(); // Place character at new position
        character.setPosition(row, col); // Update character position
    }

    public void move(int x, int y) throws InvalidMoveException {
        int newRow = position[0] + x;
        int newCol = position[1] + y;
        if (newRow < 0 || newRow >= 10 || newCol < 0 || newCol >= 10) {
            throw new InvalidMoveException("Can't move there!");
        }

        char cell = getItem(newRow, newCol);

        if (cell == 'H') {
            HealthItem healthBoost = new HealthItem();
            health += healthBoost.UseEffect();
            System.out.println("You found a health item! Health increased by " + healthBoost.UseEffect());
            updatePosition(newRow, newCol);
        } else if (cell == 'P') {
            PowerItem powerBoost = new PowerItem();
            power += powerBoost.UseEffect();
            System.out.println("power item is found! Power increased by " + powerBoost.UseEffect());
            updatePosition(newRow, newCol);
        } else if (cell == 'M') {
            Monster monster = new Monster();
            int monsterPower = monster.getPower();
            remainingMonsters--;
            System.out.println("Monster Power is: " + monsterPower);
            if (power >= monsterPower) {
                System.out.println("^^^ Defeated the monster! ^^^");
                updatePosition(newRow, newCol);
            } else {
                System.out.println("^^^ Defeated by the monster! ^^^");
                health -= 30;
                if (health <= 0) {
                    System.out.println("You have been defeated.");
                } else {
                    updatePosition(newRow, newCol);
                }
            }
        } else {
            updatePosition(newRow, newCol);
        }
    }

    public void Up() {
        try {
            move(-1, 0);
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Down() {
        try {
            move(1, 0);
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Left() {
        try {
            move(0, -1);
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Right() {
        try {
            move(0, 1);
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Start() {
        Scanner scan = new Scanner(System.in);
        String direction;

        while (true) {
            if (health <= 0) {
                System.out.println("You lose the game!");
                return;
            }
            if (remainingMonsters == 0) {
                System.out.println("Congratulations! You won!");
                return;
            }
            printMaze();
            System.out.println("Your position is ==> " + '(' + position[0] + "," + position[1] + ')');
            System.out.println("Your Current Power ==> " + power);
            System.out.println("Your Current Health ==> " + health);
            System.out.println("Enter your move (up, down, left, right): ");
            direction = scan.nextLine();

            if (direction.equals("up")) {
                Up();
            } else if (direction.equals("down")) {
                Down();
            } else if (direction.equals("left")) {
                Left();
            } else if (direction.equals("right")) {
                Right();
            } else {
                System.out.println("Invalid move, try again (up, down, left, right)");
            }
        }
    }
}
