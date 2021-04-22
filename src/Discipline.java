import java.lang.*;
/**
 * {@param enum which is a part of LabWork class
 */
public class Discipline<practiceHours> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long practiceHours; //Поле не может быть null
    public Discipline(String name, Long practiceHours){
        this.name = name;
        this.practiceHours = practiceHours;
    }
    public Discipline(){}
    void setName(String name){this.name =name;}
    void setPracticeHours(Long practiceHours){this.practiceHours = practiceHours; }
    String getName(){return name;}
    Long getPracticeHours(){return practiceHours;}

    @Override
    public String toString() {
        return "Discipline{" +
                "name = " + name +
                ", practiceHours = " + practiceHours +
                '}';
    }
}