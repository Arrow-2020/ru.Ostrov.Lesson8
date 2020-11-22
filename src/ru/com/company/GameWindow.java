package ru.com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameWindow extends JFrame {
    Graphics g;

    long last_frame_time;
    Image background;
    Image game_over;
    Image drop;
    float drop_left = 200;
    float drop_top = -100;
    float drop_v = 200;

    GameWindow()  {

        try {


            background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
            game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
            drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));

        }
        catch (Exception e)
        {

        }

     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200, 100);
        setSize(906, 478);
        setResizable(false);
        last_frame_time = System.nanoTime();




        GameField game_Field = new GameField();
        game_Field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + drop.getWidth(null);
                float drop_bottom = drop_top + drop.getHeight(null);
                boolean is_drop = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_bottom;
                if (is_drop) {
                    drop_top = -100;
                    drop_left = (int) (Math.random() * (game_Field.getWidth() - drop.getHeight(null)));
                    drop_v = drop_v + 20;
                }
            }
        });
        add(game_Field);
        setVisible(true);


    }
        class GameField extends JPanel {

             @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                onRepaint(g);
                repaint();

            }
             void onRepaint (Graphics g){
                 long current_time = System.nanoTime();
                 float delta_time = (current_time - last_frame_time) * 0.000000001f;
                 last_frame_time = current_time;

                 drop_top = drop_top + drop_v * delta_time;

                 g.drawImage(background, 0, 0, null);
                 g.drawImage(drop, (int) drop_left, (int) drop_top, null);
                 if (drop_top > getHeight()) g.drawImage(game_over, 260, 100, null);//150
                 setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); //крестик
             }
        }
    }


