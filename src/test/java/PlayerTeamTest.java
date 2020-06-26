import org.junit.Test;
import um.cps2002.*;

import static org.junit.Assert.*;

public class PlayerTeamTest {


    @Test
    public void TestAddPlayer(){
        PlayerTeam team = new PlayerTeam(0);
        Player p = new Player();
        team.addPlayer(p);

        assertEquals(0, p.getTeam());
    }
    @Test
    public void TestSetMap(){
        Player p = new Player();
        PlayerTeam team = new PlayerTeam(0);
        team.addPlayer(p);

        SafeMap map;

        map = new SafeMap(5);

        map.generate_Map();

        team.setMap(map);

        assertEquals(map, p.VisibleMap);
    }


}
