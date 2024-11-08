public class Key {
    private String label;
    private int type;

    // Constructor
    public Key(String theLabel, int theType) {
        label = theLabel.toLowerCase();
        type = theType;
    }
    
    // Returns the String stored in instance variable label
    public String getLabel() {
        return label;
    }

    // Returns the value of instance variable type
    public int getType() {
        return type;
    }

    // Returns 0 if Key object is equal to k, else returns -1 if object is smaller than k, and 1 otherwise.
    public int compareTo(Key k) {
        if (label.equals(k.getLabel()) && type == k.getType()) {
            return 0;
        } else if (label.compareTo(k.getLabel()) < 0 || (label.equals(k.getLabel()) && type < k.getType())) {
            return -1;
        } else {
            return 1;
        }
    }
}