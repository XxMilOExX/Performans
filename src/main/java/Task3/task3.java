package Task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.Iterator;

public class task3 {

    public static void main(String[] args) throws IOException {
        String valuesFile = "C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task3\\values.json";
        String testsFile = "C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task3\\tests.json";
        String reportFile = "C:\\Users\\miloe\\Downloads\\Performans\\src\\main\\java\\Task3\\reportFile.json";

        try {
            fillReport(valuesFile, testsFile, reportFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void fillReport(String valuesFile, String testsFile, String reportFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode valuesNode = mapper.readTree(new File(valuesFile));
        JsonNode testsNode = mapper.readTree(new File(testsFile));

        fillValues(testsNode, valuesNode);

        try (FileWriter fileWriter = new FileWriter(reportFile)) {
            mapper.writeValue(fileWriter, testsNode);
        }
    }

    private static void fillValues(JsonNode testsNode, JsonNode valuesNode) {
        fillValuesRecursive(testsNode, valuesNode);
    }

    private static void fillValuesRecursive(JsonNode node, JsonNode valuesNode) {
        if (node.isArray()) {
            for (JsonNode child : node) {
                fillValuesRecursive(child, valuesNode);
            }
        } else if (node.isObject()) {
            for (Iterator<String> fieldIterator = node.fieldNames(); fieldIterator.hasNext(); ) {
                String field = fieldIterator.next();
                if (field.equals("id")) {
                    int id = node.path("id").asInt();
                    for (JsonNode valueNode : valuesNode.path("values")) {
                        if (valueNode.path("id").asInt() == id) {
                            ObjectNode objectNode = (ObjectNode) node;
                            objectNode.set("value", valueNode.path("value"));
                            break;
                        }
                    }
                }
                JsonNode child = node.path(field);
                if (child.isArray() || child.isObject()) {
                    fillValuesRecursive(child, valuesNode);
                }
            }
        }
    }
}
