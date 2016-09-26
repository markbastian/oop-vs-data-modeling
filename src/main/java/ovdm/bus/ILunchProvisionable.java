package ovdm.bus;

public interface ILunchProvisionable {
    long countItems();
    long countItems(String type);
    double moneyAmount();
}
