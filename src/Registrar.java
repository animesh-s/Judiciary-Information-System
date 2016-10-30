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

public class Registrar extends JFrame implements ActionListener
{
    private String user;
    private String pass;
    private String temp;
    private String date;
    private int count;
    private int value = 0;
    private static ResultSet rs;
    Query q = new Query();
    Cases c = new Cases();
    
    public Registrar() 
    {
        initComponents();
    }

    private void initComponents()                                               //Method for creating GUI
    {
        jPanel1 = new JPanel();
        Welcome = new JLabel();
        UserLabel = new JLabel();
        LogOut = new JButton();
        RegisterNewCase = new JButton();
        DisplayDates = new JButton();
        ViewCaseStatus = new JButton();
        CaseDetails = new JComboBox();
        EditAccounts = new JComboBox();
        jScrollPane1 = new JScrollPane();
        JISConsole = new JTextArea();
        JISConsoleLabel = new JLabel();
        UpdateCaseSummary = new JButton();
        InfoLabel = new JLabel();
        CINField = new JTextField();
        Update = new JButton();
        Edit = new JButton();
        Fetch = new JButton();
        ClearConsole = new JButton();
        AssignDate = new JButton();
        CloseCase = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Judiciary Information System");

        Welcome.setFont(new java.awt.Font("SansSerif", 0, 18));
        Welcome.setText("Welcome");

        UserLabel.setFont(new java.awt.Font("SansSerif", 0, 18)); 

        LogOut.setBackground(new java.awt.Color(156, 161, 157));
        LogOut.setFont(new java.awt.Font("SansSerif", 1, 18));
        LogOut.setForeground(new java.awt.Color(102, 0, 102));
        LogOut.setText("Log Out");
        LogOut.addActionListener(this);

        RegisterNewCase.setBackground(new java.awt.Color(156, 161, 157));
        RegisterNewCase.setFont(new java.awt.Font("SansSerif", 1, 18));
        RegisterNewCase.setForeground(new java.awt.Color(102, 0, 102));
        RegisterNewCase.setText("Register New Case");
        RegisterNewCase.addActionListener(this);

        DisplayDates.setBackground(new java.awt.Color(156, 161, 157));
        DisplayDates.setFont(new java.awt.Font("SansSerif", 1, 18));
        DisplayDates.setForeground(new java.awt.Color(102, 0, 102));
        DisplayDates.setText("Display Dates");
        DisplayDates.addActionListener(this);

        ViewCaseStatus.setBackground(new java.awt.Color(156, 161, 157));
        ViewCaseStatus.setFont(new java.awt.Font("SansSerif", 1, 18));
        ViewCaseStatus.setForeground(new java.awt.Color(102, 0, 102));
        ViewCaseStatus.setText("View Case Status");
        ViewCaseStatus.addActionListener(this);

        CaseDetails.setFont(new java.awt.Font("SansSerif", 1, 18));
        CaseDetails.setMaximumRowCount(4);
        CaseDetails.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Case Details", "Pending Cases", "Resolved Cases", "Upcoming Cases" }));
        CaseDetails.setSelectedItem(CaseDetails);

        EditAccounts.setFont(new java.awt.Font("SansSerif", 1, 18));
        EditAccounts.setMaximumRowCount(3);
        EditAccounts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Edit Accounts", "Add Account", "Delete Account" }));
        EditAccounts.setSelectedItem(EditAccounts);

        JISConsole.setColumns(20);
        JISConsole.setFont(new java.awt.Font("SansSerif", 0, 18));
        JISConsole.setRows(5);
        jScrollPane1.setViewportView(JISConsole);

        JISConsoleLabel.setFont(new java.awt.Font("SansSerif", 1, 18));
        JISConsoleLabel.setText("JIS Console");

        UpdateCaseSummary.setBackground(new java.awt.Color(156, 161, 157));
        UpdateCaseSummary.setFont(new java.awt.Font("SansSerif", 1, 18));
        UpdateCaseSummary.setForeground(new java.awt.Color(102, 0, 102));
        UpdateCaseSummary.setText("Update Case Summary");
        UpdateCaseSummary.addActionListener(this);

        InfoLabel.setFont(new java.awt.Font("SansSerif", 0, 14));
        InfoLabel.setText("Enter the CIN of the case :");

        CINField.setFont(new java.awt.Font("SansSerif", 0, 14));
        
        Update.setBounds(56,580,90,30);
        Update.setBackground(new java.awt.Color(156, 161, 157));
        Update.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        Update.setForeground(new java.awt.Color(102, 0, 102));
        Update.setText("Update");
        jPanel1.add(Update);
        Update.addActionListener(this);
        
        Edit.setBounds(160,580,90,30);
        Edit.setBackground(new java.awt.Color(156, 161, 157));
        Edit.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        Edit.setForeground(new java.awt.Color(102, 0, 102));
        Edit.setText("Edit");
        jPanel1.add(Edit);
        Edit.addActionListener(this);
        
        Fetch.setBounds(264,580,90,30);
        Fetch.setBackground(new java.awt.Color(156, 161, 157));
        Fetch.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        Fetch.setForeground(new java.awt.Color(102, 0, 102));
        Fetch.setText("Fetch");
        jPanel1.add(Fetch);
        Fetch.addActionListener(this);
        
        ClearConsole.setBounds(56,615,145,30);
        ClearConsole.setBackground(new java.awt.Color(156, 161, 157));
        ClearConsole.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        ClearConsole.setForeground(new java.awt.Color(102, 0, 102));
        ClearConsole.setText("Clear Console");
        jPanel1.add(ClearConsole);
        ClearConsole.addActionListener(this);
        
        AssignDate.setBounds(210,615,145,30);
        AssignDate.setBackground(new java.awt.Color(156, 161, 157));
        AssignDate.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        AssignDate.setForeground(new java.awt.Color(102, 0, 102));
        AssignDate.setText("Assign Date");
        jPanel1.add(AssignDate);
        AssignDate.addActionListener(this);
        
        CloseCase.setBounds(125,650,150,30);
        CloseCase.setBackground(new java.awt.Color(156, 161, 157));
        CloseCase.setFont(new java.awt.Font("SansSerif", 1, 14)); 
        CloseCase.setForeground(new java.awt.Color(102, 0, 102));
        CloseCase.setText("Close Case");
        jPanel1.add(CloseCase);
        CloseCase.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(UpdateCaseSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EditAccounts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CaseDetails, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewCaseStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DisplayDates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RegisterNewCase, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addComponent(InfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CINField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(753, Short.MAX_VALUE)
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserLabel)
                .addGap(18, 18, 18)
                .addComponent(LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(650, Short.MAX_VALUE)
                .addComponent(JISConsoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogOut)
                    .addComponent(UserLabel)
                    .addComponent(Welcome))
                .addGap(51, 51, 51)
                .addComponent(JISConsoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(RegisterNewCase)
                        .addGap(28, 28, 28)
                        .addComponent(DisplayDates)
                        .addGap(29, 29, 29)
                        .addComponent(ViewCaseStatus)
                        .addGap(36, 36, 36)
                        .addComponent(CaseDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(EditAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(UpdateCaseSummary)
                        .addGap(37, 37, 37)
                        .addComponent(InfoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CINField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }
    
    public void actionPerformed(ActionEvent click)
    {
        Object source = click.getSource();
        
        if(source == RegisterNewCase)
        {
          try
           {
                rs = q.execute("select * from counter");
                
                while(rs.next())
                {
                    temp = rs.getString(1);
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }  
          
          c.CIN = Integer.parseInt(temp);
          c.CIN++;
          
          try
           {
                rs = q.execute("update counter set count = '"+c.CIN+"'");                 //Setting the CIN for the new case
                
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
          
          c.setVisible(true); 
        }
        
        if(source == DisplayDates)                                                    //Displays the free slots
        {
           try
              {
                  rs = q.execute("select * from cases");
                  
                  JISConsole.append("\nThe dates on which hearings can be scheduled are : \n");
                  
                  while(rs.next())
                  {
                      if(!rs.getString(16).equals("NULL"))
                      {
                        JISConsole.append(rs.getString(16));
                        JISConsole.append("\n");
                      }
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
        
        if(source == ViewCaseStatus)                                               //Displays the status of the selected case
        {  
           if(!CINField.getText().equals(""))
           {
             try
             {
                   rs = q.execute("select caseStatus from cases where CIN = '"+CINField.getText()+"'");
                   JISConsole.append("\n");
                
                   while(rs.next())
                   {
                     JISConsole.append("\n");
                     JISConsole.append("Case Status : ");
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
           
           else
           {
               JOptionPane.showMessageDialog(null,"Enter a CIN","Error",JOptionPane.ERROR_MESSAGE);
           }
        }
        
        if(source == UpdateCaseSummary)                                             //Updates the summary of the selected case
        {
           if(!CINField.getText().equals("") && JISConsole.getText().equals(""))
           {
              try
              {
                  rs = q.execute("select caseSummary from cases where CIN = '"+CINField.getText()+"'");
                
                  while(rs.next())
                  {
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
           
           else if(CINField.getText().equals("") && JISConsole.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Enter a CIN","Error",JOptionPane.ERROR_MESSAGE);
           }
           
           else if(!JISConsole.getText().equals(""))
           {
               JOptionPane.showMessageDialog(null,"Please clear the console first","Error",JOptionPane.ERROR_MESSAGE);
           }
        }
        
        if(source == Update)
        {
          if(!CINField.getText().equals(""))
          {
            try
            {
                rs = q.execute("update cases set caseSummary = '"+JISConsole.getText()+"' where CIN = '"+CINField.getText()+"'");
                
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
          
           JOptionPane.showMessageDialog(null,"Case Summary Updated","Summary",JOptionPane.INFORMATION_MESSAGE);
           JISConsole.setText("");
          }
          
          else
          {
              JOptionPane.showMessageDialog(null,"Enter a CIN","Error",JOptionPane.ERROR_MESSAGE);
          }
        }
        
        if(source == Fetch)                                                      //Fetches the search results
        {
            String temp = (String)CaseDetails.getSelectedItem();
            
            if(temp.equals("Pending Cases") || temp.equals("Resolved Cases") || temp.equals("Upcoming Cases"))
            {
                if(temp.equals("Pending Cases"))                                     //Displays the pending cases 
                {
                    try
                    {
                        rs = q.execute("select * from cases where caseStatus = 'Pending'");
                 
                 
                        JISConsole.append("\n\n************************Pending Cases************************");
                 
                        if(rs.equals("NULL"))
                        {
                            JOptionPane.showMessageDialog(null,"Sorry, No search result found,","Search",JOptionPane.INFORMATION_MESSAGE);
                        }
                 
                       while(rs.next())                                                //Printing the search results
                       {
                            JISConsole.append("\n\n");
                            JISConsole.append("Starting Date : ");
                            JISConsole.append(rs.getString(12));
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
                            JISConsole.append("Crime Location : ");
                            JISConsole.append(rs.getString(6));
                            JISConsole.append("\n");
                            JISConsole.append("Crime Date : ");
                            JISConsole.append(rs.getString(5));
                            JISConsole.append("\n");
                            JISConsole.append("Public Prosecutor's Name : ");
                            JISConsole.append(rs.getString(11));
                            JISConsole.append("\n");
                            JISConsole.append("Lawyer's Name : ");
                            JISConsole.append(rs.getString(10));
                            JISConsole.append("\n");
                            JISConsole.append("Attending Judge's Name : ");
                            JISConsole.append(rs.getString(9));
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
            
                if(temp.equals("Resolved Cases"))                                     //Displays the resolved cases
                {
                    try
                    {
                        rs = q.execute("select * from cases where caseStatus = 'Resolved'");
                 
                        if(rs.equals("NULL"))
                        {
                            JOptionPane.showMessageDialog(null,"Sorry, No search result found,","Search",JOptionPane.INFORMATION_MESSAGE);
                        }
                 
                        JISConsole.append("\n\n************************Resolved Cases************************");
                 
                        while(rs.next())                                                //Printing the search results
                    {
                        JISConsole.append("\n\n");
                        JISConsole.append("Starting Date : ");
                        JISConsole.append(rs.getString(12));
                        JISConsole.append("\n");
                        JISConsole.append("CIN : ");
                        JISConsole.append(rs.getString(1));
                        JISConsole.append("\n");
                        JISConsole.append("Judgement Date : ");
                        JISConsole.append(rs.getString(13));
                        JISConsole.append("\n");
                        JISConsole.append("Attending Judge's Name  : ");
                        JISConsole.append(rs.getString(9));
                        JISConsole.append("\n");
                        JISConsole.append("Judgement Summary : ");
                        JISConsole.append(rs.getString(15));
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
            
               if(temp.equals("Upcoming Cases"))                                    //Displays the upcoming cases
               {
                 try
                 {
                    rs = q.execute("select * from cases");
                  
                    JISConsole.append("\n\nThe dates on which hearings are coming up are : \n");    //Printing the hearing dates
                  
                    while(rs.next())
                    {
                        if(!rs.getString(16).equals("NULL"))
                        {
                            JISConsole.append(rs.getString(16));
                            JISConsole.append("\n");
                        }
                    }
                 }
                 catch (SQLException ex) 
                 {
                     Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                 }
              
                 catch(NullPointerException ex)
                 {
                  
                 }
                
                 date = JOptionPane.showInputDialog("Enter the date in dd/mm/yyyy format : ");
                
                 if(!date.equals(""))
                 {
                   try
                   {
                      rs = q.execute("select CIN from cases where hearingDate = '"+date+"'");
                     
                      if(rs.equals("NULL"))
                      {
                              JOptionPane.showMessageDialog(null,"Sorry, No search result found,","Search",JOptionPane.INFORMATION_MESSAGE);
                      }
                 
                      JISConsole.append("\n\n************************Upcoming Cases************************");        //Printing the search results
                 
                      while(rs.next())
                      {
                            JISConsole.append("\n");
                            JISConsole.append("CIN : ");
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
                
                else
                 {
                    JOptionPane.showMessageDialog(null,"Enter a valid date","Error",JOptionPane.ERROR_MESSAGE);
                 }
             }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null,"Select an option from Case Details","Error",JOptionPane.ERROR_MESSAGE);
            }
           
        }
        
        if(source == LogOut)                                                      //Logs out to the log-in page
        {
            this.setVisible(false);
            new Users().setVisible(true);
        }  
        
        if(source == Edit)                                                        //Adds/Deletes lawyers's/judge's accounts
        {
            String str = (String)EditAccounts.getSelectedItem();
             
            if(str.equals("Add Account") || str.equals("Delete Account"))
            {
                 if(str.equals("Add Account"))
                 {
                    try
                    {
                         temp = JOptionPane.showInputDialog("Press j for new judge and l for new lawyer");
                         user = JOptionPane.showInputDialog("Enter the Username : ");
                         pass = JOptionPane.showInputDialog("Enter the Password");
                
                         if(temp.equals("j"))
                         {
                            rs = q.execute("insert into loginJ values('"+user+"','"+pass+"')");              //Assigns the username & password
                 
                            while(rs.next())
                            {
                  
                            }
                         }
                 
                        if(temp.equals("l"))
                        {
                             rs = q.execute("insert into loginL values('"+user+"','"+pass+"','"+value+"')");
                 
                             while(rs.next())
                             {
                  
                             }
                         }
                    }
              
                    catch (SQLException ex) 
                    {
                      Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                    catch(NullPointerException ex)
                    {
                  
                    }
              
                     JOptionPane.showMessageDialog(null,"New Account Successfully Created","Edit Accounts",JOptionPane.INFORMATION_MESSAGE);
              
                }
                
                if(str.equals("Delete Account"))                                         //Deletes the judge's/lawyer's accounts
                {
                    try
                    {
                        temp = JOptionPane.showInputDialog("Press j for judge and l for lawyer");
                        user = JOptionPane.showInputDialog("Enter the Username whose account is to be deleted : ");
                
                        if(temp.equals("j"))
                        {
                            rs = q.execute("delete from loginJ where username = '"+user+"'");
                        }
                 
                        if(temp.equals("l"))
                        {
                            rs = q.execute("delete from loginL where username = '"+user+"'");  
                        }
                 
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
                    
                    JOptionPane.showMessageDialog(null,"Account Successfully Deleted","Edit Accounts",JOptionPane.INFORMATION_MESSAGE);
                 }
              
            }
            
            else
            {
                JOptionPane.showMessageDialog(null,"Select an option from Edit Accounts","Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        if(source == ClearConsole)
        {
            JISConsole.setText("");
        }
        
        if(source == AssignDate)                                                  //Assigns a new hearing date to a case
        { 
           date = JOptionPane.showInputDialog(null,"Enter a new hearing date : ","Hearing Date",JOptionPane.INFORMATION_MESSAGE);
           
           try
           {
                rs = q.execute("update cases set hearingDate = '"+date+"' where CIN = '"+CINField.getText()+"'");                
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
           
           JOptionPane.showMessageDialog(null," New Hearing Date assigned","Hearing Date",JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(source == CloseCase)                                                 //Changes the case status to Resolved
        {
           try
           {
                rs = q.execute("select caseStatus from cases where CIN = '"+CINField.getText()+"'");                
                while(rs.next())
                {
                  temp = rs.getString(1);
                }     
            }
          
            catch (SQLException ex) 
            {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }  
          
            catch(NullPointerException ex)
            {
                  
            }    
            
          if(!CINField.getText().equals("") && !temp.equals("Resolved")) 
          {
            try
            {
                rs = q.execute("update cases set caseStatus = 'Resolved' where CIN = '"+CINField.getText()+"'");                
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
           
             JOptionPane.showMessageDialog(null," Case Closed","Case Closed",JOptionPane.INFORMATION_MESSAGE);
          }
          
          else if(CINField.getText().equals(""))
          {
              JOptionPane.showMessageDialog(null,"Enter a CIN","Error",JOptionPane.ERROR_MESSAGE);
          }
          
          else if(temp.equals("Resolved"))
          {
              JOptionPane.showMessageDialog(null,"Case already closed","Error",JOptionPane.ERROR_MESSAGE);
          }
          
        }
        
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
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                new Registrar().setVisible(true);
            }
        });
    }
    // Variables declaration
    private JTextField CINField;
    private JComboBox CaseDetails;
    private JButton DisplayDates;
    private JComboBox EditAccounts;
    private JLabel InfoLabel;
    private JTextArea JISConsole;
    private JLabel JISConsoleLabel;
    private JButton LogOut;
    private JButton RegisterNewCase;
    private JButton UpdateCaseSummary;
    private JLabel UserLabel;
    private JButton ViewCaseStatus;
    private JLabel Welcome;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JButton Update;
    private JButton Edit;
    private JButton Fetch;
    private JButton ClearConsole;
    private JButton AssignDate;
    private JButton CloseCase;
    // End of variables declaration

}
