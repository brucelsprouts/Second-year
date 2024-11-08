// Records to be stored in the internal nodes of the binary search tree.
public class Record {
    private Key theKey;     // Key of the record.
    private String data;    // Data item stored in the record.

    // Constructor which initializes a new Record object.
    public Record(Key k, String theData) {
        this.theKey = k;
        this.data = theData;
    }
    
    // Returns theKey.
    public Key getKey() {
        return theKey;
    }

    // Returns data.
    public String getDataItem() {
        return data;
    }
}
