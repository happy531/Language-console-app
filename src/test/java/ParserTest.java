import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ParserTest {
    String jsonAPI = """
            [
                {
                    "language_name": "English",
                    "words": [
                        "a",
                        "abandon",
                        "ability",
                        "able",
                        "abortion",
                        "about",
                        "above",
                        "abroad",
                        "absence"]}
            ]""";

    Type languagesListType = new TypeToken<ArrayList<Language>>() {
    }.getType();
    List<Language> languages = new Gson().fromJson(jsonAPI, languagesListType);

    @Test
    public void searchWordShouldNotBeNull() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);

        Parser parser = new Parser(languages);
        Assertions.assertNotNull(parser.searchWord(generatedString));
    }
}