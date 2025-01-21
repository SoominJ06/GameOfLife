/**
 * This program is a world to keep track of all the 
 * life forms for the Game of Life simulator.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class World {
 
    /** Represents the value of the y dimension for this world */
    private final int rows;

    /** Represents the value of the x dimension for this world */
    private final int cols;   

    /** A grid to keep track of each cell in the world */
    public Cell[][] grid;

    /** A new grid to keep track of the updated cells in the world */
    public Cell[][] newGrid;

    /**
     * Instantiates the world with the dimension size taken from
     * the parameter, and instantiates the cells in the world using RandomGenerator.
     * 
     * @param setRows sets the size of y dimension for this world
     * @param setCols sets the size of x dimension for this world
     */
    public World(int setRows, int setCols) {
        rows = setRows;
        cols = setCols;
        grid = new Cell[rows][cols];
        newGrid = new Cell[rows][cols];
        instantiateWorld();
    }

    public void instantiateWorld() {
        // Frill grid with different life forms, leave newGrid empty for now
        int randomNumber;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                randomNumber = RandomGenerator.nextNumber(100);
                if (randomNumber >= 80) {
                    grid[y][x] = new Cell(new Herbivore());
                } else if (randomNumber >= 60) {
                    grid[y][x] = new Cell(new Plant());
                } else if (randomNumber >= 50) {
                    grid[y][x] = new Cell(new Carnivore());
                } else if (randomNumber >= 45) {
                    grid[y][x] = new Cell(new Omnivore());
                } else {
                    grid[y][x] = new Cell(null);
                }
            }
        }
        emptyWorld();
    }

    /**
     * All life forms do their thing (move, eat, reproduce, die, etc)
     */
    public void moveAll() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!grid[y][x].isEmpty) {
                    grid[y][x].life.move(this, y, x);
                }
            }
        }
    }

    /**
     * Validates if the position each life form is trying to 
     * move/reproduce/eat is a valid position.
     * 
     * @param y represents the y coordinate of the cell
     * @param x represents the x coordinate of the cell
     * @return true if position is valid, false otherwise
     */
    public boolean validatePos(int y, int x) {
        if (y < 0 || y >= rows || x < 0 || x >= cols) {
            return false;
        }
        return true;
    }

    /**
     * Takes in the coordinates of the life forms, returns a new
     * coordinates (takes in a y and x coordinate value, returns one of the
     * surrounding eight cells' coordintaes).
     * 
     * @param y represents the y coordinates of the cell
     * @param x represents the x coordinates of the cell
     * @param randNum represents the random number to be used for the switch case
     * @return random new coordinates of the cell
     */
    public int[] movePos(int y, int x, int moveTo) {
        int newX = 0;
        int newY = 0;
        switch (moveTo) {
            case 0: newY = y-1; newX = x-1; break;
            case 1: newY = y-1; newX = x; break;
            case 2: newY = y-1; newX = x+1; break;
            case 3: newY = y; newX = x-1; break;
            case 4: newY = y; newX = x+1; break;
            case 5: newY = y+1; newX = x-1; break;
            case 6: newY = y+1; newX = x; break;
            case 7: newY = y+1; newX = x+1; break;
            default: break;
        }
        return new int[]{newY, newX};
    }
   

    /**
     * This method updates the whole world.
     * It calls the moveAll method to update each cell, then re-sets
     * the grid to the value moveAll gave to the new grid.
     */
    public void updateWorld() {
        moveAll();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (newGrid[y][x].isEmpty) {
                    grid[y][x].setLife(null);
                } else {
                    grid[y][x].setLife(newGrid[y][x].life);
                }
            }
        }
        emptyWorld();
    }

    public void emptyWorld() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                newGrid[y][x] = new Cell(null);
            }
        }
    }

    /**
     * Getter method for the y dimension of the world.
     * 
     * @return y dimension
     */
    public int getRows() {
        return rows;
    }

    /**
     * Getter method for the x dimension of the world.
     * 
     * @return x dimension
     */
    public int getCols() {
        return cols;
    }
}
