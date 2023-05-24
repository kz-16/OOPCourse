package Service3;


import java.io.IOException;
import java.util.Scanner;

public class MainService3 {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите число судов:");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        Unloading.simulate(amount);
        //FromJSON.deserializeReport();
        Stats obj=new Stats();
        System.out.println(obj);

    }
}
