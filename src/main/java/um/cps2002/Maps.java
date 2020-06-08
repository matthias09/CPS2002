package um.cps2002;
public interface Maps {
    public int size = 0;
    public String [][] map = new String[0][];

    public int getWaterTiles(int percentage);
    public void generate_Map();
    public String getTileType(Position p);
    public void setTileType(Position p, String str);
    public int getSize();
}
