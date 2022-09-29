import com.fasterxml.jackson.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope= Dictionary.class)

public class Dictionary {
    @JsonProperty("words")
    private List<String> words;
    @JsonProperty("language")
    private String language;

    public String getLanguage() {
        return language;
    }

    public List<String> getWords() {
        return words;
    }

    public boolean searchAWord(String wordToFind) {
        List<String> wordsToFilter;
        if(Objects.nonNull(words)) {
            wordsToFilter = words.stream()
                    .filter(word -> word.equals(wordToFind))
                    .collect(Collectors.toList());

            if (wordsToFilter.isEmpty())
                return false;
            return true;
        }
        return false;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    @Override
    public String toString() {
        return String.format(language + "   "+ words);
    }

    public static Set<String> searchWordInDictionaries(List<Dictionary> dictionaries, String word) {
        return dictionaries.stream()
            .filter(dictionary -> dictionary.searchAWord(word))
                .map(dictionary -> dictionary.getLanguage())
                .collect(Collectors.toSet());
    }
    public static List<Dictionary> readDictionary() throws IOException, InternalError {
        List<Dictionary> dictionaries = new LinkedList<>();
        List<String> namesOfFiles = FileManager.getFilesName(DictionaryIO.dictionaryPath);
        for (String fileXMLOrJSON : namesOfFiles) {
            dictionaries.add(DictionaryIO.readDictionary(fileXMLOrJSON));
        }
        return dictionaries;
    }
}
