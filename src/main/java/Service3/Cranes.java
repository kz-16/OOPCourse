package Service3;

import Service1.Type;
import lombok.SneakyThrows;

public class Cranes implements Runnable {

    private Type type;
    ShipQueue shipQueue;

    public Cranes(Type type) {
        this.type = type;
        shipQueue = ShipQueue.getInstance();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Ship ship = shipQueue.get(this.type);
            if (ship == null){
                System.out.println("!!!!!!!!!!!!!!!!!!!! ПОТОК " + Thread.currentThread().getName() + " ЗАВЕРШИЛСЯ ");
                return;
            }

            System.out.println("-- Thread Id: " + Thread.currentThread().getId() + " Thread Name: " + Thread.currentThread().getName() +
                    " взял на разгрузку корабль: " + ship.getNameOfTheShip());

            double time = ship.getEstimatedTimeOfUnload() + ship.getDelayUnload();

            //обратный счёт до окончания разгрузки
            while (time > 0) {
                System.out.println("---- Thread Id: " + Thread.currentThread().getId() + " " + ship.getNameOfTheShip() + " осталось время " + time);
                for (int i = 0; i < 55 && time > 0; i++) {
                    time -= 0.1;
                    Thread.sleep(1);
                }
            }
            System.out.println("-- Thread Id: " + Thread.currentThread().getId() + " " + ship.getNameOfTheShip() + " Закончил разгрузку");
        }
    }
}



