package dataStructs;

/**
 * Created by Administrator on 2017-05-24.
 */
public class Equipment {
    private int id;
    private String equipment;

    public Equipment() {

    }

    public Equipment(int id, String equipment) {
        this.id = id;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", equipment='" + equipment + '\'' +
                '}';
    }
}
