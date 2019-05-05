package constantmultiplier;

public class ConstantMultiplierAlgorithm {
    private int numberQuantity;
    private long seed;
    private long constant;

    ConstantMultiplierAlgorithm(int numberQuantity, long seed, long constant) {
        setNumberQuantity(numberQuantity);
        setSeed(seed);
        setConstant(constant);
    }
    ConstantMultiplierAlgorithm(){}

    // Order params(numberQuantity, seed, constant)
    public String[] generateNumbers(Object ... params) {
        int numberQuantity = params.length > 0 ? Integer.parseInt(params[0].toString()) : getNumberQuantity();
        long seed = params.length > 1 ? Long.parseLong(params[1].toString()) : getSeed();
        long constant = params.length > 2 ? Long.parseLong(params[2].toString()) : getConstant();
        String[] numbers = new String[numberQuantity];
        var y0 = constant * seed;
        int dDigits = String.valueOf(seed).length();
        String centerDigits = centerDigits(y0, dDigits);
        numbers[0] = "0." + centerDigits;
        for(var i = 1; i < numberQuantity; i++) {
            y0 = constant * Long.parseLong(centerDigits);
            centerDigits = centerDigits(y0, dDigits);
            numbers[i] = "0." + centerDigits;
        }
        return numbers;
    }

    private String centerDigits(long y0, int dDigits) {
        int lengthDigitsY0 = String.valueOf(y0).length();
        int start = (lengthDigitsY0 - dDigits) / 2;
        int end = ((lengthDigitsY0 - dDigits) / 2) + dDigits;
        return String.valueOf(y0).substring(start, end);
    }

    public int getNumberQuantity() {
        return this.numberQuantity;
    }
    public long getSeed() {
        return this.seed;
    }
    public long getConstant() {
        return this.constant;
    }

    public void setNumberQuantity(int numberQuantity) {
        this.numberQuantity = numberQuantity;
    }
    public void setSeed(long seed) {
        this.seed = seed;
    }
    public void setConstant(long constant) {
        this.constant = constant;
    }
}
