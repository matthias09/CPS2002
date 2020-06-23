package um.cps2002;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private Scanner sc = new Scanner(System.in);

    private PlayerTeam[] ShuffleTeams(int n_Players, int n_teams, Player[] player){
        //Player[] player = new Player[n_Players];
        PlayerTeam[] team = new PlayerTeam[n_teams];
        Random rand = new Random();
        float division = n_Players/n_teams;
        //for(int i = 0; i < n_Players; i++){
            //player[i] = new Player(i);
        for(int i = 0; i < n_teams; i++){
            team[i] = new PlayerTeam(n_Players);
        }

        int i = 0;
            if(division == (int)division){
                for(int i2 = 0; i2 < n_Players; i2++){
                    player[i2] = new Player(i2);
                    player[i2].setTeam(i);
                    team[i].addPlayer(player[i2]);
                    i++;
                  if(i == n_teams){
                      i=0;
                  }
                }
            }
        return team;
    }

    private int[] get_num_players(){
        //Validating number of players input
        int n_players;
        int n_teams;

        while (true) {
            try {
                System.out.println("Please input the number of teams (0+):");
                n_teams = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect input");
                continue;
            }
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
        int[] ret = {n_players, n_teams};
        return ret;
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
        Position p;
        Main m = new Main();
        int n_Players;
        int[] nums;
        int n_teams;
        int m_size;
        String m_type;

        //Step 1
        nums = m.get_num_players();
        n_Players = nums[0];
        n_teams = nums[1];
        m_size = m.get_map_size(n_Players);
        m_type = m.get_map_type();

        MapCreator creator = new MapCreator();

        //Setting of variables
        Maps MainMap = creator.createMap(m_type,m_size);
        Maps[] map = new Maps[n_teams];
        Player[] player = new Player[n_Players];
        PlayerTeam[] teams = m.ShuffleTeams(n_Players, n_teams, player);

       /* if(n_teams <= 0) {
            PlayerTeam[] teams = new PlayerTeam[1];
        }else{

        } */
        //fix later
        //PlayerTeam[] teams = new PlayerTeam[n_teams];


        HTMLDirector[] htmlbuild = new HTMLDirector[n_teams];

       /* for (int x = 0; x < n_Players; x++) {
            player[x] = new Player(x);
        } */
        for (int x = 0; x < n_teams; x++) {
            map[x] = MainMap;
        }


        //Step 3
        for (int x = 0; x < n_Players; x++) {
            int x1 = (player[x].getTeam());
            System.out.println("x1 : "+player[x].getTeam());

            player[x].setPosition(map[x1]);

            htmlbuild[x1] = new HTMLDirector();

            map[x1].setTileType(player[x].position, "" + player[x].getNumber());

            htmlbuild[x1].Create(map[x1], player[x].getPosition(), m_size);

            map[x1].setTileType(player[x].position, "G");

            for(int i = 0; i < n_teams; i++){
                if(teams[i].containsPlayer(player[x].getNumber())){
                    teams[i].updateMap(map[x1]);
                    teams[i].updatePlayer(player[x]);
                    break;
                }
            }
        }



        boolean winner = false;

        while (!winner) {

            //Step 4 and 5
            for (int x = 0; x < n_Players; x++) {
                int x1 = player[x].getTeam();

                System.out.println("Player " + (x + 1) + " from Team "+player[x].getTeam()+" please input your move (U = Up, D = down, L = Left, R = Right):");
                Position previousPosition = player[x].getPosition();
                int posx = previousPosition.x;
                int posy = previousPosition.y;
                Position prev = new Position(posx, posy);
                p = player[x].move(map[x1]);

                for(int i = 0; i < n_teams; i++){
                    if(teams[i].containsPlayer(player[x].getNumber())){
                        teams[i].updatePlayer(player[x]);
                    }
                }

                htmlbuild[x1].Update(map[x1], p, prev, player[x]);
            }

            //Step 6
            for (int x = 0; x < n_Players; x++) {
                int x1 = player[x].getTeam();
                System.out.print("Player " + (x + 1));
                if (map[x1].getTileType(player[x].position).equals("Y")) {
                    System.out.println(" found the Treasure");
                    winner = true;
                } else if (map[x1].getTileType(player[x].position).equals("B")) {
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
            int x1 = player[x].getTeam();
            if (map[x1].getTileType(player[x].position).equals("Y"))
                System.out.print((x + 1) + " ");
        }
    }



}