import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultCaret; //////
public class SwingChatGuiV2 extends JFrame {
    public JButton sendButton;

    public JTextArea rxArea;
    public JTextField txArea;
    public Container container;
    public JScrollPane pane; ////   
    public SwingChatGuiV2(String title) {
        super(title);

        container = getContentPane();
        container.setLayout(new FlowLayout());

        txArea = new JTextField(40);

        rxArea = new JTextArea(6, 40);
        rxArea.setEditable(false); /////
        pane = new JScrollPane(rxArea); /////

        DefaultCaret caret = (DefaultCaret) rxArea.getCaret(); /////
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM); //////
        sendButton = new JButton("Send");

        container.add(pane);
        container.add(txArea);
        container.add(sendButton);
    }
}