import java.util.List;

public class Language {
    private final String language_name;
    private final List<String> words;

    public Language(String language_name, List<String> words) {
        this.language_name = language_name;
        this.words = words;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public List<String> getWords() {
        return words;
    }

}
