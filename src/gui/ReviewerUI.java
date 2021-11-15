package gui;

import editing.ReviewerEditingOptions;
import model.Data;
import model.Status;
import reading.ReadingOptions;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReviewerUI {

    private final static Scanner scanner = new Scanner(System.in);
    private final static ReviewerEditingOptions editingOptions = new ReviewerEditingOptions();
    private final static ReadingOptions readingOptions = new ReadingOptions();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String choice;
        System.out.println("---Interfejs RECENZENTA systemu redagowania hasel encyklopedycznych---");

        while(true){

            System.out.println("1. Przegladanie listy hasel i ich statusow");
            System.out.println("2. Recenzowanie definicji wybranego hasla");
            System.out.println("3. Przekazanie recenzji");
            System.out.println("4. Aktualizacja danych");
            System.out.println("5. Zapisanie zmian do pliku");
            System.out.println("6. Wyjscie z programu");
            System.out.print("Wybor: ");
            choice = scanner.nextLine();

            switch(choice){
                case "1":
                    readingOptions.printDictionary(readingOptions.getAllStatuses(), Data.getDictionary());
                    break;

                case "2":
                    createNewReview();
                    break;

                case "3":
                    passReview();
                    break;

                case "4":
                    Data.setDictionary(Data.getFileManager().deserializeDictionary());
                    break;

                case "5":
                    Data.getFileManager().serializeDictionary(Data.getDictionary());
                    break;

                case "6":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Nieprawidlowy wybor, prosze sprobowac ponownie");
                    break;
            }
        }
    }

    private static void createNewReview(){
        List<Status> recordsPossibleToReview= List.of(Status.PRZEKAZANE);
        readingOptions.printDictionary(recordsPossibleToReview, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToReview = scanner.nextLine();
        System.out.print("Recenzja: ");
        String review = scanner.nextLine();

        boolean reviewCorrect = editingOptions.createReview(recordTryedToReview, review);

        if(reviewCorrect){
            System.out.println("Pomyslnie zrecenzowano");
        }
        else System.out.println("Wybrano nieprawidlowe haslo!");
    }

    private static void passReview(){
        List<Status> recordsToPass= List.of(Status.PRZEKAZANE);
        readingOptions.printDictionary(recordsToPass, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToPass;
        recordTryedToPass = scanner.nextLine();

        boolean passCorrect = editingOptions.passRecord(recordTryedToPass);

        if(passCorrect){
            System.out.println("Poprawnie przekazano haslo do dalszego procesowania");
        }
        else System.out.println("Wybrano nieprawidlowe haslo!");
    }

}
