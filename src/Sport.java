public class Sport {
    private boolean manualGear;
    private boolean over200HP;

    public Sport(boolean manualGear, boolean over200HP) {
        this.manualGear = manualGear;
        this.over200HP = over200HP;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "manualGear=" + manualGear +
                ", over200HP=" + over200HP +
                '}';
    }

    public boolean isManualGear() {
        return manualGear;
    }

    public void setManualGear(boolean manualGear) {
        this.manualGear = manualGear;
    }

    public boolean isOver200HP() {
        return over200HP;
    }

    public void setOver200HP(boolean over200HP) {
        this.over200HP = over200HP;
    }
}
