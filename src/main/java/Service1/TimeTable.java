package Service1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeTable {

    //содержимое расписания, входит в json файл
    protected String name; //название судна
    protected Time time; //время прибытия
    protected LocalDate day; //день прибытия
    protected Type type; //тип груза
    protected double weight; //вес груза
    protected double unload; //Время разгрузки

    public TimeTable() {
        this.name = RandomFieldsGenerator.getName();
        this.time = RandomFieldsGenerator.getTime();
        this.day = RandomFieldsGenerator.getDay();
        this.type = RandomFieldsGenerator.getType();
        this.weight = RandomFieldsGenerator.getWeight(type);
        this.unload = RandomFieldsGenerator.getUnload(type, weight);
    }

    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("0.00");
        return "\nName of the ship: "+name + "\nEstimated time: " + time + "\nEstimated day: " + day + "\nType: " + type +
                "\nWeight: " + df2.format(weight) +
                " tones\nEstimated duration of unload: "
                + df2.format(unload) + " hours\n" +  "-------------------------\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.day.format(formatter);
    }

    public void setDay(Object day) {
        if (day instanceof String) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.day = LocalDate.parse((String) day, formatter);
        } else if (day instanceof LocalDate) {
            this.day = (LocalDate) day;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getUnload() {
        return unload;
    }

    public void setUnload(double unload) {
        this.unload = unload;
    }
}


