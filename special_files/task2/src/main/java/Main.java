import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String jsonObject = listToJson(list);
        try (FileWriter file = new FileWriter("data.json")) {
            file.write(jsonObject);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);

        List<Employee> XmlList = parseXML("data.xml");
        jsonObject = listToJson(XmlList);
        try (FileWriter file = new FileWriter("data2.json")) {
            file.write(jsonObject);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
    }

    private static List<Employee> parseXML(String s) {
        List<Employee> result = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(s));
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node_ = nodeList.item(i);
                if (Node.ELEMENT_NODE == node_.getNodeType()) {
                    Element element = (Element) node_;
                    long id = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    Employee employee = new Employee(id,firstName,lastName,country,age);
                    result.add(employee);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static <T> String listToJson(List<T> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee> result = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            result = csv.parse();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
