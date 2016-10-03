package ovdm.bus;

import java.util.ArrayList;
import java.util.Collection;

public class Bus {
    public BusDriver driver;
    private final Collection<BusPassenger> students = new ArrayList<>();

    public BusDriver getDriver() {
        return driver;
    }

    public void setDriver(BusDriver driver) {
        this.driver = driver;
    }

    public void addPassenger(BusPassenger passenger){
        students.add(passenger);
    }

    public int countLunchItems(){
        int items = 0;

        for(BusPassenger passenger : students){
            BusStudent student = (BusStudent)passenger;
            items += student.getLunchProvider().countItems();
        }
        return items;
    }

    public int countLunchItems(String item){
        int items = 0;

        for(BusPassenger passenger : students){
            BusStudent student = (BusStudent)passenger;
            items += student.getLunchProvider().countItems(item);
        }
        return items;
    }

    public double countLunchMoney(){
        double money = 0.0;

        for(BusPassenger passenger : students){
            BusStudent student = (BusStudent)passenger;
            money += student.getLunchProvider().moneyAmount();
        }
        return money;
    }
}
