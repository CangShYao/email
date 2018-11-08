import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class e_GUI {
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextField textField2;
    private JButton button1;
    private JPanel jpanel;

    public e_GUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiveMailAccount = textField1.getText();
                String emailSubject = textField2.getText();
                String emailContent = textArea1.getText();
                Send send = new Send(receiveMailAccount, emailSubject, emailContent);
            }
        });
    }

    public void init() {
        JFrame frame = new JFrame("Email");
        frame.setContentPane(new e_GUI().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width) / 2) - 475,
                ((Toolkit.getDefaultToolkit().getScreenSize().height) / 2) - 300,
                950, 600);
        frame.setMinimumSize(new Dimension(950, 500));
        frame.setVisible(true);
    }

}
