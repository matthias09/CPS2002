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
        this.mapcode = file.MapToHtml(map, true, cp, cp);
        file.CalculateGrid(map);
    }

    public void UpdateHTML(Maps map, Position cp, Position pp){
        this.mapcode = file.MapToHtml(map, false, cp, pp);
    }

    public void outputMap() throws IOException {
        FileWriter file = new FileWriter("map.html");
        file.write(this.mapcode);
        file.close();
    }

}
