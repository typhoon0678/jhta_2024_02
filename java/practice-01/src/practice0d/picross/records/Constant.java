package practice0d.picross.records;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Constant {

    // Size
    public static final int INFO_HEIGHT = 100;
    public static final int GAME_HEIGHT = 600 + INFO_HEIGHT;
    public static final int GAME_WIDTH = 600;

    public static final int COUNT_SIZE = 150;
    public static final int COUNT_TEXT_SIZE = 20;

    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 60;

    // Color
    public static final Color PRIMARY_COLOR = Color.blue;
    public static final Color BACKGROUND_COLOR = Color.lightGray;

    // Font
    public static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 24);
    public static final Font MIDDLE_FONT = new Font("맑은 고딕", Font.PLAIN, 16);


    // Data
    public static final ArrayList<PixelGrid> GRID_DATA = new ArrayList<>(
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

