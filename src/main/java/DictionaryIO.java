import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.codehaus.jackson.map.ObjectMapper;

public class DictionaryIO {
    public static final String dictionaryPath = "src\\Main\\resources\\";
    public static final String PDFPath = "src\\Main\\PDF\\";

    static public void saveXML(String fileName, Dictionary words) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(dictionaryPath + fileName), words);
    }

    static public void saveJSON(String fileName, Dictionary words) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(dictionaryPath + fileName), words);
    }

    public static Dictionary readXML(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Dictionary words = xmlMapper.readValue(new File(dictionaryPath + fileName), Dictionary.class);
        return words;
    }

    public static Dictionary readJSON(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Dictionary words = mapper.readValue(new File(dictionaryPath + fileName), Dictionary.class);
        return words;
    }

    public static void saveDictionary(String fileName, Dictionary words) throws IOException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (extension.equals("xml"))
            saveXML(fileName, words);
        else if (extension.equals("json"))
            saveJSON(fileName, words);
    }

    public static Dictionary readDictionary(String fileXMLOrJSON) throws IOException {
        String extension = fileXMLOrJSON.substring(fileXMLOrJSON.lastIndexOf(".") + 1);
        if (extension.equals("xml"))
            return readXML(fileXMLOrJSON);
        if (extension.equals("json"))
            return readJSON(fileXMLOrJSON);
        throw new InternalError();
    }
}