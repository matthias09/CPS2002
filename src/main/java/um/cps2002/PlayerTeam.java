package um.cps2002;

import java.util.ArrayList;

//this is an observable class
public class PlayerTeam {
    Maps map;
    HTMLDirector html = new HTMLDirector();
    Player[] players;
    ArrayList<Player> player = new ArrayList<>();
    int teamno;
    int count = 0;

    public PlayerTeam(int size, int teamno){
        //calculating the no. of players on the team
        //(team size)
        this.players = new Player[size];
        this.teamno = teamno;
    }

    public void setTeam(int n){
        this.teamno = n;
    }


    public Player getPlayer(int n){
        return this.players[n];
    }
    //observer
    public void addPlayer(Player player){
        player.setTeam(this.teamno);
        //this.players[count] = player;
        //this.count++;
        this.player.add(player);
    }
    //update observer list
    public void updatePlayer(Player player){
        this.players[player.getNumber()] = player;
    }
    //check if observer is connected
    public boolean containsPlayer(int num){
        return this.players[num] != null;
    }

    //public void setMap(Maps map){
    //    this.map = map;
    //}

    public void setMap(Maps map){
        this.map = map;
        Team team;
        for(int i = 0; i < this.player.size(); i++){
            team = this.player.get(i);
            team.update(map);
        }
    }
}
