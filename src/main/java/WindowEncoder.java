import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class WindowEncoder extends JFrame {
    CezarEncoder shiftsEncoder = new CezarEncoder();
    PermutationsEncoder permutationsEncoder = new PermutationsEncoder();
    VijnerEncoder vijnerEncoder = new VijnerEncoder();


    private JRadioButton shiftsRadio;
    private JRadioButton premutationRadio;
    private JRadioButton vijerRadio;


    public WindowEncoder() {
        super("SberEncoder");


        JPanel content = new JPanel();
        content.setLayout(null);


        shiftsRadio = new JRadioButton("Шифр сдвигом");
        premutationRadio = new JRadioButton("Шифр перестановкой");
        vijerRadio = new JRadioButton("Шифр Виджера");


        premutationRadio.setMnemonic(KeyEvent.VK_P);
        shiftsRadio.setMnemonic(KeyEvent.VK_P);
        vijerRadio.setMnemonic(KeyEvent.VK_P);

        premutationRadio.setBounds(10, 20, 170, 21);
        shiftsRadio.setBounds(180, 20, 150, 21);
        vijerRadio.setBounds(330, 20, 150, 21);
        ButtonGroup group = new ButtonGroup();

        group.add(premutationRadio);
        group.add(shiftsRadio);
        group.add(vijerRadio);


        content.add(shiftsRadio);
        content.add(premutationRadio);
        content.add(vijerRadio);


        JLabel first = new JLabel("Текст");
        first.setBounds(10, 40, 400, 21);
        JLabel second = new JLabel("Ключ");
        second.setBounds(10, 125, 400, 21);
        JLabel third = new JLabel("Зашифрованный текст");
        third.setBounds(10, 210, 400, 21);

        final JTextArea f = new JTextArea();
        f.setBounds(10, 65, 400, 21);
        f.setSize(400, 50);

        final JTextArea s = new JTextArea();
        s.setBounds(10, 150, 400, 21);
        s.setSize(400, 50);

        final JTextArea t = new JTextArea();
        t.setBounds(10, 235, 400, 21);
        t.setSize(400, 50);

        JButton one = new JButton("Зашифровать");
        one.setBounds(100, 125, 200, 21);
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                char[] key = s.getText().toCharArray();
                char[] text = f.getText().toCharArray();
                String msg;
                String result;
                String regex = "\\d+";


                if (shiftsRadio.isSelected())
                    if (String.valueOf(key).matches(regex)) {
                        result = shiftsEncoder.encode(String.valueOf(text), Integer.parseInt(String.valueOf(key)));
                        t.setText(result);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ключем этого шифра является число!");
                    }

                else if (premutationRadio.isSelected())
                    if (String.valueOf(key).matches(regex)) {
                        result = permutationsEncoder.encode(String.valueOf(text), Integer.parseInt(String.valueOf(key)));
                        t.setText(result);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ключем этого шифра является число!");
                    }
                else if (vijerRadio.isSelected()) {
                    result = vijnerEncoder.encode(String.valueOf(text), String.valueOf(key));
                    t.setText(result);
                } else {
                    msg = "Не правильно введены данные";
                    t.setText(msg);
                }
            }
        });
        JButton two = new JButton("Сохранить шифр в файл");
        two.setBounds(100, 290, 200, 21);
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                char[] text = t.getText().toCharArray();

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("chipper-output.txt");
                    fos.write(String.valueOf(text).getBytes());
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e);
                }
                JOptionPane.showMessageDialog(null, "Шифр сохранен в chipper-output.txt");
            }
        });


        setPreferredSize(new Dimension(500, 400));
        content.add(first);
        content.add(second);
        content.add(third);
        content.add(f);
        content.add(s);
        content.add(t);
        content.add(one);
        content.add(two);

        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);


    }


    public static void main(String[] args) {
        WindowEncoder set = new WindowEncoder();
        set.setVisible(true);
    }

}