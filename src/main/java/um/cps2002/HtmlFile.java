package um.cps2002;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlFile {
    String[][] grids;
    String[][] hiddenMap;

    public HtmlFile(){
    }

    void setMapSize(int size){
        grids = new String[size][size];
        hiddenMap = new String[size][size];
    }



    String MapToHtml(Maps map, boolean first, Position currentPosition, Position previousPosition, Player player){
        Position p = new Position();

        String html = "";

        String str = "";
        for(int i = 0; i < map.getSize(); i++){
            str += " auto";
        }

        //adding the head of the HTML document
        html+=("<!DOCTYPE html><html><head>\n" +
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


        html+=("<div class=\"grid-container\" data-update=\"newContent.php\" data-refresh-interval=\"500\">");

        if(first) {
            for (int i = 0; i < map.getSize(); i++) {
                p.y = i;
                for (int i2 = 0; i2 < map.getSize(); i2++) {
                    p.x = i2;
                    if (map.getTileType(p).equals("B") || map.getTileType(p).equals("G") || map.getTileType(p).equals("Y")) {
                        this.hiddenMap[i][i2] = "<div style=\"background-color:Gray;\" class=\"grid-item\"></div>";
                        html+=(this.hiddenMap[i][i2]);
                    } else {
                        this.hiddenMap[i][i2] = "<div style=\"background-color:Green;\" class=\"grid-item\">P"+(player.getNumber()+1)+"</div>";
                        html+=(this.hiddenMap[i][i2]);
                    }
                }
            }
            //setting the player's current position on the HTML file grid
        } else{
            if(map.getTileType(currentPosition).equals("G")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Green;\" class=\"grid-item\">P"+(player.getNumber()+1)+"</div>";
            } else if(map.getTileType(currentPosition).equals("B")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Blue;\" class=\"grid-item\"></div>";
                this.hiddenMap[player.getStart().y][player.getStart().x] = "<div style=\"background-color:Green;\" class=\"grid-item\">P"+(player.getNumber()+1)+"</div>";
            } else if(map.getTileType(currentPosition).equals("Y")){
                this.hiddenMap[currentPosition.y][currentPosition.x] = "<div style=\"background-color:Yellow;\" class=\"grid-item\">P"+(player.getNumber()+1)+"</div>";
            }
            if(previousPosition!=null) {
                if (map.getTileType(previousPosition).equals("G")) {
                    this.hiddenMap[previousPosition.y][previousPosition.x] = "<div style=\"background-color:Green;\" class=\"grid-item\"></div>";
                }
            }
            for (int i = 0; i < map.getSize(); i++) {
                for (int i2 = 0; i2 < map.getSize(); i2++) {
                    html+=(this.hiddenMap[i][i2]);
                }
            }
        }


        html+=("</div>" +
                "</body>" +
                "</html>");
        return html;
    }

    //calculate what teh grid looks like in order to utilize it when the user steps on one of the tiles
    void CalculateGrid(Maps map){
        Position p = new Position();
        for(int i = 0 ; i < map.getSize(); i++){
            p.y = i;
            for(int i2 = 0; i2 < map.getSize(); i2++){
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
