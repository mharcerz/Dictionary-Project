import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.itextpdf.text.pdf.BaseFont;

public class CreatePDF {

    private static String readFileName()
    {
        List<String> namesOfFiles = FileManager.getFilesName(DictionaryIO.PDFPath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku do zapisu Statystyk z końcówką \"pdf\": ");

        while (true) {
            String fileName = scanner.nextLine();
            if(!FileManager.checkExtension(fileName,"pdf"))
            {
                System.out.println("Niepoprawna nazwa, podaj poprawną nazwę z rozszerzeniem \"pdf\":");
                continue;
            }
            if (!FileManager.nameExisting(namesOfFiles, fileName)) return fileName;
            System.out.println("Nazwa już zajęta chcesz ją nadpisać? (TAK/NIE):");
            while (true) {
                String yesOrNot = scanner.nextLine();
                if (yesOrNot.equals("TAK")) return fileName;
                if (yesOrNot.equals("NIE")) {
                    System.out.println("Podaj inną nazwę z końcówką \"pdf\":");
                    break;
                }
                System.out.println("Podaj TAK/NIE:");
            }
        }
    }
    public static void writePDF(int howMuchWords, Set<String> languages) throws Throwable {
        String fileName=readFileName();
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(helvetica, 16);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DictionaryIO.PDFPath+fileName));
        document.open();
        document.add(new Paragraph("Podano słów: " + howMuchWords, font));
        document.add(new Paragraph("", font));
        document.add(new Paragraph("Słowa te pochodziły z języków:", font));
        for (String language : languages)
            document.add(new Paragraph(language, font));
        document.close();
    }
}