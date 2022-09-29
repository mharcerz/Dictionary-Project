import java.io.IOException;
import java.util.*;

public class AddDictionary {

    private static String readFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jak nazwać ten plik? Możesz wybrać rozszerzenie \"xml\" lub \"json\":");
        List<String> namesOfFiles = FileManager.getFilesName(DictionaryIO.dictionaryPath);

        while (true) {
            String fileName = scanner.nextLine();
            if (!FileManager.checkExtension(fileName, "xml") && !FileManager.checkExtension(fileName, "json")) {
                System.out.println("Nazwa niepoprawna. Możesz wybrać rozszerzenie \"xml\" lub \"json\":");
                continue;
            }
            if (!FileManager.nameExisting(namesOfFiles, fileName)) return fileName;
            System.out.println("Nazwa już zajęta chcesz ją nadpisać? (TAK/NIE):");
            while (true) {
                String yesOrNot = scanner.nextLine();
                if (yesOrNot.equals("TAK")) return fileName;
                if (yesOrNot.equals("NIE")) {
                    System.out.println("Podaj inną nazwę z końcówką \"xml\" lub \"json\":");
                    break;
                }
                System.out.println("Podaj TAK/NIE:");
            }
        }
    }

    public static void addDictionary() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = readFileName();
        Dictionary words = new Dictionary();
        System.out.print("Podaj język: ");
        String language = scanner.nextLine();
        words.setLanguage(language);
        System.out.println("Podaj słowa (Jeśli chcesz skończyć podawanie wpisz \"./\" ) :");
        List<String> newWords = new ArrayList<String>();
        String word;
        while (true) {
            word = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (word.equals("./")) break;
            else newWords.add(word);
        }
        words.setWords(newWords);
        DictionaryIO.saveDictionary(fileName, words);

    }

}
