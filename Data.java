/*This class represents the records that will be stored in the HashDictionary.  */
public class Data {
    private String config;  //String configuration of board.
    private int score;      //Algorithm chosen score.

    /*A constructor which initializes a new Data object with the specified configuration and score. */
    public Data(String config, int score){
        this.config = config;
        this.score = score;
    }   
    
    /*Returns the configuration stored in this Data object. */
    public String getConfiguration(){
        return config;
    }

    /*Returns the score in this Data. */
    public int getScore(){
        return score;
    }
}
