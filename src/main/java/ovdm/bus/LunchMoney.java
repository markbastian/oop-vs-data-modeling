package ovdm.bus;

public class LunchMoney extends Money implements ILunchProvisionable {
    @Override public int countItems() { return 0; }
    @Override public int countItems(String type) { return 0; }

    @Override
    public double moneyAmount() {
        return getAmount();
    }
}
