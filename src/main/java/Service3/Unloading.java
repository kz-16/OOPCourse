package Service3;

import Service1.Type;
import Service2.ToJSON;

import java.io.IOException;
import static Service2.Global.*;

public class Unloading {
    public static void simulate(int amount) {

        // ГЕНЕРАЦИЯ НОВЫХ СУДОВ
        ShipQueue shipQueue = ShipQueue.getInstance();
        for (int i = 0; i < amount; i++){
            shipQueue.put();
        }

        Cranes unloader1 = new Cranes(Type.CONTAINER);
        Cranes unloader2 = new Cranes(Type.LIQUID);
        Cranes unloader3 = new Cranes(Type.BULK);
        Thread t1 = new Thread(unloader1, "CONTAINER");
        Thread t2 = new Thread(unloader2, "LIQUID");
        Thread t3 = new Thread(unloader3, "BULK");

        t1.start();
        t2.start();
        t3.start();

        try { //дожидаемся окончания генерации всех судов
            t1.join();
            t2.join();
            t3.join();
            //когда ВСЕ потоки закончили свою работу, делаем сериализацию
            ToJSON.serializeReport();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}