package Service1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

public class RandomFieldsGenerator {

    public static String getName() {

        String[] listOfNames = {"Aurora", "Aqua", "Neva", "Lusitania2", "MikhailSvetlov", "Olimpic", "Titanic3", "Titan", "Danube",
                "Sava", "BelgradeRiver", "Moscow", "Spb", "Maurithania", "FinlandBay", "Blue", "Ocean", "Anchor",
                "Vltava", "Atlantic", "Pacific", "Indiana", "Artica", "Antartica", "Vessel", "Shark", "Dolphin"};

        int rand = new Random().nextInt(listOfNames.length);
        String randomName1 = (listOfNames[rand]);

        int randomName2 = (int) (Math.random() * 1000);
        var s = String.valueOf(randomName2);
        return randomName2 + randomName1;
    }

    public static Time getTime() {

        final Random random = new Random();
        final int millisInDay = 24 * 60 * 60 * 1000;
        return new Time((long) random.nextInt(millisInDay));
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public static LocalDate getDay() {
        final Random random = new Random();
        int firstDay = (int) LocalDate.of(2023, 5, 1).toEpochDay();
        int lastDay = (int) LocalDate.of(2023, 5, 30).toEpochDay();
        long randomDay = firstDay + random.nextInt(lastDay - firstDay);
        return LocalDate.ofEpochDay(randomDay);
    }


    public static Type getType() {
        Type[] enumArray = Type.values(); //строю массив значении enum-a
        Random rand = new Random();
        return Arrays.stream(enumArray).
                skip(rand.nextInt(enumArray.length)).
                findFirst().get(); //нахождение случайного значения в массиве
    }

    public static double getWeight(Type type) {
        double limit = switch (type) {
            case CONTAINER -> 50_000.0;
            case LIQUID -> 500_000.0;
            case BULK -> 990_000.0;
        };
        return (25_000 + (double) (Math.random() * limit)); //если вез груза меньше 25 000 тон, то тогда нет смысла его перевозить
    }

    public static double getUnload(Type type, double weight) {
        double carryingCapacityCrane = switch (type) {
            case CONTAINER -> 100.0;
            case LIQUID -> 500.0;
            case BULK -> 1000.0;
        };
        return 0.5 * (weight / carryingCapacityCrane) / 60.0; //конвертируем в час
    }
}
