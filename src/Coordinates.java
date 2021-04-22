/**
 * {@param class which is a part of LabWork class
 */
public class Coordinates {
    private Double x = 0.0; //Поле не может быть null
    private Integer y = 1; //Значение поля должно быть больше -957, Поле не может быть null
    public Coordinates(Double x, Integer y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(){
        this.x = 0.0;
        this.y = 0;
    }
    public Double getX(){return this.x;}
    public Integer getY(){return this.y;}
    public void setX(Double x){this.x = x;}
    public void setY(Integer y){this.y =y;}

    @Override
    public java.lang.String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}