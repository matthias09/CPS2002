package um.cps2002;

import java.util.Random;

public class HazardousMap implements Maps {
    private Random rand = new Random();

    //Variables
    public int size;
    public String [][] map;

    public HazardousMap(int size){
        this.size = size;
        map = new String[size][size];
    }

    public int getWaterTiles(int percentage){
        return (int) Math.floor(((percentage)*(size*size))/100);
    }

    public void generate_Map() {
        int waterTiles;
        waterTiles = getWaterTiles(25+rand.nextInt(11));

        //Variables
        int temp_x, temp_y;

        //Set Treasure
        map[rand.nextInt(size)][rand.nextInt(size)] = "Y";

        //Set Water
        for (int a = 0; a < waterTiles; a++) {
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
    public void setTileType(Position p, String str){
        this.map[p.y][p.x] = str;
    }

    public int getSize(){
        return size;
    }
}
