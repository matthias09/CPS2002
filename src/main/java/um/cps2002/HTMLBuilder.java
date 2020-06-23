package um.cps2002;

import java.io.FileWriter;
import java.io.IOException;

public class HTMLBuilder implements MapBuilder {
    //private HTMLMap map;
    private HtmlFile file;
    String mapcode;

    public HTMLBuilder(){}

    public void buildMap(int size){
        //this.map = new HTMLMap();
        this.file = new HtmlFile();
        file.setMapSize(size);
    }

    public void GenerateMap(Maps map, Position cp) {
        Player p = new Player();
        this.mapcode = file.MapToHtml(map, true, cp, cp, p);
        file.CalculateGrid(map);
    }

    public void UpdateHTML(Maps map, Position cp, Position pp, Player player){
        this.mapcode = file.MapToHtml(map, false, cp, pp, player);
    }

    public void outputMap() throws IOException {
        FileWriter file = new FileWriter("map.html");
        file.write(this.mapcode);
        file.close();
    }

}
