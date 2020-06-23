package um.cps2002;

import java.io.IOException;

public interface MapBuilder {

    public void buildMap(int size);
    public void GenerateMap(Maps map, Position cp, Position pp);
    public void outputMap() throws IOException;
}
