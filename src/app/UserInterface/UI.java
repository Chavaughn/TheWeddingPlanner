package app.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.table.DefaultTableModel;

import app.Management.ClientManagement;
import app.Management.Inventory;
import app.Management.Item;
import app.Management.Reservation;
import app.Management.Venue;
import app.Management.VenueManagement;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class UI {
    private int state;
    private static int type;
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
    private JFrame createVenueDisplay = new JFrame();
    private JFrame createReservationDisplay = new JFrame();
    private JFrame clientDisplay = new JFrame();
    private JFrame createClientDisplay = new JFrame();
    private JFrame createInventoryItemDisplay = new JFrame();
    private JFrame viewListDisplay = new JFrame();
    private JComboBox   dropDownBoxMenu;
    private JComboBox   dropDownBoxVType;
    private JComboBox   dropDownBoxIType;
    private DefaultTableModel model;
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
    
    private VenueManagement venMan = new VenueManagement();
    private ClientManagement clientMan = new ClientManagement();
    private Inventory itemMan = new Inventory();
    private Reservation res = new Reservation();
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
                setType(2);
                break;
            case 8:
                Venues(auth);
                setType(3);
                break;
            case 9:
                Inventory(auth);
                setType(4);
                break;
            case 10:
                createVenue();
                break;
            case 11:
                createReservation();
                break;
            case 12:
                createInventoryItem();
                break;
            case 13:
                ClientInfo(auth);
                setType(1);
                break;
            case 14:
                createClient();
                break;
            case 15:
                ViewList(UI.type);
                break;
            case 16:
                deleteListItem(UI.type);
                break;
            case 17:
                modifyData(UI.type);
                break;
            default:
                break;
        }
    }
    public int setAuth(int i){
        auth = i;
        return auth;
    }
    public void setType(int i){
        UI.type = i;
    }
    public DefaultTableModel setModel(String[] columnNam){
        model=new DefaultTableModel(columnNam,0);
        return model;
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
    //opens staff login
    private class StaffButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            loginDisplay.setVisible(false);
            new UI(5);
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
  /* ------------------------------------ STAFF LOGIN SCREEN -------------------------------------*/
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
          staffDisplay.setVisible(false);
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
              playSound(startSound);
              setAuth(1);
              new UI(4);
              adminDisplay.setVisible(false);
              wrongAdminDisplay.setVisible(false);
          }else{
              playSound(errorSound);
              adminDisplay.setVisible(false);
              wrongAdminDisplay.setVisible(false);
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
              playSound(startSound);
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
      JButton cmdClient;
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

      cmdClient = new JButton("      Clients            ", editicon);
      cmdClient.setPreferredSize(new Dimension(150, 100));

      //Change this icon later
      cmdHistory = new JButton("     History[X]       ", viewpasticon);
      cmdHistory.setPreferredSize(new Dimension(150, 100));
      
      cmdExit = new JButton          ("        Exit                ", exiticon);
      cmdExit.setPreferredSize(new Dimension(100, 80));


      //calls action listener for each button
      cmdReservation.addActionListener(new ReservationButtonListener());
      cmdVenue.addActionListener(new VenueButtonListener());
      cmdInventory.addActionListener(new InventoryButtonListener());
      cmdClient.addActionListener(new ClientButtonListener());
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

      cmdClient.setBackground(new Color(226,228,233));
      cmdClient.setBorderPainted(false);
      cmdClient.setForeground(Color.black);

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

      pnlCommand.add(cmdClient);
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


    private class ClientButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            mainDisplay.setVisible(false);
            new UI(13);
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
        createBtn.addActionListener(new AddReservationButtonListener());
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
            clientDisplay.setVisible(false);
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
        createBtn.addActionListener(new AddVenueButtonListener());
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
            createBtn.addActionListener(new AddInventoryButtonListener());
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
    /* ------------------------------------ CLIENT SCREEN -------------------------------------*/
    private void ClientInfo(int auth){
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
        JLabel Logo = new JLabel("<html><h1>Client Management</h1><html>");
        
        //Create Icons and Images
        Icon addicon = new ImageIcon(addIcon);
        Icon viewicon = new ImageIcon(listIcon);
        Icon modifyicon = new ImageIcon(editIcon);
        Icon deleteicon = new ImageIcon(deleteIcon);
        Icon backicon = new ImageIcon(exitIcon);
        Icon erroricon = new ImageIcon(errorIcon);
        ImageIcon imgLogo = new ImageIcon(LogoImg);

        //Change image of app
        clientDisplay.setIconImage(imgLogo.getImage());

        //Set the layout of the frame
        clientDisplay.setLayout(new GridLayout(2, 1));

        //Set the layout of the panels
        guiCmds.setLayout(new GridBagLayout());
        guiDisplay.setBounds(10,10,10,10);
        guiCmds.setBorder(new EmptyBorder(new Insets(10, 50, 50, 40)));

        //Create Buttons
        if(auth == 1){
        createBtn = new JButton("  Add Client      ", addicon);
        viewBtn = new JButton(" View Clients   ", viewicon);
        modifyBtn = new JButton(" Modify Client  ", modifyicon);
        deleteBtn = new JButton("  Delete Client ", deleteicon);
        backBtn = new JButton("Main Menu", backicon);
        }
        else{
            createBtn = new JButton("  Add Client      ", erroricon);
            viewBtn = new JButton(" View Clients   ", viewicon);
            modifyBtn = new JButton(" Modify Client  ", erroricon);
            deleteBtn = new JButton("  Delete Client ", erroricon);
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
       clientDisplay.add(guiDisplay, BorderLayout.NORTH);
       clientDisplay.add(guiCmds);
       
       //Add Button Listeners
       if(auth == 1){
        createBtn.addActionListener(new AddClientButtonListener());
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
       packFrameLogin(clientDisplay);

    }

    private class UnauthorizedButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(errorSound);
        }
    }
    private class AddClientButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(14);
        }
    }
    private class AddInventoryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(12);
        }
    }
    private class AddReservationButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(11);
        }
    }
    private class AddVenueButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(10);
        }
    }

    private class ViewButtonListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(15);
        }

    }


    private class ModifyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            playSound(buttonPressSound);
            new UI(17);
        }
    }


    private class DeleteButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            new UI(16);
        }
    }
    /* ------------------------------------ CREATE VENUE SCREEN -------------------------------------*/
    public void createVenue()
    {
        JTextField  txtID;   
        JTextField  txtName;
        JTextField  txtLocation;
        JTextField  txtItemsNeeded; 
        //To do the itemsneeded part, I want to show all the names of the items in the spreadsheet in a dropdown
        //Have them select the item name, then show them the quantity available, then enter in a jtextfield how much they want
        //If the amount exceeds the quantity, add a note in the spreadsheet that says "X more [item name] needed for Y Venue"
        //Do this once spreadsheet is done.
        JButton     cmdSelect;
        JButton     cmdClose;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      instructions;
        JLabel      dateinstructions;
        JLabel      title = new JLabel("<html><h>Create New Venue</h><html>");

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Create JDatepicker/Calendar
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(6,2);

        //Create spacing between interfaces
        layout.setVgap(10);
        layout.setHgap(-20);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdSelect     = new JButton("Create", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdSelect.setSize(new Dimension(340, 100));
        cmdSelect.setPreferredSize(new Dimension(76,40));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdSelect.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdSelect);
        pnlCommand.add(cmdClose);

        //Add Venue Name field
        pnlDisplay.add(new JLabel("Enter Venue Name")).setForeground(Color.white);
        txtName = new JTextField(20); 
        //Prevent numbers from being entered
        txtName.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtName.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') { //change this to add/exclude stuff if venue names have numbers in them
                   txtName.setEditable(false);
                } else {
                   txtName.setEditable(true);
                }
             }
            });
        txtName.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtName);

        //Add Parish selection instructions text to panel
        instructions = new JLabel("Please Select A Parish");
        instructions.setForeground(Color.WHITE);
        pnlDisplay.add(instructions); 

        //Create drop down box
        final JComboBox dropDownBox1 =new JComboBox(Venue.PARISHES);
        final JComboBox dropDownBox2 =new JComboBox(Venue.VENUE_TYPES);
        dropDownBoxMenu = dropDownBox1;
        dropDownBoxVType = dropDownBox2;
        pnlDisplay.add(dropDownBox1);
        dropDownBox1.setBounds(50, 100,90,20);  

        //Add Venue Type dropdown menu
        pnlDisplay.add(new JLabel("Select Venue Type")).setForeground(Color.white);
        pnlDisplay.add(dropDownBox2);
        dropDownBox2.setBounds(50, 100,90,20);  

        //Add date instructions text to panel
        dateinstructions = new JLabel("Please Select A Date");
        dateinstructions.setForeground(Color.WHITE);
        pnlDisplay.add(dateinstructions); 

        //Add date picker/calendar
        pnlDisplay.add(datePicker);

        //Add Estimated Price field
        // pnlDisplay.add(new JLabel("Enter Venue Location")).setForeground(Color.white);
        // txtLocation = new JTextField(30); 
        // txtLocation.setHorizontalAlignment(JTextField.CENTER);
        // pnlDisplay.add(txtLocation);

        

        //Give Buttons ActionListeners
        //TODO Add error handling for the if cells empty
        cmdSelect.addActionListener(e -> {venMan.createVenue(txtName.getText(), dropDownBox1.getSelectedItem().toString(), new int[]{datePicker.getModel().getYear(),datePicker.getModel().getMonth(),datePicker.getModel().getDay()},dropDownBox2.getSelectedItem().toString()); createVenueDisplay.dispose();});
        cmdClose.addActionListener(new CloseButtonListener());

        //Add Panels to frame
        createVenueDisplay.add(titlePanel, BorderLayout.NORTH);
        createVenueDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createVenueDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createVenueDisplay);
    }
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }
    
        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
    
            return "";
        }
    
    }

    //Gets the selected data from Drop Down Menu
    public void selected(String[] list){
        String value1 = dropDownBoxMenu.getSelectedItem().toString();
        String value = value1;
        int index = Arrays.asList(list).indexOf(value);
        for (int i=0; i<list.length;i++){
            if (value.equals(String.valueOf(list))){
                index = i;
                break;
            }
        }
        value = list[index]; //Stores the selected data in this variable
        //System.out.println(value);
        //This is the part where you do stuff with the selected data
        //For example
        //AddReservation(value); or something
    }
    private class createVenueButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            selected(Venue.PARISHES);
            createVenueDisplay.setVisible(false);
            
            //Eventually add a JPanel or whatever here that pops up and says "Successfully Edited" or something, idk
        }
    }
    /* ------------------------------------ CREATE RESERVATIONS SCREEN -------------------------------------*/
    public void createReservation()
    {
        JTextField  txtID;   
        JTextField  txtPrice;
        JButton     cmdSelect;
        JButton     cmdClose;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      instructions;
        JLabel      dateinstructions1;
        JLabel      dateinstructions2;
        JLabel      title = new JLabel("<html><h>Create New Reservation</h><html>");

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Create JDatepicker/Calendar
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(4,2);

        //Create spacing between interfaces
        layout.setVgap(10);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdSelect     = new JButton("Create", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdSelect.setSize(new Dimension(340, 100));
        cmdSelect.setPreferredSize(new Dimension(76,40));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdSelect.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdSelect);
        pnlCommand.add(cmdClose);

        //Add date instructions text to panel
        dateinstructions1 = new JLabel("Please Select A Wedding Date");
        dateinstructions1.setForeground(Color.WHITE);
        pnlDisplay.add(dateinstructions1); 

        //Add date picker/calendar
        pnlDisplay.add(datePicker1);

        //Add date instructions text to panel
        dateinstructions2 = new JLabel("Please Select A Reservation Date");
        dateinstructions2.setForeground(Color.WHITE);
        pnlDisplay.add(dateinstructions2); 

        //Add date picker/calendar
        pnlDisplay.add(datePicker2);

        //Add Estimated Price field
        pnlDisplay.add(new JLabel("Enter Estimated Price")).setForeground(Color.white);
        txtPrice = new JTextField(20); 
        //Only Allow Numbers To Be Entered
        txtPrice.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtPrice.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE) {
                    txtPrice.setEditable(true);
                } else {
                    txtPrice.setEditable(false);
                }
             }
            });
        txtPrice.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtPrice);

        //Give Buttons ActionListeners
        cmdSelect.addActionListener(e -> {res.cReservation(new int[]{datePicker1.getModel().getYear(),datePicker1.getModel().getMonth(),datePicker1.getModel().getDay()}, new int[]{datePicker2.getModel().getYear(),datePicker2.getModel().getMonth(),datePicker2.getModel().getDay()}, Double.parseDouble(txtPrice.getText())); createReservationDisplay.dispose();});
        cmdClose.addActionListener(new CloseButtonListener());

        //Add Panels to frame
        createReservationDisplay.add(titlePanel, BorderLayout.NORTH);
        createReservationDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createReservationDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createReservationDisplay);
    }
    /* ------------------------------------ CREATE INVENTORY ITEM SCREEN -------------------------------------*/
    public void createInventoryItem()
    {
        JTextField  txtID;   
        JTextField  txtName;
        JTextField  txtQuantity;
        JButton     cmdSelect;
        JButton     cmdClose;
        JLabel      instructions;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      title = new JLabel("<html><h>Create New Inventory Item</h><html>");

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(3,2);

        //Create spacing between interfaces
        layout.setVgap(10);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdSelect     = new JButton("Create", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdSelect.setSize(new Dimension(340, 100));
        cmdSelect.setPreferredSize(new Dimension(76,40));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdSelect.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdSelect);
        pnlCommand.add(cmdClose);

        //Add Item Name Field
        pnlDisplay.add(new JLabel("Enter Name of Item")).setForeground(Color.white);
        txtName = new JTextField(30); 
        //Prevent numbers from being entered
        txtName.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtName.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                   txtName.setEditable(false);
                } else {
                   txtName.setEditable(true);
                }
             }
            });
        txtName.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtName);

        //Add Quantity Field
        pnlDisplay.add(new JLabel("Enter Quantity of Item")).setForeground(Color.white);
        txtQuantity = new JTextField(5); 

        //Only Allow Numbers To Be Entered
        txtQuantity.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtQuantity.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE) {
                    txtQuantity.setEditable(true);
                } else {
                    txtQuantity.setEditable(false);
                }
             }
            });
        txtQuantity.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtQuantity);

       
        
        //Add Item selection instructions text to panel
        instructions = new JLabel("Please Select Item Type");
        instructions.setForeground(Color.WHITE);
        pnlDisplay.add(instructions);

        //Create drop down box
        final JComboBox dropDownBox =new JComboBox(Item.ITEM_TYPES);
        dropDownBoxVType = dropDownBox;
        pnlDisplay.add(dropDownBox);
        dropDownBox.setBounds(50, 100,90,20);
        
         //Give Buttons ActionListeners
        //cmdSelect.addActionListener(new createVenueButtonListener());
        //cmdSelect.addActionListener(e -> {venMan.createVenue(txtName.getText(), dropDownBox1.getSelectedItem().toString(), new int[]{datePicker.getModel().getYear(),datePicker.getModel().getMonth(),datePicker.getModel().getDay()},dropDownBox2.getSelectedItem().toString()); createVenueDisplay.dispose();});
        cmdSelect.addActionListener(e -> {itemMan.createItem(txtName.getText(), Integer.parseInt(txtQuantity.getText()), dropDownBox.getSelectedItem().toString()); createInventoryItemDisplay.dispose();});
        cmdClose.addActionListener(new CloseButtonListener());


        //Add Panels to frame
        createInventoryItemDisplay.add(titlePanel, BorderLayout.NORTH);
        createInventoryItemDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createInventoryItemDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createInventoryItemDisplay);
    }
    /* ------------------------------------ CREATE CLIENT SCREEN -------------------------------------*/
    public void createClient()
    {
        JTextField  txtID;   
        JTextField  txtName;
        JTextField  txtEmail;
        JTextField  txtPhoneNum;
        JButton     cmdSelect;
        JButton     cmdClose;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      dateinstructions;
        JLabel      warning;
        JLabel      title = new JLabel("<html><h>Add New Client</h><html>");

        //Create JDatepicker/Calendar
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(5,2);

        //Create spacing between interfaces
        layout.setVgap(10);
        layout.setHgap(10);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdSelect     = new JButton("Add", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdSelect.setSize(new Dimension(340, 100));
        cmdSelect.setPreferredSize(new Dimension(76,40));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdSelect.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdSelect);
        pnlCommand.add(cmdClose);

        //Add Item Name Field
        pnlDisplay.add(new JLabel("Enter Name of Client")).setForeground(Color.white);
        txtName = new JTextField(20); 
        //Prevent numbers from being entered
        txtName.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtName.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                   txtName.setEditable(false);
                } else {
                   txtName.setEditable(true);
                }
             }
            });
        txtName.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtName);

        //Add date instructions text to panel
        dateinstructions = new JLabel("Please Enter Client D.O.B");
        dateinstructions.setForeground(Color.WHITE);
        pnlDisplay.add(dateinstructions); 

        //Add date picker/calendar
        pnlDisplay.add(datePicker);

        //Add Email Field
        pnlDisplay.add(new JLabel("Enter Email of Client 'none' if not available")).setForeground(Color.white);
        txtEmail = new JTextField(20); 
        txtEmail.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtEmail);

        //Add Phone Number Field
        pnlDisplay.add(new JLabel("Enter Phone # of Client [Include Area Code]")).setForeground(Color.white);
        txtPhoneNum = new JTextField(10); 

        //Only Allow Numbers To Be Entered
        txtPhoneNum.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke) {
                String value = txtPhoneNum.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE) {
                   txtPhoneNum.setEditable(true);
                } else {
                   txtPhoneNum.setEditable(false);
                }
             }
            });
        //Set text to center and add to panel
        txtPhoneNum.setHorizontalAlignment(JTextField.CENTER);
        pnlDisplay.add(txtPhoneNum);

        //Give Buttons ActionListeners
        cmdSelect.addActionListener(e -> {clientMan.addClient(txtName.getText(), new int[]{datePicker.getModel().getYear(),datePicker.getModel().getMonth(),datePicker.getModel().getDay()},txtEmail.getText() ,txtPhoneNum.getText());createClientDisplay.dispose(); } );
        cmdClose.addActionListener(new CloseButtonListener());

        //Add Panels to frame
        createClientDisplay.add(titlePanel, BorderLayout.NORTH);
        createClientDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createClientDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createClientDisplay);
    }
    /* ------------------------------------ VIEW SCREEN -------------------------------------*/
    public void ViewList(int type) {
        this.type = type;
        JButton     cmdAddPromoter;
        JButton     cmdEditPromoter;
        JButton     cmdClose;
        JButton     cmdSortBudget;
        JButton     cmdSortName;
        JButton     cmdSave;
        JButton     cmdDontSave;
        JPanel      pnlCommand;
        JPanel      pnlDisplay;
        JScrollPane scrollPane;
        JTable      table;
        //DefaultTableModel model;
        ArrayList<String[]> clientList = new ArrayList<String[]>();
        String[]    columnNames = {};

        pnlCommand = new JPanel();
        //pnlDisplay = new JPanel();
        viewListDisplay.setLayout(new GridLayout(2,1));
        pnlCommand.setBackground(new Color(15, 17, 22));
        pnlCommand.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
       
        if(type == 1){//Client view
            String[] columnName =  {"ClientID", "Name", "Date of Birth", "Email", "Phone Numbers"};
            ClientManagement clientMang = new ClientManagement();
            clientList = clientMang.viewAllClients();
            columnNames = columnName;
        }
        else if(type == 2){//Reservation view
            String[] columnName =  {"ReservationID", "Wedding Date", "Reservsation Date", "Approximate Price"};
            Reservation res = new Reservation();
            clientList = res.viewAllReservations();
            columnNames = columnName;
        }
        else if(type == 3){//Venue view
            String[] columnName =  {"VenueID ","Venue Name",  "Date", "Venue Type", "Location"};
            VenueManagement ven = new VenueManagement();
            clientList = ven.viewAllVenues();
            columnNames = columnName;
        }
        else if(type == 4){//Inventory view
            String[] columnName = {"Item Name", "Quantity","Item Type"}; //itemType was not added as a column
            Inventory itm = new Inventory();
            clientList = itm.viewAllItems();
            columnNames = columnName;

        }
        
        model = new DefaultTableModel(columnNames,0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(model);
        showTable(clientList);

        //Set table properties
        setModel(columnNames);
        table.setBackground(new Color(15, 17, 22));
        table.setForeground(Color.white);
        table.getTableHeader().setBackground(new Color(15, 17, 22));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setReorderingAllowed(false);
        table.setShowGrid(false);
    
    
        table.setPreferredScrollableViewportSize(new Dimension(500, clientList.size()*15+50));
        table.setFillsViewportHeight(true);
        
        //Set scroll panel properties
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(15, 17, 22));
        scrollPane.setForeground(Color.white);
        scrollPane.setBorder(null);
        viewListDisplay.add(scrollPane);



       //Create icons for buttons
       Icon exiticon = new ImageIcon("src/res/icons/exiticon.png");


        //Create Buttons
        cmdClose   = new JButton("Close", exiticon);


        //Set Background Colours
        cmdClose.setBackground(new Color(221,55,78));


        //Add ActionListeners to Buttons
        cmdClose.addActionListener(new CloseButtonListener());
        cmdClose.setForeground(Color.white);
        
        //Add Buttons to the Screen
        pnlCommand.add(cmdClose);
       

        viewListDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(viewListDisplay);
    }


    private void showTable(ArrayList<String[]> list)//Put the list of things you want in the table between the () if you wanna use this
    {
        if (list.size()>0){
            for (int i=0; i<list.size();i++){
                addToTable(list.get(i));
            }
        }
            // for (String[] i : list){
            //     System.out.println("Shit the bed");
            //     addToTable(i);
            // }
    }
    private void addToTable(String[] v){//Put variable you want to add in the () if you wanna use this
        //String name= v.getName();
        //String[] item={name,""+ v.getQuantity()}; 
        model.addRow(v);
    }
    /* ------------------------------------ DELETE SCREEN -------------------------------------*/
    public void deleteListItem(int type)
    {
        this.type = type;
        JTextField  txtID;   
        JTextField  txtName;
        JButton     cmdDelete;
        JButton     cmdClose;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      instructions;
        //JLabel      dateinstructions;
        JLabel      title = new JLabel("<html><h>Delete Selected Client</h><html>");
        ArrayList<String[]> theList = new ArrayList<String[]>();
        ArrayList<String[]> list = new ArrayList<String[]>();
        //String  list1[];

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(2,2);

        if(type == 1){//Client 
            ClientManagement clientMang = new ClientManagement();
            list = clientMang.viewAllClients();//Need a get method in client management to return just names
            if (list.size()>0){
                for (int i=0; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }
        else if(type == 2){//Reservation 
            Reservation res = new Reservation();
            list = res.viewAllReservations();
            if (list.size()>0){
                for (int i=0; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }
        else if(type == 3){//Venue
            VenueManagement ven = new VenueManagement();
            list = ven.viewAllVenues();//Need a get method in client management to return just names
            if (list.size()>0){
                for (int i=0; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }
        else if(type == 4){//Inventory 
            Inventory inv = new Inventory();
            list = inv.viewAllItems();
            if (list.size()>0){
                for (int i=1; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }

        //Create spacing between interfaces
        //layout.setVgap(10);
        //layout.setHgap(-20);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdDelete     = new JButton("Delete", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdDelete.setSize(new Dimension(340, 100));
        cmdDelete.setPreferredSize(new Dimension(76,40));
        cmdDelete.setForeground(new Color(221,55,78));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdDelete.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdClose);

        //Add Parish selection instructions text to panel
        instructions = new JLabel("Please Select A Client To Remove");
        instructions.setForeground(Color.WHITE);
        pnlDisplay.add(instructions); 

        String[] newList = new String[theList.size()];
        try{
            theList.toArray(newList);
        }catch(ArrayStoreException e){
        }
        //System.out.println(Arrays.toString(newList));
        String newerList;

        for (int i=0; i<theList.size();i++){
            //System.out.println(Arrays.toString(theList.get(i)));
            newList[i] = Arrays.toString(theList.get(i));
            //System.out.println(Arrays.toString(newList));
        }
        //Create drop down box
        final JComboBox dropDownBox1 = new JComboBox(newList);
        //final JComboBox dropDownBox2 =new JComboBox(Venue.VENUE_TYPES);
        dropDownBoxMenu = dropDownBox1;
        //dropDownBoxVType = dropDownBox2;
        pnlDisplay.add(dropDownBox1);
        dropDownBox1.setBounds(50, 100,90,20);  

        //Give Buttons ActionListeners
        cmdDelete.addActionListener(e -> {switch (type) {
            case 1:
                clientMan.removeClient((int)dropDownBox1.getSelectedItem().toString().charAt(0));
                break;
            case 2:
                break;
            case 3:
                venMan.removeVenue(dropDownBox1.getSelectedItem().toString());
                break;
            case 4:
                break;
            default:
                break;
        }; 
        createVenueDisplay.dispose();});
        cmdClose.addActionListener(new CloseButtonListener());

        //Add Panels to frame
        createVenueDisplay.add(titlePanel, BorderLayout.NORTH);
        createVenueDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createVenueDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createVenueDisplay);
    }
    /* ------------------------------------ MODIFY SCREEN -------------------------------------*/
    public void modifyData(int type)
    {
        this.type = type;
        JTextField  txtID;   
        JTextField  txtName;
        JButton     cmdMod;
        JButton     cmdClose;
        JPanel      pnlCommand = new JPanel();
        JPanel      pnlDisplay = new JPanel();
        JPanel      titlePanel = new JPanel();
        JLabel      instructions;
        //JLabel      dateinstructions;
        JLabel      title = new JLabel("<html><h>Select Data To Modify</h><html>");
        ArrayList<String[]> theList = new ArrayList<String[]>();
        ArrayList<String[]> list = new ArrayList<String[]>();
        //String  list1[];

        //Create Title frame
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        //Align text to center
        title.setHorizontalAlignment(JLabel.RIGHT);
        title.setForeground(Color.white);
        titlePanel.add(title);

        //Set border and background of panels
        pnlDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlDisplay.setBackground(new Color(15, 17, 22));
        pnlCommand.setBackground(new Color(15, 17, 22));
        titlePanel.setBackground(new Color(15, 17, 22));
        txtID = new JTextField(5);

        //This makes the text in the text field alligned to the center, you can just sorta change to whatever direction you want
        txtID.setHorizontalAlignment(JTextField.CENTER);
        GridLayout layout = new GridLayout(2,2);

        if(type == 1){//Client 
            ClientManagement clientMang = new ClientManagement();
            list = clientMang.viewAllClients();//Need a get method in client management to return just names
            if (list.size()>0){
                for (int i=1; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }
        else if(type == 2){//Reservation 
            Reservation res = new Reservation();
            //clientList = res.viewAllReservations();
        }
        else if(type == 3){//Venue
            VenueManagement ven = new VenueManagement();
            list = ven.viewAllVenues();//Need a get method in client management to return just names
            if (list.size()>0){
                for (int i=1; i<list.size();i++){
                    theList.add((list.get(i)));
                }
            }
        }
        else if(type == 4){//Inventory 
        }

        //Create spacing between interfaces
        //layout.setVgap(10);
        //layout.setHgap(-20);

        //Set panel layout
        pnlDisplay.setLayout(layout);

        //Create Icons For Buttons
        Icon selecticon = new ImageIcon("icons/addpromotericon.png");
        Icon closeicon = new ImageIcon("icons/exiticon.png");

        //Create Buttons
        cmdMod     = new JButton("Edit", selecticon);
        cmdClose   = new JButton("Close", closeicon);

        //Set size of  buttons
        cmdMod.setSize(new Dimension(340, 100));
        cmdMod.setPreferredSize(new Dimension(76,40));
        cmdMod.setForeground(new Color(221,55,78));
        cmdClose.setSize(new Dimension(340, 100));
        cmdClose.setPreferredSize(new Dimension(75,40));

        //Set Background colour of Buttons
        cmdMod.setBackground(new Color(226,228,233));
        cmdClose.setBackground(new Color(221,55,78));
        cmdClose.setForeground(Color.white);

        //Add Buttons to Screen
        pnlCommand.add(cmdMod);
        pnlCommand.add(cmdClose);

        //Add Parish selection instructions text to panel
        instructions = new JLabel("Please Select The Data To Edit");
        instructions.setForeground(Color.WHITE);
        pnlDisplay.add(instructions); 

        String[] newList = new String[theList.size()];
        try{
            theList.toArray(newList);
        }catch(ArrayStoreException e){
        }
        //System.out.println(Arrays.toString(newList));
        String newerList;

        for (int i=0; i<theList.size();i++){
            //System.out.println(Arrays.toString(theList.get(i)));
            newList[i] = Arrays.toString(theList.get(i));
            //System.out.println(Arrays.toString(newList));
        }
        //Create drop down box
        final JComboBox dropDownBox1 =new JComboBox(newList);
        //final JComboBox dropDownBox2 =new JComboBox(Venue.VENUE_TYPES);
        dropDownBoxMenu = dropDownBox1;
        //dropDownBoxVType = dropDownBox2;
        pnlDisplay.add(dropDownBox1);
        dropDownBox1.setBounds(50, 100,90,20);  

        //Give Buttons ActionListeners
        //TODO Add error handling for the if cells empty
        cmdMod.addActionListener(new EditButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        //Add Panels to frame
        createVenueDisplay.add(titlePanel, BorderLayout.NORTH);
        createVenueDisplay.add(pnlDisplay, BorderLayout.CENTER);
        createVenueDisplay.add(pnlCommand, BorderLayout.SOUTH);
        packFrameLogin(createVenueDisplay);
    }
    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            createVenueDisplay.setVisible(false);
            viewListDisplay.dispose();
            createReservationDisplay.setVisible(false);
            createClientDisplay.setVisible(false);
            createInventoryItemDisplay.setVisible(false);

        }
    }
    private class EditButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playSound(buttonPressSound);
            createVenueDisplay.setVisible(false);
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