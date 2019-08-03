public class customerBond extends Bonds {
    private int maturity;
    private

    public customerBond(int value, double i, int length, String type) {
        super(value, i, length, type);
    }

    public int getMaturity() {
        return maturity;
    }

}
