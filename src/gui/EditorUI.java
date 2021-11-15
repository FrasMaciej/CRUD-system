package gui;

import editing.EditorEditingOptions;
import model.Data;
import model.Status;
import reading.ReadingOptions;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EditorUI {

    private final static Scanner scanner = new Scanner(System.in);
    private final static EditorEditingOptions editingOptions = new EditorEditingOptions();
    private final static ReadingOptions readingOptions = new ReadingOptions();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String choice;
        System.out.println("---Interfejs EDYTORA systemu redagowania hasel encyklopedycznych---");

        while(true){

            System.out.println("1. Przegladanie listy hasel i ich statusow");
            System.out.println("2. Rozpatrywanie propozycji autorow");
            System.out.println("3. Przegladanie recenzji ");
            System.out.println("4. Finalne akceptowanie/odrzucanie propozycji");
            System.out.println("5. Aktualizacja danych");
            System.out.println("6. Zapisanie zmian do pliku");
            System.out.println("7. Wyjscie z programu");
            System.out.print("Wybor: ");
            choice = scanner.nextLine();

            switch(choice){
                case "1":
                    readingOptions.printDictionary(readingOptions.getAllStatuses(), Data.getDictionary());
                    break;

                case "2":
                    considerDecision();
                    break;

                case "3":
                    printReviews();
                    break;

                case "4":
                    makeFinalDecision();
                    break;

                case "5":
                    Data.setDictionary(Data.getFileManager().deserializeDictionary());
                    break;

                case "6":
                    Data.getFileManager().serializeDictionary(Data.getDictionary());
                    break;

                case "7":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Nieprawidlowy wybor, prosze sprobowac ponownie");
                    break;
            }
        }
    }

    private static void considerDecision(){
        List<Status> reportedStatuses= List.of(Status.ZGLOSZONE);
        readingOptions.printDictionary(reportedStatuses, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToConsider = scanner.nextLine();
        System.out.print("Y - przekazanie do recenzji / N - wylaczanie z dalszego procesowania: ");
        String decision = scanner.nextLine();

        boolean decisionCorrect = editingOptions.recordConsideration(recordTryedToConsider, decision);

        if(decisionCorrect){
            System.out.println("Poprawnie przekazano haslo do dalszego procesowania");
        }
        else System.out.println("Wybrano nieprawidlowe haslo lub skorzystano z nieprawidlowego argumentu wyboru!");
    }

    private static void printReviews(){
        List<Status> reviewedStatuses= List.of(Status.ZRECENZOWANE, Status.GOTOWE);
        readingOptions.printDictionary(reviewedStatuses, Data.getDictionary());
    }

    private static void makeFinalDecision(){
        List<Status> readyStatues= List.of(Status.GOTOWE,Status.ZRECENZOWANE);
        readingOptions.printDictionary(readyStatues, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToFinish;
        recordTryedToFinish = scanner.nextLine();
        System.out.print("Y - Finalna akceptacja / N - Finalne odrzucenie: ");
        String finalRecordDecision = scanner.nextLine();

        boolean finalDecisionCorrect = editingOptions.finalDecision(recordTryedToFinish, finalRecordDecision);

        if(finalDecisionCorrect){
            System.out.println("Procesowanie hasla zostalo zakoczone pomyslnie");
        }
        else System.out.println("Wybrano nieprawidlowe haslo lub skorzystano z nieprawidlowego argumentu wyboru!");
    }

}
