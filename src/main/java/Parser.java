import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Language> languages;

    public Parser(List<Language> languages) {
        this.languages = languages;
    }

    public String searchWord(String inputWord) {
        List<String> validLanguages = new ArrayList<>();

        for (Language language : this.languages) {
            for (String word : language.getWords()) {
                if (inputWord.equals(word)) {
                    validLanguages.add(language.getLanguage_name());
                    break;
                }
            }
        }
        if (validLanguages.size() > 0) return "This word appears in this languages: " + validLanguages;
        else return "There's no such word in any language";
    }

    public void addLanguage(String languageName, List<String> words) throws IOException {
        Language userAddedLanguage = new Language(languageName, words);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.languages.add(userAddedLanguage);

        Writer writer = new FileWriter("C:\\Users\\jedrz\\IdeaProjects\\iteration\\src\\main\\java\\api.json");
        gson.toJson(this.languages, writer);
        writer.flush();
        writer.close();
    }
}
