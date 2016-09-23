package ovdm.bus;

public interface ILunchProvisionable {
    int countItems();
    int countItems(String type);
    double moneyAmount();
}
