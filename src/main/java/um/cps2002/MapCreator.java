package um.cps2002;

public class MapCreator {
    public Maps createMap(String type, int size) {
        if(type.equals("Safe")) {
            SafeMapCreator s = new SafeMapCreator();
            return s.create(size);
        }else {
            HazardousMapCreator h = new HazardousMapCreator();
            return h.create(size);
        }
    }
}
