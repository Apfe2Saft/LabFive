import java.io.IOException;
import java.util.Scanner;

interface Reading {
    void Reading() throws IOException;
    String xmlChecker(String file);
    String xmlCheckerMedium(String file);
    LabWork Creator(Long id , String name
            , Coordinates coordinates , java.time.LocalDate creationDate,
                    Long minimalPoint , Integer maximumPoint, Difficulty difficulty , Discipline discipline);
}
