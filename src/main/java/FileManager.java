import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public static List<String> getFilesName(String path) {
        File folder = new File(path);

        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile())
                .map(file -> file.getName())
                .collect(Collectors.toList());
    }

    public static boolean nameExisting(List<String> files, String fileName) {
        return files.stream().anyMatch(file -> file.equals(fileName));
    }

    public static boolean checkExtension(String fileName, String expectedExtension) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (extension.equals(expectedExtension)) return true;
        return false;
    }
}
