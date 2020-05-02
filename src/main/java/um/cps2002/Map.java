package um.cps2002;
import java.util.Random;

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
        //Since there was no clear number of water tiles I will do size-3 water tiles, 1 treasure the rest green

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
}
