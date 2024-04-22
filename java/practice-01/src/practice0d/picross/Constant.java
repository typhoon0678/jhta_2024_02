package practice0d.picross;

import practice0d.picross.record.PixelGrid;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constant {

    // Size
    public static int INFO_HEIGHT = 100;
    public static int GAME_HEIGHT = 600 + INFO_HEIGHT;
    public static int GAME_WIDTH = 600;

    public static int COUNT_SIZE = 150;
    public static int COUNT_TEXT_SIZE = 20;

    public static int BUTTON_WIDTH = 100;
    public static int BUTTON_HEIGHT = 60;

    // Color
    public static Color PRIMARY_COLOR = Color.blue;
    public static Color BACKGROUND_COLOR = Color.lightGray;
    public static Color BUTTON_COLOR = Color.blue;

    // Font
    public static Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 24);
    public static Font MIDDLE_FONT = new Font("맑은 고딕", Font.PLAIN, 16);


    // Data
    public static ArrayList<PixelGrid> GRID_DATA = new ArrayList<>(
            Arrays.asList(
                    new PixelGrid(new boolean[][]{
                            {false, true, false},
                            {true, true, true},
                            {false, true, false}},
                            3, 3, 0),
                    new PixelGrid(new boolean[][]{
                            {false, true, false, true, false},
                            {false, true, false, true, false},
                            {false, false, false, false, false},
                            {true, false, false, false, true},
                            {false, true, true, true, false}
                    }, 5, 5, 1),
                    new PixelGrid(new boolean[][]{
                            {false, false, false, false, true, true, true, true, true, true},
                            {false, false, false, false, false, false, false, true, false, false},
                            {false, false, false, false, false, false, true, true, false, false},
                            {false, false, false, false, true, true, false, true, false, false},
                            {false, true, true, true, false, false, false, true, false, false},
                            {true, false, true, true, true, false, true, true, true, false},
                            {true, true, true, true, true, true, false, true, true, true},
                            {true, true, true, true, true, true, true, true, true, true},
                            {false, true, true, true, false, true, true, true, true, true},
                            {false, false, false, false, false, false, true, true, true, false}
                    },
                            10, 10, 2),
                    new PixelGrid(new boolean[][]{
                            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
                            {true, false, true, true, true, true, false, false, true, true, true, false, true, true, true},
                            {true, true, true, true, true, false, false, false, false, true, true, true, true, true, true},
                            {true, true, true, true, true, false, false, false, false, true, true, true, true, true, true},
                            {true, true, true, true, false, true, false, false, true, false, true, false, true, true, true},
                            {true, true, false, true, false, false, false, false, false, false, true, true, true, true, true},
                            {true, true, true, true, false, true, false, false, true, false, true, true, true, true, true},
                            {true, true, true, true, false, false, false, false, false, false, true, true, true, false, true},
                            {true, true, true, true, false, true, false, false, true, false, true, true, true, true, true},
                            {true, true, true, true, false, false, false, false, false, false, true, true, true, true, true},
                            {false, true, true, true, false, true, false, false, true, false, true, true, true, true, true},
                            {true, true, true, false, false, false, false, false, false, false, false, true, true, true, false},
                            {true, true, false, false, false, false, false, false, false, false, false, false, true, true, true},
                            {true, false, false, false, true, false, true, true, false, true, false, false, false, true, true},
                            {true, true, true, true, false, true, true, true, true, false, true, true, true, true, true},
                    },
                            15, 15, 3),
                    new PixelGrid(new boolean[][]{
                            {false, false, false, true, true, true, true, true, true, true, false, false, false, false, false},
                            {false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
                            {false, true, true, true, true, false, false, false, false, false, true, true, false, false, false},
                            {true, true, true, true, false, false, false, false, false, false, false, true, false, false, false},
                            {true, true, true, false, false, true, true, false, false, false, true, true, false, false, false},
                            {true, true, true, false, true, true, false, true, false, true, true, false, true, false, false},
                            {true, true, true, false, true, true, true, true, false, true, true, true, true, false, false},
                            {true, true, true, false, false, true, true, false, true, false, true, true, false, false, false},
                            {true, true, true, false, false, false, false, false, false, false, false, false, true, true, true},
                            {true, true, true, false, false, false, false, true, false, false, false, false, false, false, true},
                            {true, true, true, false, false, false, false, false, true, true, true, true, true, true, false},
                            {true, true, true, true, false, false, false, false, false, false, false, false, true, false, false},
                            {true, true, true, true, true, false, false, false, false, false, false, false, true, true, false},
                            {false, true, true, true, true, true, true, false, false, false, false, false, true, true, true},
                            {true, false, true, true, true, true, true, true, true, false, false, false, true, true, true},
                    },
                            15, 15, 4),
                    new PixelGrid(new boolean[][]{
                            {true},
                    },
                            1, 1, 5),
                    new PixelGrid(new boolean[][]{
                            {true},
                    },
                            1, 1, 6),
                    new PixelGrid(new boolean[][]{
                            {true},
                    },
                            1, 1, 7),
                    new PixelGrid(new boolean[][]{
                            {true},
                    },
                            1, 1, 8)
            )
    );

}

