import java.util.*;

public class Main {
    public static void main(String[] arg) throws Throwable {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Jeśli chcesz wpisać nowy słownik wpisz 1, jeśli chcesz sprawdzić słowo, wpisz 2, jeśli chcesz zakończyć, wpisz 3");
            int option = scanner.nextInt();
            if (option == 1 )
                AddDictionary.addDictionary();
            else if (option == 2)
                SearchForWords.searchForWords();
            else if (option == 3) break;
            else System.out.println("Nie ma takiej opcji");
        }
    }
}
