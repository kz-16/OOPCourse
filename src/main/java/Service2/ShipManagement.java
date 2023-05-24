package Service2;

import Service1.RandomFieldsGenerator;
import Service1.TimeTable;
import Service1.Type;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static Service2.Global.List1;

public class ShipManagement {

    public static void createShip(int initialAmountOfShips) throws IOException {
        System.out.println("Time table generating....: \n");
        generateShips(initialAmountOfShips);
        while (true) {
            System.out.println("Enter new ship? y/n");
            Scanner input = new Scanner(System.in);
            String answer1 = input.next();

            if (answer1.equals("n")) {
                ToJSON.serializeEstimatedSchedule();
                System.out.println("Time table completed! Check JSON file!");
                System.exit(0);
                break;
            }
            else if (answer1.equals("y")) {
                TimeTable newShip = new TimeTable();

                System.out.println("Enter the name of the ship:  ");
                String name = input.next();
                newShip.setName(name);

                System.out.println("Enter the time of arrival according to schedule in MILLISECONDS: ");
                long timeInMilliseconds = input.nextLong();
                java.sql.Time time = new java.sql.Time(timeInMilliseconds);
                newShip.setTime(time);

                System.out.println("Enter day (only if arrival is planned in november 2020): ");
                int day = input.nextInt();
                LocalDate date = LocalDate.of(2020, 11, day);
                newShip.setDay(date);

                System.out.println("Enter the type (BULK, LIQUID or CONTAINER !!!): ");
                Type type1 = Type.valueOf(input.next());
                newShip.setType(type1);

                System.out.println("Enter the weight");
                double weight1 = input.nextDouble();

                ShipManagement.checkType(type1, weight1);

                newShip.setWeight(weight1);
                double unload1 = RandomFieldsGenerator.getUnload(type1, weight1);
                newShip.setUnload(unload1);

                System.out.println("-------------------");
                System.out.println(newShip);
                List1.add(newShip);
                ToJSON.serializeEstimatedSchedule();

            } else {
                System.out.println("Invalid symbol!");
                System.exit(1);
            }
        }
    }

    public static void generateShips(int initialAmountOfShips){
        for (int i = 1; i <= initialAmountOfShips; i++) {
            TimeTable obj = new TimeTable();
            List1.add(obj);
        }
    }
    public static void checkType(Type typeCheck, double weightCheck){
        if (weightCheck < 25_000) {
            System.out.println("Error! Cargo weight is never less than 25 000 tones!");
            System.exit(2);
        }
        if (typeCheck.toString().equals("BULK") && weightCheck>990_000) {
            System.out.println("This type doesn't support more than 990 000 tones!");
            System.exit(2);
        }
        else if (typeCheck.toString().equals("CONTAINER") && weightCheck>50_000) {
            System.out.println("This type doesn't support more than 50 000 tones!");
            System.exit(2);
        }
        else if (typeCheck.toString().equals("LIQUID") && weightCheck>500_000) {
            System.out.println("This type doesn't support more than 500 000 tones!");
            System.exit(2);
        }
    }
}
