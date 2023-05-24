package Service3;

import Service2.Global;
import java.sql.Time;

public class DoStatistics {

    public static int getUnloadedShipsAmount() {
        int bulkLength = ShipQueue.getInstance().getBulkAmount();
        int liquidLength = ShipQueue.getInstance().getLiquidAmount();
        int containerLength = ShipQueue.getInstance().getContainerAmount();
        return bulkLength + liquidLength + containerLength;
    }


    public static Time getTimeOfWaiting() {
        double time = Global.List2.stream().mapToDouble(v -> (v.getEstimatedTimeOfUnload() + v.getDelayUnload())).average().getAsDouble();
        return new Time((int) time % 24, (int) ((time % 1) * 60) , 0 );
    }

    public static double getMaxDelay() {
        return Global.List2.stream().mapToDouble(Ship::getDelayUnload).max().getAsDouble();
    }

    public static double getAverageDelay() {
        return Global.List2.stream().mapToDouble(Ship::getDelayUnload).average().getAsDouble();
    }

    public static double getMaxPenalty() {
        return Global.List2.stream().mapToDouble(Ship::getPenalty).max().getAsDouble();
    }

    public static int getNeededCranes() {
        return 3;
    }

    public static double getAverageLengthOfTheQueue() {
        int bulkLength = ShipQueue.getInstance().getBulkAmount();
        int liquidLength = ShipQueue.getInstance().getLiquidAmount();
        int containerLength = ShipQueue.getInstance().getContainerAmount();
        return (bulkLength + liquidLength + containerLength) / 3.0;

    }
}
