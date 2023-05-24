package restcontroller;

import Service1.TimeTable;
import Service2.ToJSON;
import Service3.Ship;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static Service2.Global.*;

@RestController
@RequestMapping("/api")
public class TimeTableController {

    //сервис 1
    @GetMapping("/timetable")
    public List<TimeTable> getTimeTable() {
        List<TimeTable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(new TimeTable());
        return list;
    }

    // сервис2 + сервис 3
    // возвращает json файлы по имени
    @GetMapping("/timetable/report/json")
    public List<? extends Object> getReportDocument(@RequestParam String name) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        if (name.equals("Report.json")) {
            List<Ship> element = objectMapper.readValue(
                    new File("Report.json"),
                    new TypeReference<List<Ship>>() {
                    });
            return element;
        } else if (name.equals("timeTable.json")) {
            List<TimeTable> element1 = objectMapper.readValue(
                    new File("timeTable.json"),
                    new TypeReference<List<TimeTable>>() {
                    });
            return element1;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "entity not found");
        }
    }

    // отправляет результаты работы сервиса 3 в json файл
    //метод выше возвращает также и Report.json
    @PostMapping("/timetable")
    public List<Ship> postTimeTable() throws IOException {
        List<Ship> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //Закыдиваем все судна в очередь
            Ship newShip = new Ship(); //создаём новое судно на каждой итерации
            list.add(newShip); //добавляем в очередь
        }
        ToJSON.serializeReport(list);
        return list;
    }

    @PostMapping("/timetable/custom")
    public ResponseEntity postTimeTableCustom(@RequestBody List<Ship> ships) throws IOException {
        ToJSON.serializeReport(ships);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
