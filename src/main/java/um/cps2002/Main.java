package um.cps2002;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    HTMLBuilder builder = new HTMLBuilder();

    private int get_num_players(){
        //Validating number of players input
        int n_players;

        while (true) {
            try {
                System.out.println("Please input the number of players (Min = 2, Max = 8):");
                n_players = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect input");
                continue;
            }
            if (n_players < 2 || n_players > 8) {
                System.out.println("Input is not in acceptable range.");
            } else {
                break;
            }
        }
        return n_players;
    }

    private int get_map_size(int n_players){
        int map_size;

        //Validating map size input
        while (true) {
            try{
                if (n_players < 4) {
                    System.out.println("Please input the size of the square map (Min = 5, Max = 50):");
                } else {
                    System.out.println("Please input the size of the square map (Min = 8, Max = 50):");
                }
                map_size = sc.nextInt();
            }catch (Exception e){
                System.out.println("Incorrect input");
                continue;
            }

            if ((map_size < 5 && n_players <= 4) || (map_size < 8 && n_players > 4) || map_size > 50) {
                System.out.println("Input is not in acceptable range.");
            } else {
                break;
            }
        }
        return map_size;
    }

    private String get_map_type(){
        int choice;
        while (true) {
            try {
                System.out.println("Please input the map you want to play in (1 for safe) (2 for hazardous):");
                choice = sc.nextInt();

                if (choice == 1)
                    return "Safe";
                else if (choice == 2)
                    return "Hazardous";
                else
                    System.out.println("Incorrect input");
            }catch (Exception e){
                System.out.println("Incorrect input");
                continue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //Variables
        Position p = new Position();
        Main m = new Main();
        int n_Players;
        int m_size;
        String m_type;

        //Step 1
        n_Players = m.get_num_players();
        m_size = m.get_map_size(n_Players);
        m_type = m.get_map_type();

        MapCreator creator = new MapCreator();

        //Setting of variables
        Maps MainMap = creator.createMap(m_type,m_size);
        Maps[] map = new Maps[n_Players];
        Player[] player = new Player[n_Players];
        //HtmlFile[] file = new HtmlFile[n_Players];
        //CHANGED HTMLFILE TO HTMLBUILDER
        HTMLBuilder[] builds = new HTMLBuilder[n_Players];

        for (int x = 0; x < n_Players; x++) {
            map[x] = MainMap;
            player[x] = new Player(x);
        }


        //Step 3
        for (int x = 0; x < n_Players; x++) {
            player[x].setPosition(map[x]);
            //builds[x] = new HtmlFile(player[x], m_size);
            //ADDED NEW METHOD
            builds[x] = new HTMLBuilder();
            builds[x].buildMap(m_size);

            map[x].setTileType(player[x].position, "" + player[x].getNumber());
            //file[x].MapToHtml(map[x], true, player[x].getPosition(), p);
            builds[x].GenerateMap(map[x], player[x].getPosition(), p);

            map[x].setTileType(player[x].position, "G");
            //ALREADY IN THE HTMLBUILDER CLASS
            //file[x].CalculateGrid(map[x]);
        }

        boolean winner = false;

        while (!winner) {

            //Step 4 and 5
            for (int x = 0; x < n_Players; x++) {
                System.out.println("Player " + (x + 1) + " please input your move (U = Up, D = down, L = Left, R = Right):");
                Position previousPosition = player[x].getPosition();
                int posx = previousPosition.x;
                int posy = previousPosition.y;
                Position prev = new Position(posx, posy);
                p = player[x].move(map[x]);

                builds[x].UpdateHTML(map[x], p, prev);
                builds[x].outputMap();
                //file[x].MapToHtml(map[x], false, p, prev);
            }

            //Step 6
            for (int x = 0; x < n_Players; x++) {
                System.out.print("Player " + (x + 1));
                if (map[x].getTileType(player[x].position).equals("Y")) {
                    System.out.println(" found the Treasure");
                    winner = true;
                } else if (map[x].getTileType(player[x].position).equals("B")) {
                    System.out.println(" stepped on water and went back to his starting position");
                    player[x].position.x = player[x].start.x;
                    player[x].position.y = player[x].start.y;
                } else {
                    System.out.println(" stepped on grass");
                }
            }
        }

        //Declaration of winners
        System.out.println("Winner(s): ");
        for (int x = 0; x < n_Players; x++) {
            if (map[x].getTileType(player[x].position).equals("Y"))
                System.out.print((x + 1) + " ");
        }
    }



}