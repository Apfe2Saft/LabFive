import java.io.IOException;
/**
 * {@param class which execute commands from File by Commander class
 */
public class Executer implements Executing {

    @Override
    public void Executing(String file) throws Exception {
        Commander commander = new Commander(file);
        commander.Working();
    }
}
