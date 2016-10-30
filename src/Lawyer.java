package jis;

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

public class Lawyer extends JFrame implements ActionListener
{
    private static ResultSet rs;
    public String username;
    private String newPassword;
    private int zero=0;
    private String temp;
    private int value;
    
    Query q = new Query();
    
    public Lawyer()
    {
        initComponents();
    }

    private void initComponents()                                                //Method for creating GUI
    { 
        jPanel1 = new JPanel();
        Welcome = new JLabel();
        User = new JLabel();
        LogOut = new JButton();
        jScrollPane1 = new JScrollPane();
        JISConsole = new JTextArea();
        JISConsoleLabel = new JLabel();
        ResolvedCases = new JButton();
        ViewCharges = new JButton();
        PayCharges = new JButton();
        InfoLabel = new JLabel();
        CINField = new JTextField();
        Done = new JButton();
        ClearConsole = new JButton();
        ChangeAccountSettings = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Judiciary Information System");

        Welcome.setFont(new java.awt.Font("SansSerif", 0, 18));
        Welcome.setText("Welcome");

        User.setFont(new java.awt.Font("SansSerif", 0, 18));

        LogOut.setBackground(new java.awt.Color(156, 161, 157));
        LogOut.setFont(new java.awt.Font("SansSerif", 1, 18));
        LogOut.setForeground(new java.awt.Color(102, 0, 102));
        LogOut.setText("Log Out");
        LogOut.addActionListener(this);

        JISConsole.setColumns(20);
        JISConsole.setFont(new java.awt.Font("SansSerif", 0, 18));
        JISConsole.setRows(5);
        jScrollPane1.setViewportView(JISConsole);

        JISConsoleLabel.setFont(new java.awt.Font("SansSerif", 1, 18));
        JISConsoleLabel.setText("JIS Console");

        ResolvedCases.setBackground(new java.awt.Color(156, 161, 157));
        ResolvedCases.setFont(new java.awt.Font("SansSerif", 1, 18));
        ResolvedCases.setForeground(new java.awt.Color(102, 0, 102));
        ResolvedCases.setText("View Past Cases");
        ResolvedCases.addActionListener(this);

        ViewCharges.setBackground(new java.awt.Color(156, 161, 157));
        ViewCharges.setFont(new java.awt.Font("SansSerif", 1, 18));
        ViewCharges.setForeground(new java.awt.Color(102, 0, 102));
        ViewCharges.setText("View Charges");
        ViewCharges.addActionListener(this);

        PayCharges.setBackground(new java.awt.Color(156, 161, 157));
        PayCharges.setFont(new java.awt.Font("SansSerif", 1, 18));
        PayCharges.setForeground(new java.awt.Color(102, 0, 102));
        PayCharges.setText("Pay Charges");
        PayCharges.addActionListener(this);

        InfoLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        InfoLabel.setText("Enter the CIN/Case Status/Crime Type :");
        InfoLabel.setToolTipText("");
        InfoLabel.setVisible(false);

        CINField.setFont(new java.awt.Font("SansSerif", 0, 14));
        CINField.setVisible(false);

        Done.setBackground(new java.awt.Color(156, 161, 157));
        Done.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        Done.setForeground(new java.awt.Color(102, 0, 102));
        Done.setText("Done");
        Done.addActionListener(this);
        Done.setVisible(false);
        
        ClearConsole.setBounds(30,450,160,30);
        ClearConsole.setBackground(new java.awt.Color(156, 161, 157));
        ClearConsole.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        ClearConsole.setForeground(new java.awt.Color(102, 0, 102));
        ClearConsole.setText("Clear Console");
        jPanel1.add(ClearConsole);
        ClearConsole.addActionListener(this);
        
        ChangeAccountSettings.setBounds(30,580,243,35);
        ChangeAccountSettings.setBackground(new java.awt.Color(156, 161, 157));
        ChangeAccountSettings.setFont(new java.awt.Font("SansSerif", 1, 18)); 
        ChangeAccountSettings.setForeground(new java.awt.Color(102, 0, 102));
        ChangeAccountSettings.setText("Edit Password");
        jPanel1.add(ChangeAccountSettings);
        ChangeAccountSettings.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ResolvedCases, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addComponent(ViewCharges, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addComponent(PayCharges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(InfoLabel)
                            .addComponent(CINField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Done))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(755, Short.MAX_VALUE)
                        .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(User)
                        .addGap(18, 18, 18)
                        .addComponent(LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(635, Short.MAX_VALUE)
                .addComponent(JISConsoleLabel)
                .addGap(278, 278, 278))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogOut)
                    .addComponent(User)
                    .addComponent(Welcome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(JISConsoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ResolvedCases)
                        .addGap(29, 29, 29)
                        .addComponent(ViewCharges)
                        .addGap(29, 29, 29)
                        .addComponent(PayCharges)
                        .addGap(50, 50, 50)
                        .addComponent(InfoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CINField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Done))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    
    public void actionPerformed(ActionEvent click)
    {
        Object source = click.getSource();
        
        if(source == ResolvedCases)
        {
            InfoLabel.setVisible(true);
            CINField.setVisible(true);
            Done.setVisible(true);
        }
        
        if(source == ViewCharges)                                                //Displays the current charges
        {
          try
           {
                
                rs = q.execute("select charge from loginL where username = '"+username+"'");
                
                while(rs.next())
                {
                    JISConsole.append("\nYour account information : Rs. ");
                    JISConsole.append(rs.getString(1));
                }     
            }
          
            catch (SQLException ex) 
            {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }  
          
            catch(NullPointerException ex)
            {
                  
            }    
        }
        
        if(source == PayCharges)
        {
          try
           { 
                rs = q.execute("update loginL set charge = '"+zero+"' where username = '"+username+"'");     //Clears the account charge
                
                while(rs.next())
                {
                  
                }
            }
          
            catch (SQLException ex) 
            {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }  
          
            catch(NullPointerException ex)
            {
                  
            }  
          
           JOptionPane.showMessageDialog(null,"Your account is cleared","Charges",JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(source == ClearConsole)
        {
           JISConsole.setText("");
        }
        
        if(source == Done)
        {
           if(!CINField.getText().equals(""))
           {
             try
             {
                rs = q.execute("select charge from loginL where username = '"+username+"'");
                
                while(rs.next())
                {
                    temp = rs.getString(1);
                }  
                
               value = Integer.parseInt(temp);
              }
          
              catch (SQLException ex) 
              {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
              }  
          
              catch(NullPointerException ex)
              {
                  
              }    
            
              try
              {
                value = value + 100;
                rs = q.execute("update loginL set charge = '"+value+"' where username = '"+username+"'");     //Increases the account charge by Rs. 100
                
                while(rs.next())
                {
                    JISConsole.append("\nYour account information : Rs. ");
                    JISConsole.append(rs.getString(1));
                }     
              }
          
              catch (SQLException ex) 
              {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
              }  
          
              catch(NullPointerException ex)
              {
                  
              }   
              
              try
              {
                rs = q.execute("select * from cases where CIN = '"+CINField.getText()+"' or crimeType = '"+CINField.getText()+"' or caseStatus = '"+CINField.getText()+"'");
                
                while(rs.next())                                                 //Displays the search results
                {
                    if(!JISConsole.getText().equals(""))
                    {
                        JISConsole.append("\n");
                    }
                    JISConsole.append("\nCIN : ");
                    JISConsole.append(rs.getString(1));
                    JISConsole.append("\n");
                    JISConsole.append("Defendant's Name : ");
                    JISConsole.append(rs.getString(2));
                    JISConsole.append("\n");
                    JISConsole.append("Defendant's Address : ");
                    JISConsole.append(rs.getString(3));
                    JISConsole.append("\n");
                    JISConsole.append("Crime Type : ");
                    JISConsole.append(rs.getString(4));
                    JISConsole.append("\n");
                    JISConsole.append("Crime Date : ");
                    JISConsole.append(rs.getString(5));
                    JISConsole.append("\n");
                    JISConsole.append("Crime Location : ");
                    JISConsole.append(rs.getString(6));
                    JISConsole.append("\n");
                    JISConsole.append("Arresting Officer's Name : ");
                    JISConsole.append(rs.getString(7));
                    JISConsole.append("\n");
                    JISConsole.append("Arrest Date : ");
                    JISConsole.append(rs.getString(8));
                    JISConsole.append("\n");
                    JISConsole.append("Judge's Name : ");
                    JISConsole.append(rs.getString(9));
                    JISConsole.append("\n");
                    JISConsole.append("Lawyer's Name : ");
                    JISConsole.append(rs.getString(10));
                    JISConsole.append("\n");
                    JISConsole.append("Public Prosecutor's Name : ");
                    JISConsole.append(rs.getString(11));
                    JISConsole.append("\n");
                    JISConsole.append("Case Starting Date : ");
                    JISConsole.append(rs.getString(12));
                    JISConsole.append("\n");
                    JISConsole.append("Case Judgement Date : ");
                    JISConsole.append(rs.getString(13));
                    JISConsole.append("\n");
                    JISConsole.append("Case Status : ");
                    JISConsole.append(rs.getString(14));
                    JISConsole.append("\n");
                    JISConsole.append("Case Summary : ");
                    JISConsole.append(rs.getString(15));
                    JISConsole.append("\n");
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (NullPointerException ex)
            {
              
            }
          }
             
          else
          {
               JOptionPane.showMessageDialog(null,"Enter a CIN/Case Status/Crime Type","Error",JOptionPane.INFORMATION_MESSAGE);
          }
        }
        
        if(source == ChangeAccountSettings)                                       //Changes the account password
        {
            newPassword = JOptionPane.showInputDialog(null,"Enter new password : ","Password",JOptionPane.INFORMATION_MESSAGE);
            
            if(!newPassword.equals(""))
            {
               try
                {
                     rs = q.execute("update loginL set password = '"+newPassword+"' where username = '"+username+"'");                 
                
                     while(rs.next())
                     {
                  
                     }
                }
                catch (SQLException ex) 
                {
                     Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }  
          
                catch(NullPointerException ex)
                {
                  
                }    
            
               JOptionPane.showMessageDialog(null,"Your password has been edited","Password",JOptionPane.INFORMATION_MESSAGE);
            }
        
            else
            {
                JOptionPane.showMessageDialog(null,"Please enter a valid password","Edit Password",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        if(source == LogOut)                                                       //Logs out to the log-in page
        {
            this.setVisible(false);
            new Users().setVisible(true);
        }
    }

    public static void main(String args[]) {
      
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lawyer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lawyer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lawyer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lawyer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run() 
            {
                new Lawyer().setVisible(true);
            }
        });
    }
    // Variables declaration
    private javax.swing.JButton Done;
    private javax.swing.JTextField CINField;
    private javax.swing.JLabel InfoLabel;
    private javax.swing.JTextArea JISConsole;
    private javax.swing.JLabel JISConsoleLabel;
    private javax.swing.JButton LogOut;
    private javax.swing.JButton PayCharges;
    private javax.swing.JButton ResolvedCases;
    private javax.swing.JLabel User;
    private javax.swing.JButton ViewCharges;
    private javax.swing.JLabel Welcome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private JButton ClearConsole;
    private JButton ChangeAccountSettings;
    // End of variables declaration
}
