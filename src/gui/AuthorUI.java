package gui;

import editing.AuthorEditingOptions;
import model.Data;
import model.Status;
import reading.ReadingOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorUI {

    private final static Scanner scanner = new Scanner(System.in);
    private final static AuthorEditingOptions editingOptions = new AuthorEditingOptions();
    private final static ReadingOptions readingOptions = new ReadingOptions();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String choice;
        System.out.println("---Interfejs AUTORA systemu redagowania hasel encyklopedycznych---");

        while(true){

            System.out.println("1. Redagowanie hasla");
            System.out.println("2. Przegladanie listy hasel i ich statusow");
            System.out.println("3. Edycja hasla niezgloszonego lub zrecenzowanego ");
            System.out.println("4. Przekazanie definicji do recenzji");
            System.out.println("5. Aktualizacja danych");
            System.out.println("6. Zapisanie zmian do pliku");
            System.out.println("7. Wyjscie z programu");
            System.out.print("Wybor: ");
            choice = scanner.nextLine();

            switch(choice){
                case "1":
                    createNewDefinition();
                    break;

                case "2":
                    readingOptions.printDictionary(readingOptions.getAllStatuses(), Data.getDictionary());
                    break;

                case "3":
                    modifyDefinition();
                    break;

                case "4":
                    passDefinition();
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
    
    private static void createNewDefinition(){
        System.out.println("Nowe haslo: ");
        String name;
        name = scanner.nextLine();
        System.out.println("Definicja: ");
        String definition;
        definition = scanner.nextLine();
        boolean addingCorrect = editingOptions.createNewRecord(name, definition);
        if(addingCorrect) System.out.println("Wprowadzono nowe haslo");
        else System.out.println("Podjeto probe edycji istniejacego hasla");
    }

    private static void modifyDefinition(){
        List<Status> recordsPossibleToEdit = Arrays.asList(Status.EDYTOWANE, Status.ZRECENZOWANE);
        readingOptions.printDictionary(recordsPossibleToEdit, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToEdit = scanner.nextLine();
        System.out.print("Nowa definicja: ");
        String newDefinition;
        newDefinition = scanner.nextLine();

        boolean editingCorrect = editingOptions.definitionEdit(recordTryedToEdit, newDefinition);
        if(editingCorrect){
            System.out.println("Wprowadzenie nowej definicji zakonczone poprawnie");
        }
        else System.out.println("Wprowadzono niepoprawne haslo!");
    }

    private static void passDefinition(){
        List<Status> recordsToPass= List.of(Status.EDYTOWANE);
        readingOptions.printDictionary(recordsToPass, Data.getDictionary());

        System.out.print("Wybor hasla: ");
        String recordTryedToPass;
        recordTryedToPass = scanner.nextLine();

        boolean passCorrect = editingOptions.passRecord(recordTryedToPass);

        if(passCorrect){
            System.out.println("Poprawnie przekazano haslo do dalszego procesowania");
        }
        else System.out.println("Wprowadzono niepoprawne haslo!");
    }

}
