package Service2;

import Service1.TimeTable;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static Service2.Global.List1;
import static Service2.Global.List2;

public class ToJSON {
    public static void serializeEstimatedSchedule() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(List1);
        objectWriter.writeValue(Paths.get("timeTable.json").toFile(), List1);
    }

    public static void serializeReport() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(List2);
        objectWriter.writeValue(Paths.get("Report.json").toFile(), List2);
    }

    public static void serializeReport(List<?> list) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        objectWriter.writeValue(Paths.get("Report.json").toFile(), list);
    }
}
