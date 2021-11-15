package reading;

import model.Record;
import model.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReadingOptions {

    private final List<Status> allStatuses= Arrays.asList(Status.values());

    public void printDictionary(List<Status> statusesToPrint, TreeMap<String, Record> dictionary) {
        System.out.println("\n| Haslo | Status | Definicja | Recenzja |");
        System.out.println("---------------------------------------------------------------------------------------------");
        if(dictionary!=null){
            for (Map.Entry<String, Record> entry : dictionary.entrySet()) {
                if(statusesToPrint.contains(entry.getValue().getStatus()))  {
                    System.out.print("| " + entry.getValue().getName() + " | " + entry.getValue().getStatus() + " | " + entry.getValue().getDefinition() + " | ");
                    if(entry.getValue().getReview() != null) System.out.print(entry.getValue().getReview() + " |");
                    System.out.println();
                }

            }

        }
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public List<Status> getAllStatuses() {
        return allStatuses;
    }

}
