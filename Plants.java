public class Plants extends Character{

    public String type;
    public int cost;
    //public int freq;

    public Plants(int xCor1, int yCor1, String type1){
	//freq = freq1;
	xCor = xCor1;
	yCor = yCor1;
	if (type1.equals("P")) {
	    symbol = "P";
	    health = 10;
	    atk = 5;
	    cost = 5;
	}
    
	if (type1.equals("S")) {
	    symbol = "S";
	    health = 6;
	    atk = 4;
	    cost = 3;
	}
    
	if (type1.equals("T")) {
	    symbol = "T";
	    health = 25;
	    atk = 0;
	    cost = 10;
	}
    
	if (type1.equals("B")) {
	    symbol = "B";
	    health = 10;
	    atk = 7;
	    cost = 8;
	}
    
	if (type1.equals("C")) {
	    symbol = "C";
	    health = 8;
	    atk = 8;
	    cost = 7;
	}
    
	if (type1.equals("W")) {
	    symbol = "W";
	    health = 15;
	    atk = 15;
	    cost = 20;
	}
    }
    
    public void shoot () {
    	Bullet b = new Bullet ( atk, xCor + 1, yCor );
    } 

}
