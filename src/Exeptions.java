/**
 * {@param Exception class which control Difficulty data
 */
class DifficultyExeption extends Exception{
    public DifficultyExeption() {
        super("ВВеденное значение difficulty не является Enum и заменено на VERY_EAST");
    }
}
