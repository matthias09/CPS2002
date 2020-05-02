package um.cps2002;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public Position position = new Position();
    public Position start = new Position();
    private int playerNum;

    public Player(int num) {
        this.playerNum = num;
    }

    public Position move(Map map) {
        char direction = 0;
        boolean valid = false;

        while(!valid) {
            direction = sc.next().charAt(0);

            if((direction == 'U' && this.position.y == 0)
                    || (direction == 'D' && this.position.y == map.size - 1)
                    || (direction == 'L' && this.position.x == 0)
                    || ((direction == 'R' && this.position.x == map.size - 1))){
                System.out.println("Invalid move please try again");
            }else if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R')
                valid = true;
        }
        switch (direction) {
            case 'U':
                this.position.y--;
                break;
            case 'D':
                this.position.y++;
                break;
            case 'L':
                this.position.x--;
                break;
            case 'R':
                this.position.x++;
                break;
            default:
                System.out.println("Error");
        }
        return this.position;
    }
}