package practice0d.picross.play;

import practice0d.picross.record.PaintType;
import practice0d.picross.record.PixelGrid;
import practice0d.picross.component.Pixel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static practice0d.picross.Constant.*;

public class GridPanel extends JPanel {

    private final Pixel[][] pixels;
    private Point pressPoint = new Point();

    private boolean fill = true;
    private boolean block = true;
    private boolean isPressed = false;

    private boolean isClear = false;

    public GridPanel(PixelGrid pixelGrid) {

        this.setSize(GAME_WIDTH - COUNT_SIZE, GAME_HEIGHT - INFO_HEIGHT - COUNT_SIZE);
        this.setLocation(COUNT_SIZE, COUNT_SIZE + INFO_HEIGHT);
        this.setBackground(Color.yellow);

        this.setLayout(new GridLayout(pixelGrid.width(), pixelGrid.height()));

        pixels = new Pixel[pixelGrid.width()][pixelGrid.height()];
        for (int i = 0; i < pixelGrid.width(); i++) {
            for (int j = 0; j < pixelGrid.height(); j++) {
                pixels[i][j] = new Pixel();
                this.add(pixels[i][j]);
            }
        }


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isClear) return;

                pressPoint = e.getPoint();
                isPressed = true;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.isIn(pressPoint)) {
                                fill = (pixel.getPaintType() == PaintType.EMPTY);
                                return;
                            }
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.isIn(pressPoint)) {
                                block = (pixel.getPaintType() == PaintType.EMPTY);
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isClear) return;
                isPressed = false;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.getPaintType() == PaintType.FILL_TO_EMPTY || pixel.getPaintType() == PaintType.TEMP_EMPTY) {
                                pixel.setPaintType(PaintType.EMPTY);
                            } else if (pixel.getPaintType() == PaintType.EMPTY_TO_FILL || pixel.getPaintType() == PaintType.TEMP_FILL) {
                                pixel.setPaintType(PaintType.FILL);
                            }
                        }
                    }

                    for (int i = 0; i < pixelGrid.width(); i++) {
                        for (int j = 0; j < pixelGrid.height(); j++) {
                            if ((pixels[i][j].getPaintType() == PaintType.FILL) != pixelGrid.grid()[i][j]) return;
                        }
                    }
                    isClear = true;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.getPaintType() == PaintType.EMPTY_TO_BLOCK || pixel.getPaintType() == PaintType.TEMP_BLOCK) {
                                pixel.setPaintType(PaintType.BLOCK);
                            } else if (pixel.getPaintType() == PaintType.BLOCK_TO_EMPTY || pixel.getPaintType() == PaintType.TEMP_EMPTY) {
                                pixel.setPaintType(PaintType.EMPTY);
                            }
                        }
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isClear || !isPressed) return;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.isContain(pressPoint, e.getPoint())) {
                                if (pixel.getPaintType() == PaintType.EMPTY && fill) {
                                    pixel.setPaintType(PaintType.TEMP_FILL);
                                } else if (pixel.getPaintType() == PaintType.FILL && !fill) {
                                    pixel.setPaintType(PaintType.TEMP_EMPTY);
                                }
                            } else {
                                if (pixel.getPaintType() == PaintType.TEMP_EMPTY || pixel.getPaintType() == PaintType.EMPTY_TO_FILL) {
                                    pixel.setPaintType(PaintType.EMPTY);
                                } else if (pixel.getPaintType() == PaintType.TEMP_FILL || pixel.getPaintType() == PaintType.FILL_TO_EMPTY) {
                                    pixel.setPaintType(PaintType.FILL);
                                }
                            }
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    for (Pixel[] pixelList : pixels) {
                        for (Pixel pixel : pixelList) {
                            if (pixel.isContain(pressPoint, e.getPoint())) {
                                if (pixel.getPaintType() == PaintType.EMPTY && block) {
                                    pixel.setPaintType(PaintType.TEMP_BLOCK);
                                } else if (pixel.getPaintType() == PaintType.BLOCK && !block) {
                                    pixel.setPaintType(PaintType.TEMP_EMPTY);
                                }
                            } else {
                                if (pixel.getPaintType() == PaintType.TEMP_BLOCK || pixel.getPaintType() == PaintType.EMPTY_TO_BLOCK) {
                                    pixel.setPaintType(PaintType.EMPTY);
                                } else if (pixel.getPaintType() == PaintType.TEMP_EMPTY || pixel.getPaintType() == PaintType.BLOCK_TO_EMPTY) {
                                    pixel.setPaintType(PaintType.BLOCK);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }
}