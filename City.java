import java.util.Map;

public class City {
    public int id;
    public String name;
    public String country;
    public Map<String, Double> coord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, Double> getCoord() {
        return coord;
    }

    public void setCoord(Map<String, Double> coord) {
        this.coord = coord;
    }
}
