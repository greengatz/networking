import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class MyServer {
   JFrame window;
   JPanel content;
   ServerSocket sock;
   Socket connection;
   Scanner input;
   JLabel text;
   
   public MyServer(){
      window = new JFrame();
      content = new JPanel();
      text = new JLabel();
      
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
      window.setTitle("Server");
      window.setSize(300, 200);
      initButtons();
      content.add(text);
      window.setContentPane(content);
      
      window.show();
   }
   
   public static void main(String[] args){
      new MyServer();
   }
   
   void initButtons(){
      JButton startBut = new JButton("start");
      JButton acceptBut = new JButton("accept connection");
      JButton clientBut = new JButton("start client");
      JButton scanIn = new JButton("scan");
      
      startBut.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            startButClicked();
         }
      });
      
      acceptBut.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            acceptClicked();
         }
      });
      
      clientBut.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            new Client();
         }
      });
      
      scanIn.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            scan();
         }
      });
      
      content.add(startBut);
      content.add(acceptBut);
      content.add(clientBut);
      content.add(scanIn);
   }
   
   void startButClicked(){
      startServer();
   }
   
   void startServer(){
      try{
         sock = new ServerSocket(8850);
         System.out.println("Port: " + sock.getLocalPort());
         System.out.println("Address: " + sock.getInetAddress().getLocalHost().getHostAddress());
         System.out.println("Host Name: " + sock.getInetAddress().getLocalHost().getHostName());
      }
      catch(Exception e){
         System.out.println("something bad happened");
      }
   }
   
   void acceptClicked(){
      try{
         connection = sock.accept();
         InputStream in = connection.getInputStream();
         input = new Scanner(in);
      }
      catch(Exception e){
         System.out.println("Error when accepting connection");
      }
   }
   
   void scan(){
      if(input.hasNextLine())
         text.setText(input.nextLine());
   }
}