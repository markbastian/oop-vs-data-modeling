package ovdm.bus;

import java.text.DecimalFormat;

public class AppMain {
    public static void main(String[] args){
        Bus bus = new Bus();

        //Add the first student
        BusStudent student0 = new BusStudent();
        LunchBox l0 = new LunchBox();
        l0.addItem("BANANA");
        student0.setLunchProvider(l0);
        bus.addPassenger(student0);

        //Add the second student
        BusStudent student1 = new BusStudent();
        LunchSack l1 = new LunchSack();
        l1.addItem("APPLE");
        l1.addItem("BANANA");
        l1.addItem("SANDWICH");
        student1.setLunchProvider(l1);
        bus.addPassenger(student1);

        //Add the third student
        BusStudent student2 = new BusStudent();
        LunchMoney l2 = new LunchMoney();
        l2.setAmount(5.0);
        student2.setLunchProvider(l2);
        bus.addPassenger(student2);

        DecimalFormat df = new DecimalFormat("\u00A4.00");
        System.out.println("Lunch money: " + df.format(bus.countLunchMoney()));
        System.out.println("Lunch items: " + bus.countLunchItems());
        System.out.println("Bananas: " + bus.countLunchItems("BANANA"));
        System.out.println("Sandwiches: " + bus.countLunchItems("SANDWICH"));
        System.out.println("Pears: " + bus.countLunchItems("PEAR"));
    }
}
