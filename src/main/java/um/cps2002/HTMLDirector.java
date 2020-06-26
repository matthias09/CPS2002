package um.cps2002;

import java.io.IOException;
//director class design pattern for the html display class
public class HTMLDirector {
    public HTMLBuilder builds;
    //create a new htmlbuilder option
    public HTMLDirector(){
        builds = new HTMLBuilder();
    }


    public void Create(Maps map, Position cp, int size){
        builds = new HTMLBuilder();
        builds.buildMap(size);
        builds.GenerateMap(map, cp);
    }

    public void Update(Maps map, Position cp, Position pp, Player player) throws IOException {
        builds.UpdateHTML(map, cp, pp, player);
        builds.outputMap();
    }

}
