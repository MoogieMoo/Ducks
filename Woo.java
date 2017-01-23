import java.util.Random;

import cs1.Keyboard;


public class Woo {
    
    public static Object[][] board = new Object[9][9];//size of playing grid
    //public static Zombie[][] zomBoard = new Zombie[9][9];
    //public static Plants[][] plaBoard = new Plants[9][9];
    public static String plantsDir = "------------------------------\n\t~Plants Directory~\n------------------------------\nP=\n\tPea Pod (P): 5 suns (10HP, 5ATK)\nS=\n\tShroom (S): 3 suns (6HP, 4 ATK)\nO=\n\tPotato (T): 10 suns (25HP, 0ATK)\nC=\n\tCorn Cobbler (B): 8 suns (10HP, 7ATK)\nT=\n\tCactus (C): 7 suns (8HP, 8ATK)\nW=\n\tWaterm'Cannon (W): 15 suns (15HP, 20ATK)";
    public static int plantCount = 0;
    public static int suns;
    public static boolean keepGoing = false;
    public static int life = 5;

    public Woo(){
	suns = 15;
    }

    //create an empty grid
    public static String ArrayToClass(Object[][] arr){
	String retstr = "";
	for ( int s = 0; s < arr.length ; s++ ) {
	    for ( int x = 0 ; x < arr[s].length ; x++ ) {
		//parses each element one by one
		//space used to make sure that no "null" spots are displayed
		retstr += arr[s][x] + "\t" + " ";
	    }
	    //aesthetic
	    retstr += "\n\n\n\n";
	}
	return retstr;
    }
    
    
    public static void labeling ( Object [][] arr ) {//labels the empty grid
        
	for ( int s = 0; s < arr.length ; s++ ) {//changes grid from being all "null" to space
	    for ( int x = 0 ; x < arr[s].length ; x++ ) {
		arr[s][x] = " ";
	    }
	}
	
	for ( int s = 1; s < arr[0].length ; s++ ) {//numbers along x-axis
	    String a = "" + s;
	    arr[0][s] = a;
	}
	
	for ( int s = 1; s < arr.length ; s++ ) {//numbers along y-axis
	    String a = "" + s;
	    arr[s][0] = a;
	}
    }

    //takes user input for which plant, x and y coordinates, to place a new plant on the grid
    public void chars ( Object [][] arr ) {
	System.out.println(plantsDir);//allows player to see the different plant choices
	System.out.println("Choose a plant:");
	String type = Keyboard.readWord();
	System.out.println("Type in x coordinate:");
	int x = Keyboard.readInt();
	System.out.println("Type in y coordinate:");
	int y = Keyboard.readInt();

	Plants p = new Plants ( x, y, type );//instantiate new plant
	System.out.println( p + "" + p.health );//diagnostic

	arr[y][x] = p.toString();//places plant at coordinates

	suns -= p.cost;//subtract cost of plant from total suns
    }


    public void turn () {

	for ( int s = 0; s < board.length ; s++ ) {
	    for ( int x = 0 ; x < board[s].length ; x++ ) {
		if (board[s][x].getClass().equals("Zombie")) {//makes each Zombie on the board move one unit to the left
		    move((Zombie) board[s][x], -1);
		}
		if (board[s][x].getClass().equals("Plants")) {//makes each plant on the board shoot one bullet
		    shoot((Plants) board[s][x]);
		}
		if (board[s][x].getClass().equals("Bullet")) {//makes each bullet on the board move one unit to the right
		    move((Bullet) board[s][x], 1);
		}
	    }
	}
      
	System.out.println ( "Would you like to add a plant? \ny = yes \nn = no" );
	boolean go;
	String input = Keyboard.readWord();
	if ( input.equals("y") ) {
	    go = true;
	}
	else { go = false; }
	
	while ( go == true ) {
	    chars(board);
	    System.out.println ( ArrayToClass(board) );
	    System.out.println ( "Would you like to add a plant? \ny = yes \nn = no" );
	    input = Keyboard.readWord();
	    if ( input.equals("y") ) {
		go = true;
	    }
	    else { go = false; }
	}
    }

    public void shoot ( Plants p ) {
	Bullet b = new Bullet ( p.atk, p.xCor + 1, p.yCor );
	board[b.xCor][b.yCor] = b;
    }


    public void createZombie(Object[][] arr){
	boolean proceed = false;
	int x = (int)(Math.random() * 3.0) + 6;
	int y = (int)(Math.random() * 8.0) + 1;
	
	if (arr[x][y].equals(" \t")) {
	    proceed = true;
	}
	
	if(proceed == true){
	    int randomNum = (int)(Math.random() * 10.0);
	    Zombie z = new Zombie (randomNum, randomNum, x, y);
	    arr[y][x] = z;
	    proceed = false;
	    
	}
    }


    public static void move( Character c, int direction ){
	board[c.xCor][c.yCor] = " \t";
	board[c.xCor + direction][c.yCor] = c;
    
    }

    public String getClass( Object o ) {
	
	if ( o.toString().equals("Z") ) {
	    return "Zombie";
	}
	
	if ( o.toString().equals("-") ) {
	    return "Bullet";
	}
	
	if ( o.toString().equals("P")||o.toString().equals("s")||o.toString().equals("t")||o.toString().equals("b")||o.toString().equals("c")||o.toString().equals("W")) {
	    return "Plants";
	}

	else {
	    return "error";
	}
    }

    public static void main ( String[] args ) {
	Woo kelly = new Woo();
		
	String r = "==============================";
	System.out.println(r);
	System.out.println("\tWelcome to PvZ!");
	System.out.println(r);

	System.out.println("Suns = " + suns);
	System.out.println("This is your lawn:\n");
	
	kelly.labeling(board);
	System.out.println( kelly.ArrayToClass(board) );

	System.out.println(r);
	
	System.out.println("Now let's begin the game!");
	
	kelly.labeling(board);

	System.out.println(r);
	
	System.out.println("Suns = " + suns);
	System.out.println("Life = " + life);
	System.out.println(r);
	
	//boolean go = false;
	System.out.println("Play a turn?\n(y/n)");
	String input = Keyboard.readWord();
	if(input.equals("y")){
	    keepGoing = true;
	}
	while(keepGoing == true){
	    kelly.turn();
	}
    }
}
