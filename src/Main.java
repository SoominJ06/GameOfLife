/**
 * This program initiates the Game of Life simulator.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Main {

    /**
     * Drives the program.
     * @param args unused
     */
    public static void main(String[] args) {
        Game game = new Game(25, 25); // You can change the dimension size to whatever you want
        new GUI(game); // Display game using GUI as UI
    }
}
