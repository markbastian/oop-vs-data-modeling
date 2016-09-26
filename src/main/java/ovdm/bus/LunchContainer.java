package ovdm.bus;

import java.util.Collection;
import java.util.LinkedList;

public abstract class LunchContainer implements ILunchProvisionable {
    private final Collection<String> items = new LinkedList<>();
    @Override public double moneyAmount() { return 0.0; }

    public void addItem(String item){
        items.add(item);
    }

    @Override
    public long countItems() {
        return items.size();
    }

    @Override
    public long countItems(String type) {
        return items.stream().filter(item -> item.equals(type)).count();
    }
}
