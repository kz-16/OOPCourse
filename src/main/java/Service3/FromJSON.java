package Service3;

import Service1.TimeTable;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class FromJSON {
    static void deserializeTimeTable() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(new File("timeTable.json"));
            List<TimeTable> lst = null;

            TypeReference<List<TimeTable>> tRef = new TypeReference<List<TimeTable>>() {
            };
            lst = objectMapper.readValue(jp, tRef);
            for (TimeTable user : lst) {
                System.out.println(user.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void deserializeReport() throws IOException {
        System.out.println("Data from JSON file: \n");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(new File("Report.json"));
            List<Ship> lst = null;

            TypeReference<List<Ship>> tRef = new TypeReference<List<Ship>>() {
            };
            lst = objectMapper.readValue(jp, tRef);
            for (Ship user : lst) {
                System.out.println(user.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
