package ru.com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MyWindow extends JFrame
{

    MyWindow()
    {
        setVisible(true);

        setLocation(1000,300); //под мой моник 32дюйма
        setSize(500,500);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //крестик

        setTitle("Впоймай СНЕЖИНКУ"); //имя окна

        JPanel panel = new JPanel(new GridLayout(1, 2)); //разделение кнопок

        JButton btn1 = new JButton("Выход");//кнопка1
        btn1.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        panel.add(btn1);


        JButton btn2 = new JButton("Game"); //кнопка2


        btn2.addActionListener(new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {

                            GameWindow w = new GameWindow ();

                    }

                });
        panel.add(btn2);

        add(panel, BorderLayout.SOUTH); // размещение кнопки
    }
}

