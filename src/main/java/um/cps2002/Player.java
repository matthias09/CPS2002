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

    public Player() {

    }
}