package com.regis.regisdebot.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

public class Console {
    
    private int x = 300, y = 300; //global x and y size of console, 300 square by default
    private String title = "Console"; //title of the console, set to "Console" by default
    private String usrAnswer = "";
    private boolean ifAnswer;
    
    public Console(){
        
    }
    /**
     * Create the console in one line of code. 
     * @param x
     * @param y
     * @param title 
     */
    public Console(int x, int y, String title){
        this(x, y);
        setTitle(title); 
    }
    
    /**
     * Create the console with custom size but default title.
     * @param x
     * @param y 
     */
    public Console(int x, int y){
        setSize(x, y);
    }
    
    /**
     * Create the console with default size but custom title.
     * @param title 
     */
    public Console(String title){
        setTitle(title);
    }
    
    
    /**
     * X - Set the height of the console window. Default 300
     * Y - Set the width of the console window. Default 300
     * @param x 
     * @param y 
     */
    public void setSize(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setSizeX(int x){
        this.x = x;
    }
    
    public void setSizeY(int y){
        this.y = y;
    }
    
    /**
     * Change the title of the console. Default "Console"
     * @param title 
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * Builds the console.
     */
    public void build(){
        JFrame frame = new JFrame(title);
        JLabel title = new JLabel("Console");
        JTextArea console = new JTextArea();
        JTextField usrInput = new JTextField();
        JButton enter = new JButton("Enter");
        
        JScrollPane scroll = new JScrollPane(console);
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        
        
        TextAreaOutputStream taos = TextAreaOutputStream.getInstance(console);
        
        frame.add(outerPanel);
        
        outerPanel.add(topPanel, BorderLayout.PAGE_START);
        outerPanel.add(mainPanel, BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.PAGE_END);
        
        topPanel.add(title, BorderLayout.BEFORE_LINE_BEGINS);
        topPanel.setSize(600, 25);
        
        
        mainPanel.add(scroll, BorderLayout.CENTER);
        scroll.setSize(400, 400);
        
        bottomPanel.add(usrInput, BorderLayout.CENTER);
        bottomPanel.add(enter, BorderLayout.AFTER_LINE_ENDS);
        usrInput.setSize(350, 25);
        frame.getRootPane().setDefaultButton(enter);
        
         
        
        frame.pack();
        frame.setSize(x, y);
        console.setEditable(false);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.show();
        
        DefaultCaret autoscroll = (DefaultCaret)console.getCaret();
        autoscroll.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        enter.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent arg0) {
                
                
                String printUsrInput = usrInput.getText();
                
                usrAnswer = printUsrInput;
                System.out.println(usrAnswer);
                ifAnswer = true;
                
                usrInput.setText("");
            }
        });
    }
    
    /**
     * Returns a string, but only returns it after the user has typed something into the console.
     * @return private String usrAnswer
     * @throws InterruptedException 
     */
    public String answer() throws InterruptedException, URISyntaxException, IOException{
        //scan();
        return usrAnswer;
    }
    
    public void scan() throws InterruptedException, URISyntaxException, IOException  {
		
        usrAnswer = ("");

        ifAnswer = false;

        scanCheck();          
    }

    private void scanCheck() throws InterruptedException, URISyntaxException, IOException {

        if (ifAnswer == true) {  
            Command.commandCheck(usrAnswer);
        }
        else {
            Thread.sleep(200);
            scanCheck();
        }
    }
    /**
     * prints variable text 
     * @param text 
     */
    public void print(String text){
        System.out.print(text);
    }
    /**
     * prints variable text on new line
     * @param text 
     */
    public void println(String text){
        System.out.println(text);
    }
    
    
}
