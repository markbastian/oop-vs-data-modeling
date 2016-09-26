package ovdm.bus;

public class LunchMoney extends Money implements ILunchProvisionable {
    @Override public long countItems() { return 0; }
    @Override public long countItems(String type) { return 0; }

    @Override
    public double moneyAmount() {
        return getAmount();
    }
}
