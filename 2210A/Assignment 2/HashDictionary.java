import java.util.LinkedList;

/* This class implements the Dictionary ADT using a hash table. */
public class HashDictionary implements DictionaryADT {
    private LinkedList<Data>[] table;   // Array of linked lists to store Data objects
    private int size;                   // Size of the hash table
    private int numRecords;             // Number of records in the hash table

    /* Constructor */
    @SuppressWarnings("unchecked")
    public HashDictionary(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        this.numRecords = 0;
            for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /* Polynomial hash function */
    private int doubleHash(String config) {
        int hash = 0;
        int prime = 31;
        for (int i = 0; i < config.length(); i++) {
            hash = hash * prime + config.charAt(i);
        }
        return Math.abs(hash) % size; // Primary hash value
    }
    
    private int secondaryHashFunction(String config) {
        int hash = 0;
        int prime = 37; // Use a different prime number for the secondary hash
        for (int i = 0; i < config.length(); i++) {
            hash = hash * prime + config.charAt(i);
        }
        return 1 + (Math.abs(hash) % (size - 1)); // Ensure step size is non-zero
    }
    
    // Example of how to use the hash functions
    public int hashFunction(String config) {
        int primaryHash = doubleHash(config);
        int stepSize = secondaryHashFunction(config);
        return (primaryHash * stepSize) % size;
    }
    
    /* Adds record to the dictionary */
    @Override
    public int put(Data pair) throws DictionaryException {
        int index = hashFunction(pair.getConfiguration());  // Get the index of the record
        LinkedList<Data> bucket = table[index];             // Get the linked list at the index
    
        for (int i = 0; i < bucket.size(); i++) {           // Check for duplicate key
            Data data = bucket.get(i);
            if (data.getConfiguration().equals(pair.getConfiguration())) {
                throw new DictionaryException();
            }
        }
    
        boolean isCollision = !bucket.isEmpty();    // Check if there is a collision
        bucket.add(pair);                           // Add the record to the linked list
        numRecords++;                               // Increment the number of records
    
        if (isCollision) {
            return 1;  // Return 1 if there was a collision
        } else {
            return 0;  // Return 0 if there was no collision
        }
    }

    /* Removes record from the dictionary */
    @Override
    public void remove(String config) throws DictionaryException {
        int index = hashFunction(config);                   // Get the index of the record
        LinkedList<Data> bucket = table[index];             // Get the linked list at the index
        
        for (int i = 0; i < bucket.size(); i++) {           // Iterate through the linked list
            Data data = bucket.get(i);
            if (data.getConfiguration().equals(config)) {
                bucket.remove(i);                           // Remove the Data object from the linked list
                numRecords--;                               // Decrement the number of records
                return;
            }
        }
    
        throw new DictionaryException();      // Throw an exception if the record is not found
    }

    /* Returns the score stored in the record of the dictionary */
    @Override
    public int get(String config) {
        int index = hashFunction(config);                   // Get the index of the record
        LinkedList<Data> bucket = table[index];             // Get the linked list at the index
        
        for (int i = 0; i < bucket.size(); i++) {           // Iterate through the linked list
            Data data = bucket.get(i);                          
            if (data.getConfiguration().equals(config)) {
                return data.getScore();                     // Return the score if the record is found
            }
        }
        
        return -1;      // Return -1 if the record is not found
    }

    /* Returns the number of Data objects stored in the dictionary */
    @Override
    public int numRecords() {
        return numRecords;
    }
}