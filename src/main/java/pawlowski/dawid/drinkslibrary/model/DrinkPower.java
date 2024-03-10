package pawlowski.dawid.drinkslibrary.model;

public enum DrinkPower {
    SOFT(1), MEDIUM(3), STRONG(5), EXTRA_STRONG(8), PURE_ALCOHOL(10);

    private final int numericValue;

    DrinkPower(int numericValue){
        this.numericValue = numericValue;
    }
    public int getNumericValue(){
        return numericValue;
    }
}
