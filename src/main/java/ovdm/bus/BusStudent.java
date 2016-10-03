package ovdm.bus;

public class BusStudent extends BusPassenger {
    private ILunchProvisionable lunchProvider;

    public ILunchProvisionable getLunchProvider() {
        return lunchProvider;
    }

    public void setLunchProvider(ILunchProvisionable lunchProvider) {
        this.lunchProvider = lunchProvider;
    }
}
