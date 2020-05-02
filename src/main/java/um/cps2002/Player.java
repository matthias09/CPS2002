package com.company;
import java.util.Random;
import java.util.Scanner;

class Player {
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    Position position = new Position();
    Position start = new Position();
    private int playerNum;

    Player(int num){
        this.playerNum = num;
    }

    Position move(Map map) {
        char direction = 0;
        boolean valid = false;

        while(!valid) {
            direction = sc.next().charAt(0);

            if((direction == 'U' && this.position.y == 0)
                || (direction == 'D' && this.position.y == map.size - 1)
                || (direction == 'L' && this.position.x == 0)
                || ((direction == 'R' && this.position.x == map.size - 1))){
                System.out.println("Invalid move please try again");
            }else if (direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R')
                valid = true;
        }
        switch (direction) {
            case 'U':
                this.position.y--;
                break;
            case 'D':
                this.position.y++;
                break;
            case 'L':
                this.position.x--;
                break;
            case 'R':
                this.position.x++;
                break;
            default:
                System.out.println("Error");
        }
        return this.position;
    }

    void setPosition(Map map){
        boolean valid = false;
        int temp_x, temp_y;
        Position tempPos = new Position();

        while(!valid){
            temp_x = rand.nextInt(map.size);
            temp_y = rand.nextInt(map.size);
            tempPos.x = temp_x;
            tempPos.y = temp_y;

            if((map.getTileType(tempPos)).equals("G")){
                valid = true;
                this.start.x = temp_x;
                this.start.y = temp_y;
                this.position.x = temp_x;
                this.position.y = temp_y;
            }
        }
    }

    int getNumber(){
        return this.playerNum;
    }

    Position getPosition(){
        return this.position;
    }

    Position getStart(){
        return this.start;
    }

}


