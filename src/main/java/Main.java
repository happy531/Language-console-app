import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        deserialize();
    }

    private static void deserialize() throws FileNotFoundException {

        Language[] languages = new Gson().fromJson(new FileReader(
                "C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json"), Language[].class);
        //System.out.println(languages[0].getLanguage_name());
        //System.out.println(languages[0].getWords());

        Scanner sc = new Scanner(System.in);
        String inputWord = sc.nextLine();

        List<String> validLanguages = new ArrayList<>();

        for (Language language : languages) {
            for (String word : language.getWords()) {
                if (inputWord.equals(word)) {
                    validLanguages.add(language.getLanguage_name());
                    break;
                }
            }
        }
        if (validLanguages.size() > 0) System.out.println(validLanguages);
        else System.out.println("There's no such word in any language");


    }
}
