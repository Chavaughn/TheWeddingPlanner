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
    private JPasswordField  txtPass;    //entered password
    private String      password;       //correct password
    private JLabel      label;
    private JFrame mainDisplay = new JFrame();
    private JFrame loginDisplay = new JFrame();
    private JFrame adminDisplay = new JFrame();
    private JFrame wrongAdminDisplay = new JFrame();
    private JFrame staffDisplay = new JFrame();
    private int top = 1, left = 1, bottom = 1, right = 1;
    private Insets i = new Insets(top, left, bottom, right);

    public UI(int state){
        this.state = state;
        switch (state) {
            case 1:    
                playSound("res/sound/start2.wav");
                startLogin();
                break;
            case 2:
                AdminLogin(0);
                break;
            case 3:
                AdminLogin(1);
                break;
            case 4:
                //adminDisplay.setVisible(false);
                //staffDisplay.setVisible(false);
                TheMainMenu();
                break;
            case 5:
                StaffLogin(0);
                break;
            case 6:
                StaffLogin(1);
            default:
                break;
        }
    }

    /* ------------------------------------ ENTRY SCREEN -------------------------------------*/
    private void startLogin(){
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton adminBtn;
        JButton staffBtn;
        JButton exitBtn;
        JLabel Logo;
        
        Icon adminicon = new ImageIcon("res/icons/adminicon.png");
        Icon stafficon = new ImageIcon("res/icons/stafficon.png");
        Icon exiticon = new ImageIcon("res/icons/exiticon.png");
        ImageIcon imgLogo = new ImageIcon("res/start/Logov5.png");

        loginDisplay.setTitle("Login");
        loginDisplay.setIconImage(imgLogo.getImage());
        loginDisplay.setLayout(new GridLayout(2, 1));
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        adminBtn = new JButton(" Administrator  ", adminicon);
        staffBtn = new JButton("         Staff        ", stafficon);
        exitBtn = new JButton("Exit", exiticon);
        Logo = new JLabel("", imgLogo, JLabel.NORTH_EAST);
        gbc = new GridBagConstraints();

        staffBtn.setSize(new Dimension(340, 100));
        adminBtn.setSize(new Dimension(340, 100));

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
       
       adminBtn.addActionListener(new AdminButtonListener());
       staffBtn.addActionListener(new StaffButtonListener());
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
        /*frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(720,640);    
        frame.setPreferredSize(new Dimension(720, 640));
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void playSound(String soundName)
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

    public static void main(String[] args) {
        new UI(1);
    }

    //plays audio
    //opens admin login
    private class AdminButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        { 
            playSound("res/sound/button2.wav");
            loginDisplay.setVisible(false);
            new UI(2);
        }
    }

    private class StaffButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
            loginDisplay.setVisible(false);
            new UI(5);
            //setVisible(false);
            //new StaffLogin();
        }
    }

    private class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
            System.exit(0);
        }
    }
  /* ------------------------------------ ADMIN LOGIN SCREEN -------------------------------------*/
    /**
 * Features of AdminLogin:
 *   provides security against un-authorized users   
 */
  public void AdminLogin(int state){
      this.state = state;
      JButton     cmdPass;
      JButton     cmdClose;
      JButton     cmdClearAll;
      JPanel      pnlCommand;
      JPanel      pnlDisplay;
      JLabel      instructions;

      password = "admin";
      pnlCommand = new JPanel();
      pnlDisplay = new JPanel();
      instructions = new JLabel("Please Enter Administrator Password");
      instructions.setForeground(Color.WHITE);
      pnlDisplay.add(instructions); 

      if(state == 0){
      label = new JLabel("<html><br/Password: <html>");
      label.setForeground(Color.WHITE);
      pnlDisplay.add(label);
      pnlDisplay.setBackground(new Color(15,17,22));
      pnlCommand.setBackground(new Color(15,17,22));
  
  
      pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      txtPass = new JPasswordField(10);
      txtPass.setEchoChar('*');
      pnlDisplay.add(txtPass);
      pnlDisplay.setLayout(new GridLayout(4,1));
  
  
      //Create Icons For Buttons
      Icon passicon = new ImageIcon("res/icons/unlockpasswordicon.png");
      Icon closeicon = new ImageIcon("res/icons/exiticon.png");
  
  
      //Create Buttons
      cmdPass    = new JButton("Enter", passicon);
      cmdClose   = new JButton("Back", closeicon);
  
  
      //Set Background colour of Buttons
      cmdPass.setBackground(new Color(226,228,233));
      cmdClose.setBackground(new Color(221,55,78));
  
  
      //Add Buttons to Screen
      pnlCommand.add(cmdPass);
      pnlCommand.add(cmdClose);
  
  
      //Give Buttons ActionListeners
      cmdPass.addActionListener(new AdminPasswordButtonListener());
      cmdClose.addActionListener(new BackButtonListener());
  
      adminDisplay.add(pnlDisplay, BorderLayout.CENTER);
      adminDisplay.add(pnlCommand, BorderLayout.SOUTH);
      adminDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      adminDisplay.setUndecorated(true);
      adminDisplay.pack();
      adminDisplay.setResizable(false);
      adminDisplay.setLocationRelativeTo(null);
      adminDisplay.setVisible(true);
      }
      /* IF PASSWORD WAS INCORRECT, CHANGE UI */
      else{
      label = new JLabel("<html><i><b></b></i>Password: <html>");
      label.setForeground(Color.WHITE);
      ImageIcon image = new ImageIcon("res/icons/erroricon.png");
      JLabel label2 = new JLabel("<html><i><b>INCORRECT PASSWORD</b></i><html>", image, JLabel.NORTH_EAST);
      label2.setForeground(Color.WHITE);
      pnlDisplay.add( label2, BorderLayout.WEST);
      pnlDisplay.add(label);

      pnlDisplay.setBackground(new Color(15,17,22));
      pnlCommand.setBackground(new Color(15,17,22));
  
  
      pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      txtPass = new JPasswordField(10);
      txtPass.setEchoChar('*');
      pnlDisplay.add(txtPass);
      pnlDisplay.setLayout(new GridLayout(4,1));
  
  
      //Create Icons For Buttons
      Icon passicon = new ImageIcon("res/icons/unlockpasswordicon.png");
      Icon closeicon = new ImageIcon("res/icons/exiticon.png");
  
  
      //Create Buttons
      cmdPass    = new JButton("Enter", passicon);
      cmdClose   = new JButton("Back", closeicon);
  
  
      //Set Background colour of Buttons
      cmdPass.setBackground(new Color(226,228,233));
      cmdClose.setBackground(new Color(221,55,78));
  
  
      //Add Buttons to Screen
      pnlCommand.add(cmdPass);
      pnlCommand.add(cmdClose);
  
  
      //Give Buttons ActionListeners
      cmdPass.addActionListener(new AdminPasswordButtonListener());
      cmdClose.addActionListener(new BackButtonListener());
  
      wrongAdminDisplay.add(pnlDisplay, BorderLayout.CENTER);
      wrongAdminDisplay.add(pnlCommand, BorderLayout.SOUTH);
      wrongAdminDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      wrongAdminDisplay.setUndecorated(true);
      wrongAdminDisplay.pack();
      wrongAdminDisplay.setResizable(false);
      wrongAdminDisplay.setLocationRelativeTo(null);
      wrongAdminDisplay.setVisible(true);
      }
  }
  public void StaffLogin(int state){
        this.state = state;
        JButton     cmdPass;
        JButton     cmdClose;
        JButton     cmdClearAll;
        JPanel      pnlCommand;
        JPanel      pnlDisplay;
        JLabel      instructions;
  
        password = "staff";
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        instructions = new JLabel("Please Enter The Staff Password");
        instructions.setForeground(Color.WHITE);
        pnlDisplay.add(instructions); 
  
        if(state == 0){
        label = new JLabel("<html><br/Password: <html>");
        label.setForeground(Color.WHITE);
        pnlDisplay.add(label);
        pnlDisplay.setBackground(new Color(15,17,22));
        pnlCommand.setBackground(new Color(15,17,22));
    
    
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtPass = new JPasswordField(10);
        txtPass.setEchoChar('*');
        pnlDisplay.add(txtPass);
        pnlDisplay.setLayout(new GridLayout(4,1));
    
    
        //Create Icons For Buttons
        Icon passicon = new ImageIcon("res/icons/unlockpasswordicon.png");
        Icon closeicon = new ImageIcon("res/icons/exiticon.png");
    
    
        //Create Buttons
        cmdPass    = new JButton("Enter", passicon);
        cmdClose   = new JButton("Back", closeicon);
    
    
        //Set Background colour of Buttons
        cmdPass.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
    
    
        //Add Buttons to Screen
        pnlCommand.add(cmdPass);
        pnlCommand.add(cmdClose);
    
    
        //Give Buttons ActionListeners
        cmdPass.addActionListener(new StaffPasswordButtonListener());
        cmdClose.addActionListener(new BackButtonListener());
    
        staffDisplay.add(pnlDisplay, BorderLayout.CENTER);
        staffDisplay.add(pnlCommand, BorderLayout.SOUTH);
        staffDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffDisplay.setUndecorated(true);
        staffDisplay.pack();
        staffDisplay.setResizable(false);
        staffDisplay.setLocationRelativeTo(null);
        staffDisplay.setVisible(true);
        }
        /* IF PASSWORD WAS INCORRECT, CHANGE UI */
        else{
        label = new JLabel("<html><i><b></b></i>Password: <html>");
        label.setForeground(Color.WHITE);
        ImageIcon image = new ImageIcon("res/icons/erroricon.png");
        JLabel label2 = new JLabel("<html><i><b>INCORRECT PASSWORD</b></i><html>", image, JLabel.NORTH_EAST);
        label2.setForeground(Color.WHITE);
        pnlDisplay.add( label2, BorderLayout.WEST);
        pnlDisplay.add(label);
  
        pnlDisplay.setBackground(new Color(15,17,22));
        pnlCommand.setBackground(new Color(15,17,22));
    
    
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtPass = new JPasswordField(10);
        txtPass.setEchoChar('*');
        pnlDisplay.add(txtPass);
        pnlDisplay.setLayout(new GridLayout(4,1));
    
    
        //Create Icons For Buttons
        Icon passicon = new ImageIcon("res/icons/unlockpasswordicon.png");
        Icon closeicon = new ImageIcon("res/icons/exiticon.png");
    
    
        //Create Buttons
        cmdPass    = new JButton("Enter", passicon);
        cmdClose   = new JButton("Back", closeicon);
    
    
        //Set Background colour of Buttons
        cmdPass.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
    
    
        //Add Buttons to Screen
        pnlCommand.add(cmdPass);
        pnlCommand.add(cmdClose);
    
    
        //Give Buttons ActionListeners
        cmdPass.addActionListener(new StaffPasswordButtonListener());
        cmdClose.addActionListener(new BackButtonListener());
    
        staffDisplay.add(pnlDisplay, BorderLayout.CENTER);
        staffDisplay.add(pnlCommand, BorderLayout.SOUTH);
        staffDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffDisplay.setUndecorated(true);
        staffDisplay.pack();
        staffDisplay.setResizable(false);
        staffDisplay.setLocationRelativeTo(null);
        staffDisplay.setVisible(true);
        }
    }


  private class BackButtonListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        playSound("res/sound/button2.wav");
          adminDisplay.setVisible(false);
          new UI(1);
      }
  }
  private class AdminPasswordButtonListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        playSound("res/sound/button2.wav");

          //Check if password inputted is equal to correct password
          if (String.valueOf(txtPass.getPassword()).equals(password)){
              playSound("res/sound/start2.wav");
              new UI(4);
              adminDisplay.setVisible(false);
          }else{
              playSound("res/sound/error.wav");
              adminDisplay.setVisible(false);
              new UI(3);
          }
      }
  }
  private class StaffPasswordButtonListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        playSound("res/sound/button2.wav");

          //Check if password inputted is equal to correct password
          if (String.valueOf(txtPass.getPassword()).equals(password)){
              playSound("res/sound/start2.wav");
              new UI(4); //Remember to add restricted authorization
              staffDisplay.setVisible(false);
          }else{
              playSound("res/sound/error.wav");
              staffDisplay.setVisible(false);
              new UI(6);
          }
      }
  }
  /* ------------------------------------ MAIN MENU SCREEN -------------------------------------*/
        /** 
     * Available Actions in TheMainMenu():
     *   Add a promoter;
     *      runs PromoterEntry
     *      [expected addition to the list of promoters]
     * 
     *   List the saved promoters;
     *      runs PromoterListing
     *      [expected pop up window showing all saved promoters]
     * 
     *   Edit a saved promoter;
     *      runs 
     *      [expected update to the list of promoters]
     *   Delete a saved promoter;
     *      [expected removal from the list of promoters]
     *   View recently deleted promoters;
     *      [expected pop up window showing most recently deleted promoter]
     *   Exit the program
     *      [closes the program completely]
    */
    public void TheMainMenu(){
      JButton cmdReservation;
      JButton cmdVenue;
      JButton cmdInventory;
      JButton cmdDunno;
      JButton cmdHistory;
      JButton cmdExit;
      JPanel pnlCommand;
      JPanel pnlDisplay;
      mainDisplay.setLayout(new GridLayout(1,2));

      pnlCommand = new JPanel();
      pnlCommand.setPreferredSize(new Dimension(1920,1080));
      BoxLayout boxlayout = new BoxLayout(pnlCommand, BoxLayout.Y_AXIS);
      pnlCommand.setBackground(new Color(15,17,22));
      pnlDisplay = new JPanel();
      pnlDisplay.setBackground(new Color(15,17,22));


      pnlCommand.setLayout(boxlayout);
      pnlCommand.setBorder(new EmptyBorder(new Insets(100, 100, 50, 50)));  //Move around buttons  

     
      //Create icons for buttons
      Icon addpromicon =      new ImageIcon("res/icons/addpromotericon.png");
      Icon lstpromicon =      new ImageIcon("res/icons/listpromotericon.png");
      Icon editpromicon =     new ImageIcon("res/icons/editpromotericon.png");
      Icon delpromicon =      new ImageIcon("res/icons/deletepromotericon.png");
      Icon viewpastpromicon = new ImageIcon("res/icons/viewpastpromotersicon.png");
      Icon exiticon =         new ImageIcon("res/icons/exiticon.png");

      
      //Create Buttons
      cmdReservation = new JButton   ("   Reservations    ", editpromicon);
      cmdReservation.setPreferredSize(new Dimension(150, 100));

      cmdVenue = new JButton  ("      Venues           ", editpromicon);
      cmdVenue.setPreferredSize(new Dimension(150, 100));

      cmdInventory = new JButton  ("      Inventory        ", editpromicon);
      cmdInventory.setPreferredSize(new Dimension(150, 100));

      cmdDunno = new JButton("  Dont Know Yet  ", editpromicon);
      cmdDunno.setPreferredSize(new Dimension(150, 100));

      //Change this icon later
      cmdHistory = new JButton("     History             ", viewpastpromicon);
      cmdHistory.setPreferredSize(new Dimension(150, 100));
      
      cmdExit = new JButton          ("        Exit                ", exiticon);
      cmdExit.setPreferredSize(new Dimension(100, 80));


      //calls action listener for each button
      cmdReservation.addActionListener(new ReservationButtonListener());
      cmdVenue.addActionListener(new VenueButtonListener());
      cmdInventory.addActionListener(new InventoryButtonListener());
      cmdDunno.addActionListener(new DunnoButtonListener());
      cmdHistory.addActionListener(new HistoryButtonListener());
      cmdExit.addActionListener(new ExitButtonListener());
     

      //sets button colours
      cmdReservation.setBackground(new  Color(226,228,233));
      cmdReservation.setBorderPainted(false);
      cmdReservation.setForeground(Color.black);

      cmdVenue.setBackground(new Color(226,228,233));
      cmdVenue.setBorderPainted(false);
      cmdVenue.setForeground(Color.black);

      cmdInventory.setBackground(new Color(226,228,233));
      cmdInventory.setBorderPainted(false);
      cmdInventory.setForeground(Color.black);

      cmdDunno.setBackground(new Color(226,228,233));
      cmdDunno.setBorderPainted(false);
      cmdDunno.setForeground(Color.black);

      cmdHistory.setBackground(new Color(226,228,233));
      cmdHistory.setBorderPainted(false);
      cmdHistory.setForeground(Color.black);

      cmdExit.setBackground(new Color(221,55,78));
      cmdExit.setBorderPainted(false);
      cmdExit.setForeground(Color.white);
      

      //adds buttons and separates each of them 
      pnlCommand.add(cmdReservation);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      pnlCommand.add(cmdVenue);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      pnlCommand.add(cmdInventory);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      pnlCommand.add(cmdDunno);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      pnlCommand.add(cmdHistory);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      pnlCommand.add(cmdExit);
      pnlCommand.add(Box.createRigidArea(new Dimension(0, 40)));  

      
      //adds image to panel
      pnlDisplay.add(Box.createRigidArea(new Dimension(-60,600)));  
      ImageIcon image = new ImageIcon("res/start/Logov5.png");
      JLabel label = new JLabel("", image, JLabel.CENTER);
      pnlDisplay.add(label);
      
      mainDisplay.add(pnlCommand);
      mainDisplay.add(pnlDisplay, BorderLayout.CENTER);  
      packFrame(mainDisplay);
  }
        // These need to be changed
    private class ReservationButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
            new UI(6);   
        }
    }


    private class VenueButtonListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
            new UI(7);
        }

    }


    private class InventoryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            playSound("res/sound/button2.wav");
            new UI(8);
        }
    }


    private class DunnoButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
        }
    }


    private class HistoryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound("res/sound/button2.wav");
            new UI(9);
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