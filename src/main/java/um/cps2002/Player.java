package um.cps2002;
import java.util.Random;
import java.util.Scanner;

public class Player implements Team{
    private int team;
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);
    public Maps VisibleMap;

    public Position position = new Position();
    public Position start = new Position();
    private int playerNum;

    public Player(int num){
        this.playerNum = num;
    }

    public Player() {

    }

    //using the interface in order to update the map which belongs to many users
    @Override
    public void update(Object o){
        this.VisibleMap = (Maps)o;
    }

    public void setTeam(int t){
        this.team = t;
    }

    public int getTeam(){
        return this.team;
    }

    public Position move(Maps map) {
        char direction = 0;
        boolean valid = false;

        while(!valid) {
            direction = sc.next().charAt(0);

            if((direction == 'U' && this.position.y == 0)
                || (direction == 'D' && this.position.y == map.getSize() - 1)
                || (direction == 'L' && this.position.x == 0)
                || (direction == 'R' && this.position.x == map.getSize() - 1)){
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

    void setPosition(Maps map){
        boolean valid = false;
        int temp_x, temp_y;
        Position tempPos = new Position();

        while(!valid){
            temp_x = rand.nextInt(map.getSize());
            temp_y = rand.nextInt(map.getSize());
            tempPos.x = temp_x;
            tempPos.y = temp_y;

            if((map.getTileType(tempPos)).equals("G")){
                valid = true;
                this.start.x = temp_x;
                this.start.y = temp_y;
                this.position.x = temp_x;
                this.position.y = temp_y;
            }
        }
    }

    int getNumber(){
        return this.playerNum;
    }

    Position getPosition(){
        return this.position;
    }

    Position getStart(){
        return this.start;
    }

}


