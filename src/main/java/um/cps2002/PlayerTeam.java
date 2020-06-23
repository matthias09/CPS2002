package um.cps2002;

//this is an observable class
public class PlayerTeam {
    Maps map;
    HTMLDirector html = new HTMLDirector();
    Player[] players;
    int count = 0;

    public PlayerTeam(int size){
        //calculating the no. of players on the team
        //(team size)
        players = new Player[size];
    }

    public Player getPlayer(int n){
        return this.players[n];
    }
    //observer
    public void addPlayer(Player player){
        this.players[count] = player;
        this.count++;
    }

    public void updatePlayer(Player player){
        this.players[player.getNumber()] = player;
    }

    public boolean containsPlayer(int num){
        return this.players[num] != null;
    }
    public void setMap(Maps map){
        this.map = map;
    }
    public void updateMap(Maps map){
        this.map = map;
    }

}
