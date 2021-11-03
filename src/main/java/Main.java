import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world");

        deserialize();
    }

    private static void deserialize() throws FileNotFoundException {
        Gson gson = new Gson();

        Language[] languages = gson.fromJson(new FileReader("C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json"), Language[].class);
        System.out.println(languages[0].getLanguage_name());
        System.out.println(languages[0].getWords());

    }
}
