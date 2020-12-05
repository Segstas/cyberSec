import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WindowDecoder extends JFrame {
    CezarEncoder shiftsEncoder = new CezarEncoder();
    PermutationsEncoder permutationsEncoder = new PermutationsEncoder();
    VijnerEncoder vijnerEncoder = new VijnerEncoder();


    private JRadioButton shiftsRadio;
    private JRadioButton premutationRadio;
    private JRadioButton vijerRadio;


    public WindowDecoder() {
        super("SberDecoder");


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


        JLabel first = new JLabel("Расшифрованный текст");
        first.setBounds(10, 210, 400, 21);
        JLabel second = new JLabel("Ключ");
        second.setBounds(10, 125, 400, 21);
        JLabel third = new JLabel("Зашифрованный текст");
        third.setBounds(10, 40, 400, 21);

        final JTextArea f = new JTextArea();
        f.setBounds(10, 235, 400, 21);
        f.setSize(400, 50);

        final JTextArea s = new JTextArea();
        s.setBounds(10, 150, 400, 21);
        s.setSize(400, 50);

        final JTextArea t = new JTextArea();
        t.setBounds(10, 65, 400, 21);
        t.setSize(400, 50);

        JButton one = new JButton("Расшифровать");
        one.setBounds(100, 290, 200, 21);
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                char[] key = s.getText().toCharArray();
                char[] text = t.getText().toCharArray();
                String msg;
                String result;
                String regex = "\\d+";


                if (shiftsRadio.isSelected())
                    if (String.valueOf(key).matches(regex)) {
                        result = shiftsEncoder.decode(String.valueOf(text), Integer.parseInt(String.valueOf(key)));
                        f.setText(result);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ключем этого шифра является число!");
                    }

                else if (premutationRadio.isSelected())
                    if (String.valueOf(key).matches(regex)) {
                        result = permutationsEncoder.decode(String.valueOf(text), Integer.parseInt(String.valueOf(key)));
                        f.setText(result);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ключем этого шифра является число!");
                    }
                else if (vijerRadio.isSelected()) {
                    result = vijnerEncoder.decode(String.valueOf(text), String.valueOf(key));
                    f.setText(result);
                } else {
                    msg = "Не правильно введены данные";
                    f.setText(msg);
                }
            }
        });
        JButton two = new JButton("Загрузить шифр из файла");
        two.setBounds(100, 125, 200, 21);
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("chipper-output.txt"))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e);

                }
                System.out.println(sb);
                t.setText(sb.toString());
                JOptionPane.showMessageDialog(null, "Шифр загружен из chipper-output.txt");
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
        WindowDecoder set = new WindowDecoder();
        set.setVisible(true);
    }
}