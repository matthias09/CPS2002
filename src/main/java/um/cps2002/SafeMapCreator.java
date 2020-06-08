package um.cps2002;

public class SafeMapCreator {public Maps create(int size){
    SafeMap map = new SafeMap(size);
    map.generate_Map();
    return map;
}
}
