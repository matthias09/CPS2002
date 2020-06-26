package um.cps2002;

import java.io.IOException;

public interface MapBuilder {
    public void UpdateHTML(Maps map, Position cp, Position pp, Player player);
    public void buildMap(int size);
    public void GenerateMap(Maps map, Position cp);
    public void outputMap() throws IOException;
}
