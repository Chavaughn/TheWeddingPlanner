package app.vis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class UI {
    private int state;
    private JFrame mainDisplay = new JFrame();
    private JFrame loginDisplay = new JFrame();
    private int top = 1, left = 1, bottom = 1, right = 1;
    private Insets i = new Insets(top, left, bottom, right);

    public UI(int state){
        this.state = state;
        switch (state) {
            case 1:    
                startLogin();
                break;
            default:
                break;
        }
    }

    private void startLogin(){
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton adminBtn;
        JButton staffBtn;
        JButton exitBtn;
        JLabel Logo;
        
        Icon adminicon = new ImageIcon("src/res/icons/adminicon.png");
        Icon stafficon = new ImageIcon("src/res/icons/stafficon.png");
        Icon exiticon = new ImageIcon("src/res/icons/exiticon.png");
        ImageIcon imgLogo = new ImageIcon("src/res/start/Logov5.png");

        loginDisplay.setTitle("Login");
        loginDisplay.setIconImage(imgLogo.getImage());
        loginDisplay.setLayout(new GridLayout(2, 1));
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        adminBtn = new JButton("Administrator", adminicon);
        staffBtn = new JButton("   Staff                    ", stafficon);
        exitBtn = new JButton("Exit", exiticon);
        Logo = new JLabel("", imgLogo, JLabel.NORTH_EAST);
        gbc = new GridBagConstraints();

        guiDisplay.setBackground(new Color(15, 17, 22));
        guiCmds.setBackground(new Color(15, 17, 22));
        adminBtn.setBackground(new  Color(226,228,233));
        staffBtn.setBackground(new  Color(226,228,233));
        exitBtn.setBackground(new Color(221,55,78));
        exitBtn.setForeground(Color.white);
        
       guiCmds.add(adminBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 0;
       guiCmds.add(staffBtn, gbc);
       gbc.insets = i;
       gbc.weightx=0.5;
       gbc.weighty=0.5;
       gbc.gridx = 0;  
       gbc.gridy = 2;  
       gbc.fill = GridBagConstraints.HORIZONTAL;  
       gbc.gridwidth = 2;   
       guiCmds.add(exitBtn, gbc); 
       guiDisplay.add(Logo, BorderLayout.NORTH);

       loginDisplay.add(guiDisplay, BorderLayout.NORTH);
       loginDisplay.add(guiCmds);
       
       exitBtn.addActionListener(new ExitButtonListener());   
       packFrameLogin(loginDisplay);

    }

    private void packFrameLogin(JFrame frame){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void packFrame(JFrame frame){
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UI(1);
    }

    

    private class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}

class MyDefaultMetalTheme extends DefaultMetalTheme {
    public ColorUIResource getWindowTitleInactiveBackground() {
      return new ColorUIResource(java.awt.Color.DARK_GRAY);
    }
    public ColorUIResource getWindowTitleBackground() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getPrimaryControlHighlight() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getPrimaryControlDarkShadow() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getPrimaryControl() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getControlHighlight() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getControlDarkShadow() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
    
      public ColorUIResource getControl() {
        return new ColorUIResource(java.awt.Color.DARK_GRAY);
      }
}