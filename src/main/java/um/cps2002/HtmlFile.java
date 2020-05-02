package um.cps2002;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlFile {
    Player player;
    String[][] grids;
    String[][] hiddenMap;

    public HtmlFile(Player player, int size){
        String str = "";
        for(int i = 0; i < size; i++){
            str += " auto";
        }
        this.player = player;


        //this.file.close();
        grids = new String[size][size];
        hiddenMap = new String[size][size];
    }

    FileWriter MapToHtml(Map map, boolean first, Position currentPosition, Position previousPosition) throws IOException {
        Position p = new Position();

        String str = "";
        for(int i = 0; i < map.size; i++){
            str += " auto";
        }

        FileWriter file = new FileWriter("map_player_"+(player.getNumber() + 1)+".html");
        //adding the head of the HTML document
        file.write("<!DOCTYPE html><html><head>\n" +
                "<style>\n" +
                ".grid-container {\n" +
                "  display: grid;\n" +
                "  grid-template-columns: "+str+";\n" +
                "grid-auto-rows: 50px;\n" +
                "  padding: 5px;\n" +
                "}\n" +
                ".grid-item {\n" +
                "  border: 1px solid rgba(0, 0, 0, 0.8);\n" +
                "  font-size: 30px;\n" +
                "  text-align: center;\n" +
                "}\n" +
                "</style>\n" +
                "</head>" +
                "<body>");


        file.write("<div class=\"grid-container\" data-update=\"newContent.php\" data-refresh-interval=\"500\">");

        if(first) {
            for (int i = 0; i < map.size; i++) {
                p.y = i;
                for (int i2 = 0; i2 < map.size; i2++) {
                    p.x = i2;
                    if (map.getTileType(p).equals("B") || map.getTileType(p).equals("G") || map.getTileType(p).equals("Y")) {
                        this.hiddenMap[i][i2] = "<div style=\"background-color:Gray;\" class=\"grid-item\"></div>";
                        file.write(this.hiddenMap[i][i2]);
                    } else {
                        this.hiddenMap[i][i2] = "<div style=\"background-color:Green;\" class=\"grid-item\">P</div>";
                        file.write(this.hiddenMap[i][i2]);
                    }
                }
            }
            //setting the player's current position on the HTML file grid
        } else{
            if(map.getTileType(currentPosition).equals("G")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Green;\" class=\"grid-item\">P</div>";
            } else if(map.getTileType(currentPosition).equals("B")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Blue;\" class=\"grid-item\"></div>";
                this.hiddenMap[this.player.getStart().y][this.player.getStart().x] = "<div style=\"background-color:Green;\" class=\"grid-item\">P</div>";
            } else if(map.getTileType(currentPosition).equals("Y")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Yellow;\" class=\"grid-item\">P</div>";
            }

            if(map.getTileType(previousPosition).equals("G")){
                this.hiddenMap[previousPosition.y][previousPosition.x] = "<div style=\"background-color:Green;\" class=\"grid-item\"></div>";
            }

            for (int i = 0; i < map.size; i++) {
                for (int i2 = 0; i2 < map.size; i2++) {
                    file.write(this.hiddenMap[i][i2]);
                }
            }
        }


        file.write("</div>" +
                "</body>" +
                "</html>");
        file.close();
        return file;
    }
    //calculate what teh grid looks like in order to utilize it when the user steps on one of the tiles
    void CalculateGrid(Map map){
        Position p = new Position();
        for(int i = 0 ; i < map.size; i++){
            p.y = i;
            for(int i2 = 0; i2 < map.size; i2++){
                p.x = i2;
                if(map.getTileType(p).equals("B")){
                    this.grids[i][i2] = ("<div style=\"background-color:Blue;\" class=\"grid-item\"></div>");
                } else if(map.getTileType(p).equals("G")) {
                    this.grids[i][i2] = ("<div style=\"background-color:Green;\" class=\"grid-item\"></div>");
                } else if(map.getTileType(p).equals("Y")){
                    this.grids[i][i2] = ("<div style=\"background-color:Yellow;\" class=\"grid-item\"></div>");
                } else{
                    this.grids[i][i2] = ("<div style=\"background-color:Green;\" class=\"grid-item\"></div>");
                }
            }
        }
    }

}
