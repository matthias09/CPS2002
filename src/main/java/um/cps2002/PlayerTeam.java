package um.cps2002;

import java.util.ArrayList;

//this is an observable class
//there is a one to many relationship between players and teams
//many players can belong to one time, and one team can contain many players
public class PlayerTeam {
    private Maps map;
    private ArrayList<Player> player = new ArrayList<>();
    private int teamno;

    public PlayerTeam(int teamno){
        //calculating the no. of players on the team
        //(team size)
        this.teamno = teamno;
    }

    //observer
    public void addPlayer(Player player){
        player.setTeam(this.teamno);
        this.player.add(player);
    }
    //updating the observer using the Team interface
    //this forms a one-to-many relationship
    public void setMap(Maps map){
        this.map = map;
        Team team;
        for(int i = 0; i < this.player.size(); i++){
            team = this.player.get(i);
            team.update(map);
        }

    }
}
