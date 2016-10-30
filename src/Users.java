package jis;

import com.sun.corba.se.impl.util.Version;
import java.awt.*;                              
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Users extends JFrame implements ActionListener
{
    public String userName, user1;
    private String passWord, pass1;
    private static ResultSet rs;
    
    Query q = new Query();
       
    public Users()
    {
        initComponents();
    }
     
    private void initComponents()                                               //Method for creating GUI
    {

        jPanel1 = new JPanel();
        UserCategory = new JComboBox();
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        Username = new JLabel();
        Password = new JLabel();
        LogIn = new JButton();
        jLabel1 = new JLabel();
        JIFLabel = new JLabel();
        ErrorLabel = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Judiciary Information System");

        UserCategory.setFont(new java.awt.Font("SansSerif", 1, 18)); 
        UserCategory.setMaximumRowCount(4);
        UserCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Registrar", "Judge", "Lawyer" }));
        UserCategory.setToolTipText("");
        UserCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserCategoryActionPerformed(evt);
            }
        });

        UsernameField.setFont(new java.awt.Font("SansSerif", 0, 18));
        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });

        PasswordField.setFont(new java.awt.Font("SansSerif", 1, 18));
        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });

        Username.setFont(new java.awt.Font("SansSerif", 1, 18));
        Username.setText("Username");

        Password.setFont(new java.awt.Font("SansSerif", 1, 18));
        Password.setText("Password");

        LogIn.setBackground(new java.awt.Color(156, 161, 157));
        LogIn.setFont(new java.awt.Font("SansSerif", 1, 18));
        LogIn.setForeground(new java.awt.Color(102, 0, 102));
        LogIn.setText("Log In");
        LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jis/icon.png"))); 
        jLabel1.setToolTipText("");

        JIFLabel.setFont(new java.awt.Font("SansSerif", 1, 48));
        JIFLabel.setText("Judiciary Information System");

        ErrorLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        ErrorLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorLabel.setText("The username or password you entered is incorrect.");
        ErrorLabel.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(JIFLabel)
                .addGap(261, 261, 261))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ErrorLabel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                        .addComponent(UsernameField)
                        .addComponent(UserCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(JIFLabel)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(UserCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Username))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Password))
                        .addGap(18, 18, 18)
                        .addComponent(ErrorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(LogIn)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                        .addGap(179, 179, 179))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

private void UsernameFieldActionPerformed(ActionEvent evt)
{

}

private void UserCategoryActionPerformed(ActionEvent evt)
{
    
}

private void DoneActionPerformed(ActionEvent evt) 
{
    String str = (String)UserCategory.getSelectedItem();
  
    if(str.equals("Registrar")) 
    {
                            
        try
        {
            userName = UsernameField.getText();                    //Extracting username from text-field
            passWord = PasswordField.getText();                    //Extracting password from text-field
            
            rs = q.execute("select * from loginR where username='"+userName+"' && password='"+passWord+"'");   //Extracting username & password from database
           
            while(rs.next())
            {
                user1 = rs.getString("username");
                pass1 = rs.getString("password");
            }
          
       }
       catch (SQLException ex) 
       {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
       }
   
        
      if(!userName.equals(user1) && !passWord.equals(pass1))                     //Matching username & password from database
      {
         ErrorLabel.setVisible(true);
         UsernameField.setText("");
         PasswordField.setText("");
      }
      else
      {
         this.setVisible(false);
         Registrar registrar = new Registrar();
         registrar.setVisible(true);                                             //Opening Registrar's page
      }
    }
    
    if(str.equals("Judge"))           
    {
        try
        {
             userName = UsernameField.getText();
             passWord = PasswordField.getText();
            
             rs = q.execute("select * from loginJ where username='"+userName+"' && password='"+passWord+"'");
           
             while(rs.next())
             {
                 user1 = rs.getString("username");
                 pass1 = rs.getString("password");
              }
          
         }
         catch (SQLException ex) 
         {
                 Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
         }
   
      if(!userName.equals(user1) && !passWord.equals(pass1))                     //Matching username & password from database
      {
         ErrorLabel.setVisible(true);
         UsernameField.setText("");
         PasswordField.setText("");
      }
      else
      {
        this.setVisible(false);
        Judge judge = new Judge();
        judge.username = userName;
        judge.setVisible(true);                                               //Opening Registrar's page
      }
    }
    
    if(str.equals("Lawyer"))                        
    {
        try
        {
             userName = UsernameField.getText();
             passWord = PasswordField.getText();
            
             rs = q.execute("select * from loginL where username='"+userName+"' && password='"+passWord+"'");
           
             while(rs.next())
             {
                  user1 = rs.getString("username");
                  pass1 = rs.getString("password");
             }
          
         }
         catch (SQLException ex) 
         {
              Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
         }
   
        
      if(!userName.equals(user1) && !passWord.equals(pass1))                      //Matching username & password from database
      {
         ErrorLabel.setVisible(true);
         UsernameField.setText("");
         PasswordField.setText("");
      }
      else
      {
        this.setVisible(false);
        Lawyer lawyer = new Lawyer();
        lawyer.username = userName;
        lawyer.setVisible(true);                                              //Opening Lawyer's page
      }
    }
}

    private void PasswordFieldActionPerformed(ActionEvent evt) 
    {
       
    }

    public static void main(String args[]) 
    { 
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Users().setVisible(true);
            }
        });

        
    }
    // Variables declaration
    private javax.swing.JButton LogIn;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JLabel JIFLabel;
    private javax.swing.JLabel Password;
    private javax.swing.JComboBox UserCategory;
    private javax.swing.JLabel Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTextField UsernameField;

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
