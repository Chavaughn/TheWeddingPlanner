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
    public static int auth; //Replace with user authority once User.java is done
    private JFrame mainDisplay = new JFrame();
    private JFrame loginDisplay = new JFrame();
    private JFrame adminDisplay = new JFrame();
    private JFrame wrongAdminDisplay = new JFrame();
    private JFrame staffDisplay = new JFrame();
    private JFrame reservationDisplay = new JFrame();
    private JFrame venueDisplay = new JFrame();
    private JFrame inventoryDisplay = new JFrame();
    private int top = 1, left = 1, bottom = 1, right = 1;
    private Insets i = new Insets(top, left, bottom, right);

    /**Images constants */
    private final String adminIcon = "src/res/icons/adminicon.png";
    private final String staffIcon = "src/res/icons/stafficon.png";
    private final String exitIcon = "src/res/icons/exiticon.png";
    private final String LogoImg = "src/res/start/Logov5.png";
    private final String passIcon = "src/res/icons/unlockpasswordicon.png";
    private final String errorIcon = "src/res/icons/erroricon.png";
    private final String addIcon = "src/res/icons/addicon.png";
    private final String editIcon = "src/res/icons/editicon.png";
    private final String listIcon = "src/res/icons/listicon.png";
    private final String deleteIcon = "src/res/icons/deleteIcon.png";
    private final String viewPastIcon = "src/res/icons/viewpasticon.png";


    /**Souynd Constants */
    private final String startSound = "src/res/sound/start2.wav";
    private final String buttonPressSound = "src/res/sound/button2.wav";
    private final String errorSound = "src/res/sound/error.wav";

    public UI(int state){
        this.state = state;
        switch (state) {
            case 1:    
                playSound(startSound);
                StartLogin();
                break;
            case 2:
                AdminLogin(0);
                break;
            case 3:
                AdminLogin(1);
                break;
            case 4:
                TheMainMenu();
                break;
            case 5:
                StaffLogin(0);
                break;
            case 6:
                StaffLogin(1);
                break;
            case 7:
                Reservations(auth);
                break;
            case 8:
                Venues(auth);
                break;
            case 9:
                Inventory(auth);
                break;
            default:
                break;
        }
    }
    public int setAuth(int i){
        auth = i;
        return auth;
    }
    /* ------------------------------------ ENTRY SCREEN -------------------------------------*/
    private void StartLogin(){
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton adminBtn;
        JButton staffBtn;
        JButton exitBtn;
        JLabel Logo;
        
        Icon adminicon = new ImageIcon(adminIcon);
        Icon stafficon = new ImageIcon(staffIcon);
        Icon exiticon = new ImageIcon(exitIcon);
        ImageIcon imgLogo = new ImageIcon(LogoImg);

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
    //Plays sound
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

    //opens admin login
    private class AdminButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        { 
            playSound(buttonPressSound);
            loginDisplay.setVisible(false);
            new UI(2);
        }
    }

    private class StaffButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
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
            playSound(buttonPressSound);
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
      Icon passicon = new ImageIcon(passIcon);
      Icon closeicon = new ImageIcon(exitIcon);
  
  
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
      adminDisplay.getRootPane().setDefaultButton(cmdPass);
      adminDisplay.setVisible(true);
      }
      /* IF PASSWORD WAS INCORRECT, CHANGE UI */
      else{
      label = new JLabel("<html><i><b></b></i>Password: <html>");
      label.setForeground(Color.WHITE);
      ImageIcon image = new ImageIcon(errorIcon);
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
      Icon passicon = new ImageIcon(passIcon);
      Icon closeicon = new ImageIcon(exitIcon);
  
  
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
      wrongAdminDisplay.getRootPane().setDefaultButton(cmdPass);
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
        Icon passicon = new ImageIcon(passIcon);
        Icon closeicon = new ImageIcon(exitIcon);
    
    
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
        ImageIcon image = new ImageIcon(errorIcon);
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
        Icon passicon = new ImageIcon(passIcon);
        Icon closeicon = new ImageIcon(exitIcon);
    
    
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
        playSound(buttonPressSound);
          adminDisplay.setVisible(false);
          new UI(1);
      }
  }
  private class AdminPasswordButtonListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        playSound(buttonPressSound);

          //Check if password inputted is equal to correct password
          if (String.valueOf(txtPass.getPassword()).equals(password)){
              playSound("res/sound/start2.wav");
              setAuth(1);
              new UI(4);
              adminDisplay.setVisible(false);
          }else{
              playSound(errorSound);
              adminDisplay.setVisible(false);
              new UI(3);
          }
      }
  }
  private class StaffPasswordButtonListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        playSound(buttonPressSound);

          //Check if password inputted is equal to correct password
          if (String.valueOf(txtPass.getPassword()).equals(password)){
              playSound("res/sound/start2.wav");
              setAuth(0);
              new UI(4); //Remember to add restricted authorization
              staffDisplay.setVisible(false);
          }else{
              playSound(errorSound);
              staffDisplay.setVisible(false);
              new UI(6);
          }
      }
  }
  /* ------------------------------------ MAIN MENU SCREEN -------------------------------------*/
        /** 
     * Available Actions in TheMainMenu():
     *   Reservations;
     *      Opens Reservations UI
     * 
     *   Venue;
     *      Opens venue UI
     * 
     *   Inventory;
     *      Opens Inventory UI
     * 
     *   History;
     *       Opens History .xlsx file
     * 
     *   Exit;
     *      Closes the program completely
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
      Icon addicon =      new ImageIcon(addIcon);
      Icon lsticon =      new ImageIcon(listIcon);
      Icon editicon =     new ImageIcon(editIcon);
      Icon delicon =      new ImageIcon(deleteIcon);
      Icon viewpasticon = new ImageIcon(viewPastIcon);
      Icon exiticon =         new ImageIcon(exitIcon);

      
      //Create Buttons
      cmdReservation = new JButton   ("   Reservations    ", editicon);
      cmdReservation.setPreferredSize(new Dimension(150, 100));

      cmdVenue = new JButton  ("      Venues           ", editicon);
      cmdVenue.setPreferredSize(new Dimension(150, 100));

      cmdInventory = new JButton  ("      Inventory        ", editicon);
      cmdInventory.setPreferredSize(new Dimension(150, 100));

      cmdDunno = new JButton("  Clients  ", editicon);
      cmdDunno.setPreferredSize(new Dimension(150, 100));

      //Change this icon later
      cmdHistory = new JButton("     History             ", viewpasticon);
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
      ImageIcon image = new ImageIcon(LogoImg);
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
            playSound(buttonPressSound);
            mainDisplay.setVisible(false); //Debatable whether this should be hidden or not
            new UI(7);   
        }
    }


    private class VenueButtonListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            mainDisplay.setVisible(false); //Debatable whether this should be hidden or not
            new UI(8);
        }

    }


    private class InventoryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            playSound(buttonPressSound);
            mainDisplay.setVisible(false); //Debatable whether this should be hidden or not
            new UI(9);
        }
    }


    private class DunnoButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
        }
    }


    private class HistoryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(10);
        }
    }
    /* ------------------------------------ RESERVATIONS SCREEN -------------------------------------*/
    private void Reservations(int auth){
        //System.out.println(this.auth);
        this.auth = auth;
        //System.out.println(auth);
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton viewBtn;
        JButton modifyBtn;
        JButton deleteBtn;
        JButton createBtn;
        JButton backBtn;
        JLabel Logo = new JLabel("<html><h1>Reservation Management</h1><html>");
        
        //Create Icons and Images
        Icon addicon = new ImageIcon(addIcon);
        Icon viewicon = new ImageIcon(listIcon);
        Icon modifyicon = new ImageIcon(editIcon);
        Icon deleteicon = new ImageIcon(deleteIcon);
        Icon backicon = new ImageIcon(exitIcon);
        Icon erroricon = new ImageIcon(errorIcon);
        ImageIcon imgLogo = new ImageIcon(LogoImg);

        //Change image of app
        reservationDisplay.setIconImage(imgLogo.getImage());

        //Set the layout of the frame
        reservationDisplay.setLayout(new GridLayout(2, 1));

        //Set the layout of the panels
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        //Create Buttons
        if(auth == 1){
        createBtn = new JButton("  Add Reservation      ", addicon);
        viewBtn = new JButton(" View Reservations   ", viewicon);
        modifyBtn = new JButton(" Modify Reservation  ", modifyicon);
        deleteBtn = new JButton("  Delete Reservation ", deleteicon);
        backBtn = new JButton("Main Menu", backicon);
        }
        else{
            createBtn = new JButton("  Add Reservation      ", erroricon);
            viewBtn = new JButton(" View Reservations   ", viewicon);
            modifyBtn = new JButton(" Modify Reservation  ", erroricon);
            deleteBtn = new JButton("  Delete Reservation ", erroricon);
            backBtn = new JButton("Main Menu", backicon);
        }
        
        //Create Logo and Grab Bag Constraints variable
        Logo.setIcon(imgLogo);
        Logo.setForeground(Color.WHITE);
        Logo.setVerticalTextPosition(SwingConstants.BOTTOM);
        Logo.setHorizontalTextPosition(SwingConstants.CENTER);
        gbc = new GridBagConstraints();

        //Set the size of the buttons
        createBtn.setSize(new Dimension(340, 100));
        modifyBtn.setSize(new Dimension(340, 100));
        viewBtn.setSize(new Dimension(340, 100));
        deleteBtn.setSize(new Dimension(340, 100));
        //backBtn.setSize(new Dimension(340, 100)); //Remove if not using Design 4

        //Change background of buttons and panels
        guiDisplay.setBackground(new Color(15, 17, 22));
        guiCmds.setBackground(new Color(15, 17, 22));
        createBtn.setBackground(new  Color(226,228,233));
        viewBtn.setBackground(new  Color(226,228,233));
        modifyBtn.setBackground(new  Color(226,228,233));
        deleteBtn.setBackground(new Color(226,228,233));
        deleteBtn.setForeground(new Color(221,55,78));
        backBtn.setBackground(new Color(221,55,78));
        backBtn.setForeground(Color.white);
        
       //Apply grid bag constraints to buttons
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 0;
       guiCmds.add(createBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 0;

       guiCmds.add(modifyBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 1;

       guiCmds.add(deleteBtn, gbc); 
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 1;
       guiCmds.add(viewBtn, gbc);
       gbc.ipady = 5;  
       gbc.gridx = 0;  
       gbc.gridy = 2;  
       gbc.fill = GridBagConstraints.HORIZONTAL;  //Change back to HORIZONTAL if using DESIGN 1 or 2, CENTER otherwise
       gbc.gridwidth = 2;
       guiCmds.add(backBtn, gbc);
       
       //Add logo to display
       guiDisplay.add(Logo, BorderLayout.NORTH);

       //Add panels to frame
       reservationDisplay.add(guiDisplay, BorderLayout.NORTH);
       reservationDisplay.add(guiCmds);
       
       //Add Button Listeners
       if(auth == 1){
        createBtn.addActionListener(new AddButtonListener());
        modifyBtn.addActionListener(new ModifyButtonListener());
        deleteBtn.addActionListener(new DeleteButtonListener());
        }
        else{
            createBtn.addActionListener(new UnauthorizedButtonListener());
            modifyBtn.addActionListener(new UnauthorizedButtonListener());
            deleteBtn.addActionListener(new UnauthorizedButtonListener());
        }
        viewBtn.addActionListener(new ViewButtonListener());
        backBtn.addActionListener(new BackToMainButtonListener());

       //Apply formatting to frame 
       packFrameLogin(reservationDisplay);

    }
    private class BackToMainButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
          playSound(buttonPressSound);
            reservationDisplay.setVisible(false);
            venueDisplay.setVisible(false);
            inventoryDisplay.setVisible(false);
            new UI(4);
        }
    }
    /* ------------------------------------ VENUES SCREEN -------------------------------------*/
    private void Venues(int auth){
        this.auth = auth;
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton viewBtn;
        JButton modifyBtn;
        JButton deleteBtn;
        JButton createBtn;
        JButton backBtn;
        JLabel Logo = new JLabel("<html><h1>Venue Management</h1><html>");
        
        //Create Icons and Images
        Icon addicon = new ImageIcon(addIcon);
        Icon viewicon = new ImageIcon(listIcon);
        Icon modifyicon = new ImageIcon(editIcon);
        Icon deleteicon = new ImageIcon(deleteIcon);
        Icon backicon = new ImageIcon(exitIcon);
        Icon erroricon = new ImageIcon(errorIcon);
        ImageIcon imgLogo = new ImageIcon(LogoImg);

        //Change image of app
        venueDisplay.setIconImage(imgLogo.getImage());
        
        //Set the layout of the frame
        venueDisplay.setLayout(new GridLayout(2, 1));

        //Set the layout of the panels
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        //Create Buttons
        if(auth == 1){
        createBtn = new JButton("   Add Venue   ", addicon);
        viewBtn = new JButton(" View Venues ", viewicon);
        modifyBtn = new JButton("Modify Venue", modifyicon);
        deleteBtn = new JButton(" Delete Venue", deleteicon);
        backBtn = new JButton("Main Menu", backicon);
        }
        else{
            createBtn = new JButton("   Add Venue   ", erroricon);
            viewBtn = new JButton(" View Venues ", viewicon);
            modifyBtn = new JButton("Modify Venue", erroricon);
            deleteBtn = new JButton(" Delete Venue", erroricon);
            backBtn = new JButton("Main Menu", backicon);
        }
        
        //Create Logo and Grab Bag Constraints variable
        Logo.setIcon(imgLogo);
        Logo.setForeground(Color.WHITE);
        Logo.setVerticalTextPosition(SwingConstants.BOTTOM);
        Logo.setHorizontalTextPosition(SwingConstants.CENTER);
        gbc = new GridBagConstraints();

        //Set the size of the buttons
        createBtn.setSize(new Dimension(340, 100));
        modifyBtn.setSize(new Dimension(340, 100));
        viewBtn.setSize(new Dimension(340, 100));
        deleteBtn.setSize(new Dimension(340, 100));
        //backBtn.setSize(new Dimension(340, 100)); //Remove if not using Design 4

        //Change background of buttons and panels
        guiDisplay.setBackground(new Color(15, 17, 22));
        guiCmds.setBackground(new Color(15, 17, 22));
        createBtn.setBackground(new  Color(226,228,233));
        viewBtn.setBackground(new  Color(226,228,233));
        modifyBtn.setBackground(new  Color(226,228,233));
        deleteBtn.setBackground(new Color(226,228,233));
        deleteBtn.setForeground(new Color(221,55,78));
        backBtn.setBackground(new Color(221,55,78));
        backBtn.setForeground(Color.white);
        
       //Apply grid bag constraints to buttons
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 0;
       guiCmds.add(createBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 0;

       guiCmds.add(modifyBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 1;

       guiCmds.add(deleteBtn, gbc); 
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 1;
       guiCmds.add(viewBtn, gbc);
       gbc.ipady = 5;  
       gbc.gridx = 0;  
       gbc.gridy = 2;  
       gbc.fill = GridBagConstraints.HORIZONTAL;  //Change back to HORIZONTAL if using DESIGN 1 or 2, CENTER otherwise
       gbc.gridwidth = 2;
       guiCmds.add(backBtn, gbc);
       
       //Add logo to display
       guiDisplay.add(Logo, BorderLayout.NORTH);

       //Add panels to frame
       venueDisplay.add(guiDisplay, BorderLayout.NORTH);
       venueDisplay.add(guiCmds);
       
       //Add Button Listeners
       if(auth == 1){
        createBtn.addActionListener(new AddButtonListener());
        modifyBtn.addActionListener(new ModifyButtonListener());
        deleteBtn.addActionListener(new DeleteButtonListener());
        }
        else{
            createBtn.addActionListener(new UnauthorizedButtonListener());
            modifyBtn.addActionListener(new UnauthorizedButtonListener());
            deleteBtn.addActionListener(new UnauthorizedButtonListener());
        }
        viewBtn.addActionListener(new ViewButtonListener());
        backBtn.addActionListener(new BackToMainButtonListener());

       //Apply formatting to frame 
       packFrameLogin(venueDisplay);
    }
    /* ------------------------------------ INVENTORY SCREEN -------------------------------------*/
    private void Inventory(int auth){
        this.auth = auth;
        JPanel guiCmds = new JPanel();
        JPanel guiDisplay = new JPanel();
        GridBagConstraints gbc;
        JButton viewBtn;
        JButton modifyBtn;
        JButton deleteBtn;
        JButton createBtn;
        JButton backBtn;
        JLabel Logo = new JLabel("<html><h1>Inventory Management</h1><html>");
        
        //Create Icons and Images
        Icon addicon = new ImageIcon(addIcon);
        Icon viewicon = new ImageIcon(listIcon);
        Icon modifyicon = new ImageIcon(editIcon);
        Icon deleteicon = new ImageIcon(deleteIcon);
        Icon backicon = new ImageIcon(exitIcon);
        Icon erroricon = new ImageIcon(errorIcon);
        ImageIcon imgLogo = new ImageIcon(LogoImg);

        //Change image of app
        inventoryDisplay.setIconImage(imgLogo.getImage());
        
        //Set the layout of the frame
        inventoryDisplay.setLayout(new GridLayout(2, 1));

        //Set the layout of the panels
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        //Create Buttons
        if(auth == 1){
        createBtn = new JButton("   Add Item      ", addicon);
        viewBtn = new JButton("View Inventory", viewicon);
        modifyBtn = new JButton("   Modify Item   ", modifyicon);
        deleteBtn = new JButton("  Delete Item  ", deleteicon);
        backBtn = new JButton("Main Menu", backicon);
        }
        else{
            createBtn = new JButton("   Add Item      ", erroricon);
            viewBtn = new JButton("View Inventory", viewicon);
            modifyBtn = new JButton("   Modify Item   ", erroricon);
            deleteBtn = new JButton("  Delete Item  ", erroricon);
            backBtn = new JButton("Main Menu", backicon);
        }
        
        //Create Logo and Grab Bag Constraints variable
        Logo.setIcon(imgLogo);
        Logo.setForeground(Color.WHITE);
        Logo.setVerticalTextPosition(SwingConstants.BOTTOM);
        Logo.setHorizontalTextPosition(SwingConstants.CENTER);
        gbc = new GridBagConstraints();

        //Set the size of the buttons
        createBtn.setSize(new Dimension(340, 100));
        modifyBtn.setSize(new Dimension(340, 100));
        viewBtn.setSize(new Dimension(340, 100));
        deleteBtn.setSize(new Dimension(340, 100));
        //backBtn.setSize(new Dimension(340, 100)); //Remove if not using Design 4

        //Change background of buttons and panels
        guiDisplay.setBackground(new Color(15, 17, 22));
        guiCmds.setBackground(new Color(15, 17, 22));
        createBtn.setBackground(new  Color(226,228,233));
        viewBtn.setBackground(new  Color(226,228,233));
        modifyBtn.setBackground(new  Color(226,228,233));
        deleteBtn.setBackground(new Color(226,228,233));
        deleteBtn.setForeground(new Color(221,55,78));
        backBtn.setBackground(new Color(221,55,78));
        backBtn.setForeground(Color.white);
        
       //Apply grid bag constraints to buttons
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 0;
       guiCmds.add(createBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 0;

       guiCmds.add(modifyBtn, gbc);
       gbc.insets = i;
       gbc.gridx = 0;  
       gbc.gridy = 1;

       guiCmds.add(deleteBtn, gbc); 
       gbc.insets = i;
       gbc.gridx = 1;  
       gbc.gridy = 1;
       guiCmds.add(viewBtn, gbc);
       gbc.ipady = 5;  
       gbc.gridx = 0;  
       gbc.gridy = 2;  
       gbc.fill = GridBagConstraints.HORIZONTAL;  //Change back to HORIZONTAL if using DESIGN 1 or 2, CENTER otherwise
       gbc.gridwidth = 2;
       guiCmds.add(backBtn, gbc);
       
       //Add logo to display
       guiDisplay.add(Logo, BorderLayout.NORTH);

       //Add panels to frame
       inventoryDisplay.add(guiDisplay, BorderLayout.NORTH);
       inventoryDisplay.add(guiCmds);
       
        //Add button listeners
        if(auth == 1){
            createBtn.addActionListener(new AddButtonListener());
            modifyBtn.addActionListener(new ModifyButtonListener());
            deleteBtn.addActionListener(new DeleteButtonListener());
            }
            else{
                createBtn.addActionListener(new UnauthorizedButtonListener());
                modifyBtn.addActionListener(new UnauthorizedButtonListener());
                deleteBtn.addActionListener(new UnauthorizedButtonListener());
            }
            viewBtn.addActionListener(new ViewButtonListener());
            backBtn.addActionListener(new BackToMainButtonListener());

       //Apply formatting to frame 
       packFrameLogin(inventoryDisplay);
    }

    private class UnauthorizedButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(errorSound);
        }
    }

    // These need to be finished
    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
        }
    }


    private class ViewButtonListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
        }

    }


    private class ModifyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            playSound(buttonPressSound);
        }
    }


    private class DeleteButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
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