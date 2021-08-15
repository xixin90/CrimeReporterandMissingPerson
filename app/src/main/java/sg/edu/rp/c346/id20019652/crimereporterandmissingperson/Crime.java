package sg.edu.rp.c346.id20019652.crimereporterandmissingperson;

import java.io.Serializable;

public class Crime implements Serializable {

    private int id;
    private String name;
    private String description;
    private int yearOfCrime;

    public Crime(int id, String name, String description, int yearOfCrime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearOfCrime = yearOfCrime;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearOfCrime() { return yearOfCrime; }
    public String getYear(){
        String yearString = "";
        yearString += yearOfCrime;
        return yearString;
    }

    public void setYearOfCrime(int yearOfCrime) {
        this.yearOfCrime = yearOfCrime;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", yearOfCrime=" + yearOfCrime +
                '}';
    }
}
