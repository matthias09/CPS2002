package um.cps2002;

import java.io.IOException;

public class HTMLDirector {
    private HTMLBuilder builds;
    private int count = 0;

    HTMLDirector(){
        builds = new HTMLBuilder();
    }

    void Create(Maps map, Position cp, int size){
        builds = new HTMLBuilder();
        builds.buildMap(size);
        builds.GenerateMap(map, cp);
    }

    void Update(Maps map, Position cp, Position pp, Player player) throws IOException {
        builds.UpdateHTML(map, cp, pp, player);
        builds.outputMap();
    }

}
