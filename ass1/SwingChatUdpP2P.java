import java.awt.*;

import java.net.*;

import java.awt.*;         

import java.awt.event.*;   

import javax.swing.*;      

import javax.swing.event.*;

import java.io.*;

 

public class SwingChatUdpP2P extends SwingChatGuiV2

{

    String inputLine, outputLine;

    static String remoteAddr;

    static int localPort, remotePort; ;

    public ButtonHandler bHandler; // = new ButtonHandler();

    DatagramSocket socket;

        

    public SwingChatUdpP2P (String title) throws IOException

    {

     super (title);

     bHandler = new ButtonHandler ();

     sendButton.addActionListener (bHandler);

 

     socket = new DatagramSocket (localPort);

    }

 

    // Transmit Message

    private class ButtonHandler implements ActionListener

    {

     public void actionPerformed (ActionEvent event) //throws IOException

     {

          try

          {             

               DatagramSocket socket = new DatagramSocket ();

               byte[] buf = new byte[256];

 

               String outputLine = txArea.getText ();

         

               buf = outputLine.getBytes ();

               InetAddress address = InetAddress.getByName (remoteAddr);

               DatagramPacket packet = new DatagramPacket (buf, buf.length, address, remotePort);

               System.out.println ("About to send message");

               socket.send (packet);

               System.out.println ("Sent message");

          }

          catch (IOException e)

          {

          }        

     }

    }

   

    // Receive Message

    public void run () throws IOException

    {

          try

          {

               DatagramPacket packet;

               byte[] buf = new byte[256];

 

               while (true)

                     {

                     packet = new DatagramPacket (buf, buf.length);

                     socket.receive (packet);

                     System.out.println ("Received packet");

                     String received = new String (packet.getData());

                          rxArea.setText (received);

               }

          }

          catch (IOException e)

          {

          }        

    }

 

    public static void main (String[] args) throws IOException

    {

    if (args.length != 3)

    {

          System.out.println ("Usage: java SwingChatUdpP2P <local-port> <remote-ipaddress> <remote-port>");

         

          System.exit (1);

    }

    else

    {

          localPort = Integer.parseInt (args[0]);

          remoteAddr = args[1];

          remotePort = Integer.parseInt (args[2]);

    }

   

     SwingChatUdpP2P f = new SwingChatUdpP2P ("Chat UDP P2P Program");

    

     f.pack ();

     f.show ();

    

     try

     {

          f.run ();

     }

     catch (IOException e)

     {

          System.err.println ("Couldn't get I/O for the connection to: 194.81.104.118.");

          System.exit (1);

     }

    }

}