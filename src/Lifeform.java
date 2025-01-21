import java.util.Random;

/**
 * This abstract class takes care of all the basic methods
 * a life form needs in order for them to interact with each other
 * in the world.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
abstract class Lifeform {
    int[] surroundingCells = new int[8];

    int hunger;
    int maxHunger;
    
    abstract boolean matable(Lifeform checkLife);
    abstract boolean edible(Lifeform checkLife);
    abstract boolean validateReproduction(int mate, int food, int empty);
    
    public void setHunger(int setHunger) {
        hunger = setHunger;
    }

    public void incrementHunger() {
        hunger+=1;
    }
    
    /**
     * Takes care of the move for this lifeform;
     * move meaning this lifeform can either die, reproduce, 
     * eat, migrate, or don't do anything.
     * 
     * @param world the world this lifeform resides in
     * @param y the y coordinate of this lifeform
     * @param x the x coordinate of this lifeform
     */
    public void move(World world, int y, int x) {
        Cell newCell = world.newGrid[y][x];

        // If this lifeform's hunger value reached its maximum value, kill it.
        if (reachedMaxHunger()) {
            die(newCell);
            die(world.grid[y][x]);
            return;
        }

        // If the cell isn't starving, check for valid moves
        int move = validateAll(world, y, x);
        if (move == 1) {
            reproduce(world, y, x);
        } else if (move == 2) {
            eat(world, y, x);
        } else if (move == 3) {
            migrate(world, y, x);
        } else {
            incrementHunger();
            newCell.setLife(this);
            die(world.grid[y][x]);
        }
    }

    public int validateAll(World world, int y, int x) {
        surroundingCells = checkSurrounding(world, y, x);
        int mate = 0;
        int food = 0;
        int empty = 0;
        
        for (int i = 0; i < 8; i++) {
            if (surroundingCells[i] == 0) {
                empty++;
            } else if (surroundingCells[i] == 1) {
                mate++;
            } else if (surroundingCells[i] == 2) {
                food++;
            } 
        }
        
        if (validateReproduction(mate, food, empty)) {
            // it can reproduce;
            return 1;
        } else if (food >= 1) {
            // it can eat;
            return 2;
        } else if (empty >= 1) {
            // it can move
            return 3;
        } else {
            // none of those acts are valid
            return 0;
        }
    }

    /**
     * A method that checks the surroundings of the cell this lifeform resides on.
     * 
     * @param world represents the world this lifeform lives on
     * @return an array that stores an integer value that corresponds to what the surrounding cell is occupied with
     */
    // public int[] checkSurrounding(World world, int y, int x) {
    //     int[] newCoord;
    //     for (int i = 0; i < 8; i++) {
    //         newCoord = world.movePos(y, x, i);
    //         if (world.validatePos(newCoord[0], newCoord[1])) {
    //             if (!world.grid[newCoord[0]][newCoord[1]].isEmpty) {
    //                 Lifeform check = world.grid[newCoord[0]][newCoord[1]].life;
    //                 if (matable(check)) {
    //                     // 1 means the life in that cell is its own kind
    //                     surroundingCells[i] = 1;
    //                 } else if (edible(check)) {
    //                     // 2 means the life in that cell is a food source
    //                     surroundingCells[i] = 2;
    //                 } else {
    //                     // 3 means the life in that cell is occupied with another lifeform
    //                     surroundingCells[i] = 3;
    //                 }
    //             } else {
    //                 if (world.newGrid[newCoord[0]][newCoord[1]].isEmpty) {
    //                     // 0 means the life in that cell is empty
    //                     surroundingCells[i] = 0;
    //                 } else {
    //                     // If grid was empty but newGrid wasn't, that means that spot isn't a valid empty cell
    //                     surroundingCells[i] = -1;
    //                 }
    //             }
    //         } else {
    //             // -1 means that cell does not exist
    //             surroundingCells[i] = -1;
    //         }
    //     }
    //     return surroundingCells;
    // }

    public int[] checkSurrounding(World world, int y, int x) {
        int[] newCoord;
        for (int i = 0; i < 8; i++) {
            newCoord = world.movePos(y, x, i);
            if (world.validatePos(newCoord[0], newCoord[1])) {
                Lifeform check = world.grid[newCoord[0]][newCoord[1]].life;
                if (matable(check)) {
                    // 1 means the life in that cell is its own kind
                    surroundingCells[i] = 1;
                } else if (edible(check)) {
                    // 2 means the life in that cell is a food source
                    surroundingCells[i] = 2;
                } else if (world.newGrid[newCoord[0]][newCoord[1]].isEmpty) {
                    // 0 means the life in that cell is empty
                    surroundingCells[i] = 0;
                } else {
                    // 3 means the life in that cell is occupied with another lifeform
                    surroundingCells[i] = 3;
                }
            } else {
                // -1 means that cell does not exist
                surroundingCells[i] = -1;
            }
        }
        return surroundingCells;
    }
    
    public int[] chooseCell(World world, int act, int y, int x) {
        int moveTo = freeWill();
        while (surroundingCells[moveTo] != act) {
            moveTo = freeWill();
        }
        return world.movePos(y, x, moveTo);
    }

    /**
     * The lifeform randomly decides where they want to move to.
     * @return random number between 0 ~ 7
     */
    public int freeWill() {
        return new Random().nextInt(8);
    }
    
    public void reproduce(World world, int y, int x) {
        int[] newCoord = chooseCell(world, 0, y, x);
        incrementHunger();
        world.newGrid[y][x].setLife(this);
        world.newGrid[newCoord[0]][newCoord[1]].setLife(this);
        world.newGrid[newCoord[0]][newCoord[1]].life.resetHunger();
        die(world.grid[y][x]);
    }
    
    public void eat(World world, int y, int x) {
        int[] newCoord = chooseCell(world, 2, y, x);
        resetHunger();
        world.newGrid[newCoord[0]][newCoord[1]].setLife(this);
        die(world.grid[newCoord[0]][newCoord[1]]);
        die(world.grid[y][x]);
    }
    
    public void migrate(World world, int y, int x) {
        int[] newCoord = chooseCell(world, 0, y, x);
        incrementHunger();
        world.newGrid[newCoord[0]][newCoord[1]].setLife(this);
        die(world.grid[newCoord[0]][newCoord[1]]);
        die(world.grid[y][x]);
    }
    
    /**
     * If it eats, set the hunger value to 0.
     */
    public void resetHunger() {
        hunger = 0;
    }

    /**
     * Checks if this lifeform's hunger values has reached the maximum;
     * if reached, it returns true. If not, it returns.
     * 
     * @return true if the hunger value reached the maximum, false otherwise
     */
    public boolean reachedMaxHunger() {
        if (hunger >= maxHunger) {
            return true;
        } 
        return false;
    }

    /**
     * Gets called when the lifeform dies.
     */
    public void die(Cell cell) {
        cell.setLife(null);
    }
}
