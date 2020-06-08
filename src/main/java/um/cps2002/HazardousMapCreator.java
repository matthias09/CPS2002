package um.cps2002;

public class HazardousMapCreator {
    public Maps create(int size){
        HazardousMap map = new HazardousMap(size);
        map.generate_Map();
        return map;
    }
}
