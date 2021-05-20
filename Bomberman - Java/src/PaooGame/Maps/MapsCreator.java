package PaooGame.Maps;

import PaooGame.Exceptions.WrongMapNumberException;
import PaooGame.RefLinks;

public class MapsCreator {
    private RefLinks refLink;

    public MapsCreator(RefLinks refLink){
       this.refLink = refLink;
    }

    public Map getMap(int mapNumber) throws WrongMapNumberException {
        switch(mapNumber){
            case 1:
                System.out.println("Map1");
                return new Map1(refLink);
            case 2:
                System.out.println("Map2");
                return new Map2(refLink);

            default:
                throw new WrongMapNumberException("Wrong map number in MapsCreator class");
        }
    }

    public Map getDefaultMap(){
        return new Map1(refLink);
    }
}
