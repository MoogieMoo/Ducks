public class Character {

    public String symbol;
    public int health;
    public int atk;
    public int xCor;
    public int yCor;
    //   public String name;

    public int adjustHealth(int amount){
	health -= amount;
	return health;
    }

    public String toString() {
	return symbol;
    }
}
