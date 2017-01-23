public class Zombie extends Character
{
    //public String type;
    public int health;
    //public int mobility;
    public int reward;
    
    public Zombie(int health1, int reward1, int xCor1, int yCor1){
	//type = type1;
	health = health1;
	//mobility = mobility1;
	reward = reward1;
	xCor = xCor1;
	yCor = yCor1;
	symbol = "Z";
    }

    public boolean die(){
	if (health <= 0) {
	    return true;
	}
	else {
	    return false;
	}
    }
}
    
