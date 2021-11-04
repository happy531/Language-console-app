import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        while (true) {
            Scanner sc = new Scanner(System.in);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            Type languagesListType = new TypeToken<ArrayList<Language>>() {
            }.getType();
            List<Language> languages = gson.fromJson(new FileReader(
                    "C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json"), languagesListType);

            Parser parser = new Parser(languages);

            System.out.println();
            System.out.println("1. Search");
            System.out.println("2. Add language and words");
            System.out.println("3. Exit");
            System.out.print("Choose action: ");
            String userChoice = sc.next();

            switch (userChoice) {
                //deserialize
                case "1" -> {
                    System.out.print("Type word you're interested in: ");
                    String inputWord = sc.next();

                    System.out.println(parser.searchWord(inputWord));
                }
                //serialize
                case "2" -> {
                    Scanner sc2 = new Scanner(System.in);

                    System.out.println("language name: ");
                    String languageName = sc2.nextLine();

                    System.out.println("words (separated with space): ");
                    String words = sc2.nextLine();
                    List<String> wordsList = Arrays.asList(words.split(" "));

                    parser.addLanguage(languageName, wordsList);
                }
                case "3" -> System.out.println("exiting...");
                default -> System.out.println("Wrong option");
            }
        }
    }
}

