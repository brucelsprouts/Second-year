// Implements the BSTDictionaryADT interface
public class BSTDictionary implements BSTDictionaryADT {
    private BinarySearchTree bst;

    // Constructor
    public BSTDictionary() {
        bst = new BinarySearchTree();
    }

    /* Returns the Record object with the Key as k, or it returns null if such 
        a Record is not in the dictionary. */
    @Override
    public Record get(Key k) {
        BSTNode node = bst.get(bst.getRoot(), k);
        return (node != null) ? node.getRecord() : null;
    }

    /* Inserts the Record d into the ordered dictionary. It throws a DictionaryException 
        if a Record with the same Key attribute as d is already in the dictionary. */
    @Override
    public void put(Record d) throws DictionaryException {
        try {
            bst.insert(bst.getRoot(), d);
        } catch (DictionaryException e) {
            throw new DictionaryException();
        }
    }

    /* Removes the Record with the same Key attribute as k from the dictionary. It throws a 
        DictionaryException if such a Record is not in the dictionary. */
    @Override
    public void remove(Key k) throws DictionaryException {
        try {
            bst.remove(bst.getRoot(), k);
        } catch (DictionaryException e) {
            throw new DictionaryException();
        }
    }

    /* Returns the successor of k (the Record from the ordered dictionary 
        with smallest key larger than k); it returns null if the given key has
        no successor. Note that the given key k DOES NOT need to be in the dictionary. */
    @Override
    public Record successor(Key k) {
        BSTNode node = bst.successor(bst.getRoot(), k);
        return (node != null) ? node.getRecord() : null;
    }

    /* Returns the predecessor of k (the Record from the ordered dictionary 
        with largest key smaller than k; it returns null if the given key has 
        no predecessor. Note that the given key k DOES NOT need to be in the dictionary.  */
    @Override
    public Record predecessor(Key k) {
        BSTNode node = bst.predecessor(bst.getRoot(), k);
        return (node != null) ? node.getRecord() : null;
    }

    /* Returns the Record with smallest key in the ordered dictionary. 
        Returns null if the dictionary is empty.  */
    @Override
    public Record smallest() {
        BSTNode node = bst.smallest(bst.getRoot());
        return (node != null) ? node.getRecord() : null;
    }

    /* Returns the Record with largest key in the ordered dictionary. 
        Returns null if the dictionary is empty.  */
    @Override
    public Record largest() {
        BSTNode node = bst.largest(bst.getRoot());
        return (node != null) ? node.getRecord() : null;
    }
}