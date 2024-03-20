package workwithzip.model;

public class Storage {
    private  int aisle;
    private  int bin;

    public Storage(){};
    public Storage(int  aisle, int bin) {
        this.aisle = aisle;
        this.bin = bin;
    }

    public int getAisle() {
        return aisle;
    }

    public int getBin() {
        return bin;
    }
}