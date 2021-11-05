import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Statistics {
    private int numOfGivenWords = 0;
    private final Stack<String> languageNames = new Stack<>();

    public Statistics() {
    }

    public void incrementNumOfWords(Integer quantity){
        this.numOfGivenWords+=quantity;
    }

    public void addLanguageToStack(String language){
        this.languageNames.push(language);
    }

    public int getNumOfGivenWords() {
        return numOfGivenWords;
    }

    public List<String> getLanguageNames() {
        return this.languageNames.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
