/**
 * This program takes care of the turns and updates the world.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class Game {
    
    int turn = 0;
    World world;

    /**
     * Instantiates the world using the values of the dimensions provided in the parameter.
     * 
     * @param y represents the size of the y dimension of the world
     * @param x represents the size of the x dimension of the world
     */
    public Game(int y, int x) {
        world = new World(y,x);
    }

    /**
     * When user clicks, update the world and increment the turn.
     */
    public void click() {
        world.updateWorld();
        turn++;
    }
}
