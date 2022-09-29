import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DictionaryTest {

    @Before
    public void setUp() {
        System.out.println("Run setUp");
    }

    @Test
    public void searchWordInDictionariesTestManyDictionaries() {
        Dictionary words1 = new Dictionary();
        Dictionary words2 = new Dictionary();
        words1.setLanguage("English");
        words2.setLanguage("Polish");
        words1.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        words2.setWords(Arrays.asList("słońce","samochód","latarka","kubek","klucze"));
        Set<String> result = Dictionary.searchWordInDictionaries(Arrays.asList(words1,words2), "ship");
        assertEquals(new HashSet<String>() {{add("English");}}, result);
        Set<String> result2 = Dictionary.searchWordInDictionaries(Arrays.asList(words1,words2), "rower");
        assertNotEquals(new HashSet<String>() {{add("English");}}, result2);
    }
    
    @Test
    public void searchWordInDictionariesTestOneDictionary() {
        Dictionary words = new Dictionary();
        words.setLanguage("English");
        words.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        Set<String> result = Dictionary.searchWordInDictionaries(Arrays.asList(words), "car");
        assertEquals(new HashSet<String>() {{add("English");}}, result);
        Set<String> result2 = Dictionary.searchWordInDictionaries(Arrays.asList(words), "vegetables");
        assertNotEquals(new HashSet<String>() {{add("English");}}, result2);

    }
    
    @Test
    public void searchWordInDictionariesTestTheSameWords() {
        Dictionary words1 = new Dictionary();
        Dictionary words2 = new Dictionary();
        words1.setLanguage("English");
        words2.setLanguage("English");
        words1.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        words2.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        Set<String> result = Dictionary.searchWordInDictionaries(Arrays.asList(words1,words2), "ship");
        assertEquals(new HashSet<String>() {{add("English");}}, result);
    }

    @Test
    public void searchWordInDictionariesTestEmptyDictionary() {
        Dictionary words = new Dictionary();
        Set<String> result = Dictionary.searchWordInDictionaries(Arrays.asList(words), "car");
        assertNotEquals(new HashSet<String>() {{add("English");}}, result);
    }

    @Test
    public void searchWordInDictionariesTestEmptyList() {
        Set<String> result = Dictionary.searchWordInDictionaries(Arrays.asList(), "car");
        assertNotEquals(new HashSet<String>() {{add("English");}}, result);
    }

    @Test
    public void searchAWordTest() {
        Dictionary words1 = new Dictionary();
        Dictionary words2 = new Dictionary();
        words1.setLanguage("English");
        words2.setLanguage("English");
        words1.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        words2.setWords(Arrays.asList("sunrise","car","motorbike","ship","spaceship"));
        assertTrue(words1.searchAWord("sunrise"));
        assertFalse(words2.searchAWord("sunglasses"));
    }

    @Test
    public void searchAWordTestEmptyDictionary() {
        Dictionary words = new Dictionary();
        assertFalse(words.searchAWord("sunrise"));
    }
}
