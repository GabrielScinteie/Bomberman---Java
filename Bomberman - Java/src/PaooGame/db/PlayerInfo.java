package PaooGame.db;

public class PlayerInfo implements Comparable<PlayerInfo> {

    private String name;
    private Integer time;

    public PlayerInfo(String name, Integer time){
        this.name = name;
        this.time = time;
    }

    public String getName(){return name;}
    public Integer getTime(){return time;}

    @Override
    public int compareTo(PlayerInfo o){
        return this.time.compareTo(o.time);
    }

    @Override
    public String toString(){
        return "" + name + ": " + time;
    }
}
