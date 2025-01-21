import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This program makes a new GUI for the Game of Life simulator.
 * 
 * @author Soomin Jeong
 * @version A2b
 */
public class GUI {

    /** The game this GUI will be representing */
    public final Game game;
    
    /**
     * Setting a JFrame object with a grid of JPanels
     */
    public JFrame frame;
    public JPanel panel[][];
    
    /**
     * Instantiates the GUI to show the user how the simulation is going.
     * 
     * @param setGame represents the game this GUI is going to present
     */
    public GUI(Game setGame) {
        game = setGame;

        // Initialize JFrame
        frame = new JFrame();

        // Setting the layout of the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Game of Life");
		frame.setSize(700, 700);
        frame.setLayout(new GridLayout(game.world.getRows(), game.world.getCols(), -1, -1));
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE));
        frame.getContentPane().setBackground(Color.WHITE);

        // Adding mouse click event to frame
        frame.addMouseListener(new MouseListener() {

            /**
             * When clicked, call the click function inside game,
             * which updates the world game has access to,
             * and then update the GUI accordingly.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                game.click();
                update(game.world);
            }

            // Unused overriden methods
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        // Panels (cells) to add inside the frame
        panel = new JPanel[game.world.getRows()][game.world.getCols()];

        // Initialize grid
        for (int y = 0; y < game.world.getRows(); y++) {
            for (int x = 0; x < game.world.getCols(); x++) {
                panel[y][x] = new JPanel();
                frame.add(panel[y][x]);
            }
        }
        
        // Setting each cell to the corresponding values set in world
        fillGrid(game.world);

        // Make frame show up in the middle of the screen
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }

    /**
     * This method updates the GUI according to how the
     * new world looks like after the user clicks and updates the world.
     * 
     * @param newWorld represents the updated world the GUI will newly represent
     */
    public void update(World newWorld) {
        // Update gui according to the new world
        fillGrid(newWorld);
        frame.setTitle("Game of Life - turn " + game.turn);
    }

    /**
     * This method fills the grid with the right color that corresponds
     * to the cells of the world.
     * 
     * @param world represents the world the GUI is trying to represent
     */
    public void fillGrid(World world) {
        // Setting each cell to the corresponding values set in world
        for (int y = 0; y < world.getRows(); y++) {
            for (int x = 0; x < world.getCols(); x++) {
                panel[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                if (world.grid[y][x].life instanceof Herbivore) {
                    panel[y][x].setBackground(Color.YELLOW);
                } else if (world.grid[y][x].life instanceof Plant) {
                    panel[y][x].setBackground(Color.GREEN);
                } else if (world.grid[y][x].life instanceof Carnivore) {
                    panel[y][x].setBackground(Color.RED);
                } else if (world.grid[y][x].life instanceof Omnivore) {
                    panel[y][x].setBackground(Color.BLUE);
                } else {
                    panel[y][x].setBackground(Color.WHITE);
                }
            }
        }
        // Make sure to repaint the frame
        frame.repaint();
    }
}
