package um.cps2002;

import java.io.IOException;
//director class design pattern for the html display class
public class HTMLDirector {
    private HTMLBuilder builds;
    //create a new htmlbuilder option
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
