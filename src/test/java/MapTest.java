import org.junit.Test;
import um.cps2002.*;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void generateMapTest() {
        Map map;
        //Since size is 5 there should be 1 (Y)ellow tile 2 (B)lue tiles and 22 (G)reen
        map = new Map(5);

        map.generate_Map();

        int G=0,Y=0,B=0;
        Position temp = new Position();

        for(int a = 0;a<5;a++){
            for(int b = 0;b<5;b++){
                temp.x = b;
                temp.y = a;
                switch (map.getTileType(temp)) {
                    case "G":
                        G++;
                        break;
                    case "B":
                        B++;
                        break;
                    case "Y":
                        Y++;
                        break;
                }
            }
        }

        assertEquals(22,G);
        assertEquals(2,B);
        assertEquals(1,Y);
    }
}
