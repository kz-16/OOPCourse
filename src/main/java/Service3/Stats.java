package Service3;

import lombok.Data;

import java.sql.Time;

@Data
public class Stats {
    private int amountOfUnloadedShips;
    private double averageLengthOfQueue;
    private Time timeOfWaitingInQueue;
    private double maxUnloadDelay;
    private double averageUnloadDelay;
    private double maximumPenalty;
    private int amountOfNeededCranes;

    public Stats() {
        this.amountOfUnloadedShips = DoStatistics.getUnloadedShipsAmount();
        this.timeOfWaitingInQueue = DoStatistics.getTimeOfWaiting();
        this.maxUnloadDelay = DoStatistics.getMaxDelay();
        this.averageUnloadDelay = DoStatistics.getAverageDelay();
        this.maximumPenalty = DoStatistics.getMaxPenalty();
        this.amountOfNeededCranes = DoStatistics.getNeededCranes();
        this.averageLengthOfQueue=DoStatistics.getAverageLengthOfTheQueue();
    }


    @Override
    public String toString() {
        return "Stats{" +
                "amountOfUnloadedShips=" + amountOfUnloadedShips +
                ", timeOfWaitingInQueue=" + timeOfWaitingInQueue +
                ", maxUnloadDelay=" + maxUnloadDelay +
                ", averageUnloadDelay=" + averageUnloadDelay +
                ", maximumPenalty=" + maximumPenalty +
                ", amountOfNeededCranes=" + amountOfNeededCranes +
                '}';
    }
}
