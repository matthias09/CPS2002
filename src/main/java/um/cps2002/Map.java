package um.cps2002;
import java.util.Random;
import java.util.Scanner;

public class Map {
    private Random rand = new Random();

    //Variables
    int size;
    private String [][] map;

    public Map(int size){
        this.size = size;
        map = new String[size][size];
    }

    public void generate_Map() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("What type of map do you wish to play in:\n (1)Safe \n (2)Hazardous");
            choice = sc.nextInt();
            if (choice == 1 || choice == 2)
                break;
            else
                System.out.println("Incorrect input");
        }while (true);
        //Variables
        int temp_x, temp_y;

        //Set Treasure
        map[rand.nextInt(size)][rand.nextInt(size)] = "Y";

        //Set Water
        for (int a = 0; a < size - 3; a++) {
            temp_x = rand.nextInt(size);
            temp_y = rand.nextInt(size);
            if (map[temp_y][temp_x] == null) {
                map[temp_y][temp_x] = "B";
            } else {
                a--;
            }
        }

        //Fill Green Tiles
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                if (map[a][b] == null) {
                    map[a][b] = "G";
                }
            }
        }


        //Display map
        for (int y = 0; y < size; y++) {
            System.out.println(" ");
            for (int x = 0; x < size; x++) {
                System.out.print(map[y][x]);
            }
        }
        System.out.println();

    }


    public String getTileType(Position p){
        return map[p.y][p.x];
    }
    void setTileType(Position p, String str){
        this.map[p.y][p.x] = str;
    }

}
