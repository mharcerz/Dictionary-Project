import java.util.*;

public class SearchForWords {

    public static void searchForWords() throws Throwable {
        Set<String> allLanguages = new TreeSet<>();
        int wordCount = 0;
        List<Dictionary> dictionaries = Dictionary.readDictionary();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj słowo do sprawdzenia lub \"1\" jak chcesz skończyć:");
            String wordToCheck = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (wordToCheck.equals("1")) break;
            wordCount++;
            Set<String> languages = Dictionary.searchWordInDictionaries(dictionaries, wordToCheck);
            if (languages.isEmpty())
                System.out.println("Nie ma takiego słowa w bazie");
            else System.out.println(languages);

            allLanguages.addAll(languages);
            languages.clear();

        }
        CreatePDF.writePDF(wordCount, allLanguages);
    }
}
