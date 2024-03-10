package pawlowski.dawid.drinkslibrary.model;

public enum DrinkPower {
    SOFT(1), MEDIUM(2), STRONG(3), EXTRA_STRONG(4), PURE_ALCOHOL(5);

    private final int numericValue;

    DrinkPower(int numericValue){
        this.numericValue = numericValue;
    }
    public int getNumericValue(){
        return numericValue;
    }
}
