import java.util.Comparator;
/**
 * {@param Comparator class which compairs LabWork's maximumPoints
 */
class MaximumPointComparator  implements Comparator<LabWork>{
    public int compare(LabWork labWork1, LabWork labWork2){
        if(labWork1.getMaximumPoint() == labWork2.getMaximumPoint()) return 0;
        if(labWork1.getMaximumPoint() > labWork2.getMaximumPoint()) return 1;
        else  return -1;

        /**
         * {@return 0,1 or -1 to manage the compair between LabWork's pair
         */
    }
}
