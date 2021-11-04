import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Shutdown hook ran!")));

        while (true) {
            System.out.println("1. Search");
            System.out.println("2. Add language and words");
            System.out.println("3. Exit");
            System.out.print("Choose action: ");

            Scanner sc = new Scanner(System.in);
            String userChoice = sc.next();

            switch (userChoice) {
                case "1":
                    deserialize();
                    break;
                case "2":
                    serialize();
                    break;
                case "3":
                    System.out.println("exiting...");
                default:
                    System.out.println("Wrong option");

            }
        }

    }

    private static void deserialize() throws FileNotFoundException {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Type languagesListType = new TypeToken<ArrayList<Language>>(){}.getType();
        List<Language> languages = gson.fromJson(new FileReader(
                "C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json"), languagesListType);

        Scanner sc = new Scanner(System.in);

        System.out.print("Type word you're interested in: ");
        String inputWord = sc.next();

        List<String> validLanguages = new ArrayList<>();

        for (Language language : languages) {
            for (String word : language.getWords()) {
                if (inputWord.equals(word)) {
                    validLanguages.add(language.getLanguage_name());
                    break;
                }
            }
        }
        if (validLanguages.size() > 0) System.out.println("This word appears in this languages: " + validLanguages);
        else System.out.println("There's no such word in any language");
    }

    private static void serialize() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("language name: ");
        String inputLanguage = sc.nextLine();

        System.out.println("words (separated with space): ");
        String words = sc.nextLine();
        List<String> inputWords = Arrays.asList(words.split(" "));

        Language userAddedLanguage = new Language(inputLanguage, inputWords);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Type languagesListType = new TypeToken<ArrayList<Language>>(){}.getType();
        List<Language> languages = gson.fromJson(new FileReader(
                "C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json"), languagesListType);
        languages.add(userAddedLanguage);

        Writer writer = new FileWriter("C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json");
        gson.toJson(languages, writer);
        writer.flush();
        writer.close();


    }
}
