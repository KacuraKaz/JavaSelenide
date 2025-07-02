package qaTestProject.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

    public static ObjectNode createRateJson(Double yuanRate, Double euroRate, Double dollarUsaRate) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();
        result.put("Юань", yuanRate);
        result.put("Евро", euroRate);
        result.put("Доллар США", dollarUsaRate);
        return result;
    }

    // Добавит единицу к курсу валют
    public static ObjectNode changeRateJson(ObjectNode rates){
        ObjectNode result = rates;
        result.put("Юань", rates.get("Юань").asDouble()+1);
        result.put("Евро", rates.get("Евро").asDouble()+1);
        result.put("Доллар США", rates.get("Доллар США").asDouble()+1);
        return result;
    }

    public static void writeJsonToFile(ObjectNode json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("etalon.json"), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObjectNode readJsonFromFile() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return (ObjectNode) mapper.readTree(new File("etalon.json"));
    }

    public static String ratesDiffPrint(ObjectNode actual, ObjectNode expected){
        Iterator<Map.Entry<String, JsonNode>> fields = expected.fields();
        String result = "";
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            Double expectedValued = entry.getValue().asDouble();
            Double actualValue = actual.get(key).asDouble();
            double diff = expectedValued - actualValue;
            if (diff != 0){
                result = result + "Курс "+ key + " оличается на " + diff +"\n";
            }

        }
        return result;
    }


}

