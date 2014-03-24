import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Client{
   JFrame window;
   JPanel content;
   Socket conn;
   OutputStream out;
   PrintStream ps;
   JTextField message;
   
   public Client(){
      window = new JFrame();
      content = new JPanel();
      message = new JTextField();

      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
      window.setTitle("Client");
      window.setSize(300, 200);
      initButtons();
      content.add(message);
      window.setContentPane(content);
      
      window.show();
   }
   
   public static void main(String[] args){
      new Client();
   }
   
   void initButtons(){
      JButton connectBut = new JButton("connect");
      JButton sendBut = new JButton("send");
      
      connectBut.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            connectClicked();
         }
      });
      
      sendBut.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            sendClicked();
         }
      });
      
      content.add(connectBut);
      content.add(sendBut);
   }
   
   void connectClicked(){
      try{
         conn = new Socket("localhost", 8850);
         out = conn.getOutputStream();
         ps = new PrintStream(out, true);
      }
      catch(Exception e){
      }
   }
   
   void sendClicked(){
      ps.println(message.getText());
      message.setText("");
   }
}