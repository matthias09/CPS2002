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
}
