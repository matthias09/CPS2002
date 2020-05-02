package um.cps2002;


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
    public static void main(String[] args) {

    }
}
