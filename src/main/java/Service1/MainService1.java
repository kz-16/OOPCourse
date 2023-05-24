package Service1;


public class MainService1 {
    public static void main(String[] args) {

        System.out.println("Time table: \n");

        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ":");
            TimeTable obj = new TimeTable();
            System.out.println(obj);
        }
    }
}
