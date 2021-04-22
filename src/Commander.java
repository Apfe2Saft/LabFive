import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * {@param Commander which works with commands from InputSystem and Files
 */
public class Commander implements Working{
    final String file = "data.xml";
    final Loader loader = new Loader(file);
    Scanner in = new Scanner(System.in);

    public Commander(InputStream system){
        in = new Scanner(system);
    }
    public Commander(String file) throws IOException {
        in = new Scanner(Paths.get(file));
    }

    @Override
    public void Working() throws Exception {
        System.out.println("ВВедите команду ( команда help - справка по командам ) :");
        loader.Reading();
        String command = in.next();
        while(!command.equals("exit")) {

            switch (command) {
                case "help" :
                {
                    PrintWriter writer = new PrintWriter(System.out);
                    writer.println(
                            "help : вывести справку по доступным командам\n" +
                            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                            "add {element} : добавить новый элемент в коллекцию\n" +
                            "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                            "remove_by_id id : удалить элемент из коллекции по его id\n" +
                            "clear : очистить коллекцию\n" +
                            "save : сохранить коллекцию в файл\n" +
                            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                            "exit : завершить программу (без сохранения в файл)\n" +
                            "remove_first : удалить первый элемент из коллекции\n" +
                            "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                            "sort : отсортировать коллекцию в естественном порядке\n" +
                            "remove_all_by_difficulty difficulty : удалить из коллекции все элементы, значение поля difficulty которого эквивалентно заданному\n" +
                            "filter_by_difficulty difficulty : вывести элементы, значение поля difficulty которых равно заданному\n" +
                            "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n");
                    writer.flush();
                }
                    break;
                case "info" :{
                    PrintWriter writer = new PrintWriter(System.out,true);
                    writer.println(
                                    "Type : Vector; \n"+"    Initialization date : "+loader.ShowVectorDate()+"\n"+
                                    "Elements :"+loader.ShowVector().capacity()+"\n"+
                                            "Type: LabWork;\n"+
                                    "LabWork's data:" +
                                    "    private Long id; \n" +
                                    "    private String name; \n" +
                                    "    private Coordinates coordinates; \n" +
                                    "    private java.time.LocalDate creationDate; \n" +
                                    "    private Long minimalPoint; \n" +
                                    "    private Integer maximumPoint; \n" +
                                    "    private Difficulty difficulty; \n" +
                                    "    private Discipline discipline; \n" +
                                    "LabWork's Methods :"+
                                    "     toString();\n" +
                                    "     LabWork();\n" +
                                    "     getId();\n" +
                                    "     getName();\n" +
                                    "     getCoordinates();\n" +
                                    "     getMinimalPoint(); \n" +
                                    "     getMaximumPoint(); \n" +
                                    "     getCreationDate();\n" +
                                    "     getDifficulty(); \n" +
                                    "     getDiscipline(); \n"+
                                            "Type: Difficulty;\n"+
                                            "     VERY_EASY;\n"+
                                            "     NORMAL;\n"+
                                            "     IMPOSSIBLE;\n"+
                                            "     INSANE;\n"+
                                            "     TERRIBLE;"

                    );
                    writer.flush();
                }
                    break;
                case "show" :{
                    PrintWriter a = new PrintWriter(System.out);
                    a.println(loader.ShowVector().toString());
                    a.flush();}
                    break;
                case "add" :{
                    try{loader.ShowVector().add(Adding(in));}catch (NumberFormatException e){
                        System.out.println("ВВеденное значение не соответствует формату");
                    }
                }
                    break;
                case "update" :{
                    command = in.next();
                    int index = Integer.parseInt(command);
                    loader.ShowVector().remove(index);
                    try{loader.ShowVector().insertElementAt(new LabWork(Adding(in), (long) index),index);}
                    catch (NumberFormatException e){
                    System.out.println("ВВеденное значение не соответствует формату");
                }
                }
                    break;
                case "remove_by_id" :{
                    try{command = in.next();
                    Long index = Long.parseLong(command);
                    for (int i = 0 ; i <loader.ShowVector().capacity();++i) {
                        if(loader.ShowVector().get(i).getId().equals(index)){
                            loader.ShowVector().remove(i);
                        }
                    }}catch (NumberFormatException e){
                        System.out.println("После команды remove_by_id необходимо ввести число с типом Long");
                    }
                }
                    break;
                case "clear" :{
                    loader.ShowVector().clear();
                    PrintWriter a = new PrintWriter("data.xml");
                    a.write("");
                    a.flush();
                    loader.ShowVector().removeAll(loader.ShowVector());

                }
                    break;
                case "save" :{
                    Writer writer = new Writer();
                    writer.Writing("data.xml", loader.ShowVector());
                }
                    break;
                case "execute_script" :{
                    try{
                    command = in.next();
                    Executer executer = new Executer();
                    executer.Executing(command);
                    } catch(NoSuchFileException e){
                        System.out.println("Файл не найден или не может быть открыт");
                    }

                }
                    break;
                case "remove_first" :{
                    loader.ShowVector().remove(0);
                }
                    break;
                case "add_if_max" :{
                    try{
                    try{LabWork labWork = Adding(in);
                    int MAX = 0;
                    for(int i = 0 ; i < loader.ShowVector().capacity();++i){
                        if(loader.ShowVector().get(i).getMaximumPoint()>MAX){
                            MAX = loader.ShowVector().get(i).getMaximumPoint();
                        }
                    }
                    if(labWork.getMaximumPoint()>MAX){
                        loader.ShowVector().add(labWork);
                    }}
                    catch (NumberFormatException e){
                        System.out.println("Введенный LabWork не может быть создан");
                    }}
                    catch (NumberFormatException a){
                        System.out.println("ВВеденное значение не соответствует формату");
                    }
                }

                    break;
                case "sort" :{
                    loader.ShowVector().sort(new MaximumPointComparator());

                }
                    break;
                case "remove_all_by_difficulty" :{
                    try{command =in.next();
                    PrintWriter writer = new PrintWriter(System.out,true);
                    for(int i = 0 ; i < loader.ShowVector().capacity();++i){
                        if(loader.ShowVector().get(i).getDifficulty() == loader.StringtoEnum(command)){
                            loader.ShowVector().remove(loader.ShowVector().get(i));
                        }
                    }

                    writer.flush();}
                    catch (DifficultyExeption e){
                        System.out.println("Неправильное значение difficulty");
                    }
                }

                    break;
                case "filter_by_difficulty" :{
                    try{
                    command =in.next();
                    PrintWriter writer = new PrintWriter(System.out,true);
                    for(int i = 0 ; i < loader.ShowVector().capacity();++i){
                        if(loader.ShowVector().get(i).getDifficulty() == loader.StringtoEnum(command)){
                            writer.println(loader.ShowVector().get(i));
                        }
                    }

                    writer.flush();}
                    catch (DifficultyExeption e){
                        System.out.println("Неправильное значение difficulty");
                    }
                }
                    break;
                case "filter_starts_with_name" :{
                    command =in.next();
                    PrintWriter writer = new PrintWriter(System.out,true);
                    for(int i = 0 ; i < loader.ShowVector().capacity();++i){
                        if(loader.ShowVector().get(i).getName().contains(command)){
                            writer.println(loader.ShowVector().get(i));
                        }
                    }

                    writer.flush();
                }
                    break;
                default:{
                    System.out.println("Такой команды не существует, обратитесь к команде help для вывода справки по командам");
                }

            }
            command = in.next();
        }
        in.close();
    }
    public String DataReading(String line) {
        Pattern pattern = Pattern.compile(".+?,");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            line = line.substring(matcher.start(), matcher.end());
        }
        line =line.replace(",","");
        line =line.replace(" ","");
        return line;
    }
    public LabWork Adding(Scanner in){
        String command;
        Object[] mass = new Object[8];
        mass[1] = (double) 1;
        for(int i = 0 ; i < 8 ; ++i ){
            command = in.next();
            if(command.equals(",")){i--;}
            else {
                command = command.replace(",", "");
                command = command.replace(" ", "");
                if(i == 0){
                    mass[0] = command;
                }
                if(i == 1){
                    mass[1] = Double.parseDouble(command);
                }
                if(i == 2){
                    mass[2] = Integer.parseInt(command);
                }
                if(i == 3){
                    mass[3] = Long.parseLong(command);
                }
                if(i == 4){
                    mass[4] = Integer.parseInt(command);
                }
                if(i == 5){
                    mass[5] = command;
                }
                if(i == 6){
                    mass[6] = command;
                }
                if(i == 7){
                    mass[7] = Long.parseLong(command);
                }

            }
        }
        try{Discipline discipline = new Discipline((String)mass[6],(Long)mass[7]);
        Difficulty difficulty = loader.StringtoEnum((String) mass[5]);
        Coordinates coordinates = new Coordinates((Double)mass[1],(Integer) mass[2]);
        return new LabWork((long) (loader.ShowVector().capacity() + 1),mass[0],coordinates, mass[3],(Integer)mass[4],
                difficulty,discipline);}
        catch (DifficultyExeption e){
            System.out.println("ВВеденное значение difficulty не является Enum и заменено на VERY_EAST");
        }
        Coordinates coordinates = new Coordinates((Double)mass[1],(Integer) mass[2]);
        Discipline discipline = new Discipline((String)mass[6],(Long)mass[7]);
        return new LabWork((long) (loader.ShowVector().capacity() + 1),mass[0],coordinates, mass[3],(Integer)mass[4],
                Difficulty.VERY_EASY,discipline);
        /**
         * {@return class LabWork which assembled by string data
         */
    }


}