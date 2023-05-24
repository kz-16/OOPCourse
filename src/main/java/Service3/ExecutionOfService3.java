package Service3;

import Service1.Type;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;

public class ExecutionOfService3 {

    public static double getUnloadDelay() { //задержка разгрузки в часах
        // (случайная величина от 0 до 1440 минутах (24 часа))

        return ((double) (Math.random() * 24.0));
    }

    public static double getFullUnloadTime(double unloadTime, double unloadDelayTime) {
        //задержка разгрузки в часах (случайная величина от 0 до 1440 минутах (24 часа))

        return (unloadDelayTime+unloadTime);
    }

    public static double getArrivalDeviation() {  //отклонение в прибытии

        double minimalArrivalEarlier = -168.0; //7 дней (7*24 часов)
        double maximumArrivalLater = 168.0;
        return (minimalArrivalEarlier + ((double) 2*(Math.random() * maximumArrivalLater)));
    }

    public static double getPenalty(double delay) { //с каждым часом задержки, штраф увеличывается за 100
        return delay * 100;
    }

    public static Time getRealTimeOfArrival(Time estimatedTime, double deviation){
        //получаем реальное время прибытия корабля расписание+отклонение
        //отклонение преобразуем в время типа Time
        long deviationInMillis= (long) (deviation*86400*1000); //конвертируем отклонение из часов в милисекунды
        long fullTime=estimatedTime.getTime()+deviationInMillis; //конвертитуем время по рассписанию в милисекунды
        long realTime=(long) (fullTime*24 * 60 * 60); //получаем точное время
        //не забываем что отклонение может идти и с минусом, т.к. судно можеть заранее прибыть
        Time timeOfArrival=new Time(realTime);
        return timeOfArrival;
    }

    //здесь есть маленькая нелогичность
    public static LocalDate getRealDayOfArrival(LocalDate estimatedDay, Time realArrivalTime, double deviation){
        if (deviation>0.00){ //день позже
            int exactAmountOfDaysLater= (int) (deviation/24.0);
            return estimatedDay.plus(Period.ofDays(exactAmountOfDaysLater));
        }
        else /*if (deviation<-24.00)*/{ //день раньше
            int exactAmountOfDaysEarly= (int) (deviation/24.0);
            return estimatedDay.minus(Period.ofDays(exactAmountOfDaysEarly));

        }
        /*else{
            return estimatedDay; //возвращает ту же дату потому что задержка всего несколько часов ТОГО же дня
        }*/
    }

    //problem here!
    public static Time getTimeOfWaitingForUnloading(Type typeOfShip, double estimatedUnload) {
        double delay = ExecutionOfService3.getUnloadDelay();
        long timeOfWaiting= (long) (estimatedUnload+delay);
        Time time= new Time(timeOfWaiting);
        return time;
    }

    public static Time getBeginningUnloading(Time timeWaiting, Time arrivalTime) {
        long t1=timeWaiting.getTime();
        long t2=arrivalTime.getTime();
        long begining=t1+t2;
        Time beginingUnloadTime=new Time(begining);
        return beginingUnloadTime;

    }


}