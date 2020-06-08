import org.junit.Test;
import um.cps2002.*;

import static org.junit.Assert.*;

public class MapTest {
    @Test
    public void generateMapTest_SafeMap() {
        SafeMap map;
        //Given new enhancement water tiles should be 2
        map = new SafeMap(5);

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

    @Test
    public void getSetTileTypeTest_SafeMap(){
        SafeMap map;
        map = new SafeMap(5);

        Position p = new Position(1,1);

        map.setTileType(p,"W");
        assertEquals("W",map.map[p.x][p.y] );
        assertEquals("W",map.getTileType(p));
    }

    @Test
    public void getWaterTilesTest_SafeMap(){
        SafeMap map = new SafeMap(5);

        //10% of 25 is 2.5 but method suppose to round downwards to not exceed percentage
        assertEquals(2,map.getWaterTiles(10));


        //50% of 25 is 12.5 so answer should be 12
        assertEquals(12,map.getWaterTiles(50));
    }

    @Test
    public void generateMapTest_HazardousMap() {
        HazardousMap map;
        //Given new enhancement water tiles should be 6 7 or 8
        map = new HazardousMap(5);

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

        if(B == 6) {
            assertEquals(18, G);
            assertEquals(6, B);
            assertEquals(1, Y);
        }else if(B == 7){
            assertEquals(17, G);
            assertEquals(7, B);
            assertEquals(1, Y);
        }else if(B == 8){
            assertEquals(16, G);
            assertEquals(8, B);
            assertEquals(1, Y);
        }
    }

    @Test
    public void getSetTileTypeTest_HazardousMap(){
        HazardousMap map;
        map = new HazardousMap(5);

        Position p = new Position(1,1);

        map.setTileType(p,"W");
        assertEquals("W",map.map[p.x][p.y] );
        assertEquals("W",map.getTileType(p));
    }

    @Test
    public void getWaterTilesTest_HazardousMap(){
        HazardousMap map = new HazardousMap(5);

        //10% of 25 is 2.5 but method suppose to round downwards to not exceed percentage
        assertEquals(2,map.getWaterTiles(10));


        //50% of 25 is 12.5 so answer should be 12
        assertEquals(12,map.getWaterTiles(50));
    }

}
