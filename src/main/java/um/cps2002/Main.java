package um.cps2002;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);

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
    public static void main(String[] args) throws IOException {
        //Variables
        Position p = new Position();
        Main m = new Main();
        int n_Players;
        int m_size;

        //Step 1
        n_Players = m.get_num_players();
        m_size = m.get_map_size(n_Players);

        //Setting of variables
        Map MainMap = new Map(m_size);
        Map[] map = new Map[n_Players];
        Player[] player = new Player[n_Players];
        for (int x = 0; x < n_Players; x++) {
            map[x] = new Map(m_size);
            player[x] = new Player(x);
        }

        //Step 2
        MainMap.generate_Map();
        for (int i = 0; i < n_Players; i++) {
            map[i] = MainMap;
        }

        for (int x = 0; x < n_Players; x++) {
            player[x].setPosition(map[x]);
            map[x].setTileType(player[x].position, "" + player[x].getNumber());
            map[x].setTileType(player[x].position, "G");
        }

    }
}
