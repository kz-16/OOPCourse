package Service3;

import Service1.RandomFieldsGenerator;
import Service1.Type;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//судно прибывает в порт, с текущими данными мы его закидиваем в очередь
//а потом и в новый json файл report.json
public class Ship {
    @Getter @Setter
    private String  nameOfTheShip;
    @Getter @Setter
    private Time estimatedTimeOfArrival;
    @Getter @Setter
    private double deviationInArrival;
    @Getter @Setter
    private Time definiteTimeOfArrival;
    @Getter @Setter
    private Time WaitingInQueue; //время ожидания в очереди
    @Getter @Setter
    private Time beginningOfUnloading; //начало разгрузки3
    @Getter @Setter
    private Type typeOfTheShip;
    @Getter @Setter
    private double  exactWeight;
    @Getter @Setter
    public double estimatedTimeOfUnload;
    @Getter @Setter
    public double delayUnload; //задержка разгрузки
    @Getter @Setter
    public double penalty;    //штраф
    private LocalDate estimatedDayOfArrival;
    private LocalDate RealDayOfArrival;

    public Ship(){
        this.nameOfTheShip=RandomFieldsGenerator.getName();
        this.estimatedTimeOfArrival=RandomFieldsGenerator.getTime();
        this.deviationInArrival=ExecutionOfService3.getArrivalDeviation();
        this.definiteTimeOfArrival=ExecutionOfService3.getRealTimeOfArrival(estimatedTimeOfArrival, deviationInArrival);
        this.estimatedDayOfArrival=RandomFieldsGenerator.getDay();
        this.RealDayOfArrival=ExecutionOfService3.getRealDayOfArrival(estimatedDayOfArrival,
                definiteTimeOfArrival, deviationInArrival);
        this.typeOfTheShip=RandomFieldsGenerator.getType();
        this.exactWeight=RandomFieldsGenerator.getWeight(typeOfTheShip);
        this.estimatedTimeOfUnload=RandomFieldsGenerator.getUnload(typeOfTheShip, exactWeight);
        this.WaitingInQueue=ExecutionOfService3.getTimeOfWaitingForUnloading(typeOfTheShip, estimatedTimeOfUnload);
        this.beginningOfUnloading=ExecutionOfService3.getBeginningUnloading(WaitingInQueue, definiteTimeOfArrival);
        this.delayUnload=ExecutionOfService3.getUnloadDelay();
        this.penalty=ExecutionOfService3.getPenalty(delayUnload);
    }


    public String getEstimatedDayOfArrival() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.estimatedDayOfArrival.format(formatter);
    }
    public void setEstimatedDayOfArrival(Object estimatedDayOfArrival) {
        if (estimatedDayOfArrival instanceof String) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.estimatedDayOfArrival = LocalDate.parse((String) estimatedDayOfArrival, formatter);
        } else if (estimatedDayOfArrival instanceof LocalDate) {
            this.estimatedDayOfArrival = (LocalDate) estimatedDayOfArrival;
        }
    }
    public String getRealDayOfArrival() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.RealDayOfArrival.format(formatter);
    }
    public void setRealDayOfArrival(Object RealDayOfArrival) {
        if (RealDayOfArrival instanceof String) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.RealDayOfArrival = LocalDate.parse((String) RealDayOfArrival, formatter);
        } else if (RealDayOfArrival instanceof LocalDate) {
            this.RealDayOfArrival = (LocalDate) RealDayOfArrival;
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                "nameOfTheShip='" + nameOfTheShip + "\n"+
                ", estimatedTimeOfArrival=" + estimatedTimeOfArrival + "\n"+
                ", deviationInArrival=" + deviationInArrival +"\n"+
                ", definiteTimeOfArrival=" + definiteTimeOfArrival + "\n"+
                ", WaitingInQueue=" + WaitingInQueue + "\n"+
                ", beginningOfUnloading=" + beginningOfUnloading + "\n"+
                ", estimatedDayOfArrival=" + estimatedDayOfArrival + "\n"+
                ", RealDayOfArrival=" + RealDayOfArrival +"\n"+
                ", typeOfTheShip=" + typeOfTheShip +"\n"+
                ", exactWeight=" + exactWeight +"\n"+
                ", estimatedTimeOfUnload=" + estimatedTimeOfUnload +"\n"+
                ", delayUnload=" + delayUnload +"\n"+
                ", penalty=" + penalty +"\n"+
                '}';
    }

}
