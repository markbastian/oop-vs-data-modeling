package ovdm.simplebus;

import ovdm.bus.*;

import java.util.Collection;
import java.util.LinkedList;

public class AppMain {
    public static void main(String[] args){
        Collection<BusOccupant> bus = new LinkedList<>();
        bus.add(new BusDriver());

        //Add the first student
        BusStudent student0 = new BusStudent();
        LunchBox l0 = new LunchBox();
        l0.addItem("BANANA");
        student0.setLunchProvider(l0);
        bus.add(student0);

        //Add the second student
        BusStudent student1 = new BusStudent();
        LunchSack l1 = new LunchSack();
        l1.addItem("APPLE");
        l1.addItem("BANANA");
        l1.addItem("SANDWICH");
        student1.setLunchProvider(l1);
        bus.add(student1);

        //Add the third student
        BusStudent student2 = new BusStudent();
        LunchMoney l2 = new LunchMoney();
        l2.setAmount(5.0);
        student2.setLunchProvider(l2);
        bus.add(student2);
    }
}
