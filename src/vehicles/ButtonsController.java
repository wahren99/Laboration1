package vehicles;

public class ButtonsController implements ButtonsView.Observer {
    private final CarModel model;

    public ButtonsController(CarModel model) {
        this.model = model;
    }

    @Override
    public void wantStartEngine() {
        model.startEngine();
    }

    @Override
    public void wantGas(int amount) {
        model.gas(amount);
    }

    @Override
    public void wantBrake(int amount) {
        model.brake(amount);
    }

    @Override
    public void wantLiftTruckBed() {
        model.liftTruckBed();
    }

    @Override
    public void wantLowerTruckBed() {
        model.lowerTruckBed();
    }

    @Override
    public void wantTurnTurboOff() {
        model.turnTurboOff();
    }

    @Override
    public void wantTurnTurboOn() {
        model.turnTurboOn();
    }

    @Override
    public void wantStopEngine() {
        model.stopEngine();
    }
}
