import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private final List<Language> languages;

    public Parser(List<Language> languages) {
        this.languages = languages;
    }

    public String searchWord(String inputWord) {
        List<String> validLanguages = this.languages
                .stream()
                .filter(language -> language.getWords().contains(inputWord))
                .map(Language::getLanguage_name)
                .collect(Collectors.toList());

        if (validLanguages.size() > 0) return "This word appears in this languages: " + validLanguages;
        else return "There's no such word in any language";
    }

    public void addLanguage(String languageName, List<String> words) throws IOException {
        Language userAddedLanguage = new Language(languageName, words);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.languages.add(userAddedLanguage);

        Writer writer = new FileWriter("./src/main/api.json");
        gson.toJson(this.languages, writer);
        writer.flush();
        writer.close();
    }
}
