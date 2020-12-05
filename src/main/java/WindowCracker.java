import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WindowCracker extends JFrame {
    CezarEncoder cezarEncoder = new CezarEncoder();
    PermutationsEncoder permutationsEncoder = new PermutationsEncoder();
    VijnerEncoder vijnerEncoder = new VijnerEncoder();
    SecretCracker secretCracker = new SecretCracker();


    private JRadioButton shiftsRadio;
    private JRadioButton premutationRadio;
    private JRadioButton vijerRadio;


    public WindowCracker() {
        super("SberCracker");


        JPanel content = new JPanel();
        content.setLayout(null);
        setBackground(new Color(1111111));

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


        JLabel second = new JLabel("Подобранный ключ");
        second.setBounds(10, 210, 400, 21);

        JLabel third = new JLabel("Зашифрованный текст");
        third.setBounds(10, 125, 400, 21);
        JLabel first = new JLabel("Вероятное слово");
        first.setBounds(10, 40, 400, 21);

        JLabel maxKey = new JLabel("Цифра для ключа");
        maxKey.setBounds(10, 310, 400, 21);


        JLabel maxKeyAbout = new JLabel("Для шифра Виджера - цифра это количество букв в ключе,");
        JLabel maxKeyAbout2 = new JLabel("для остальных - максимальное возможное значение ключа");
        maxKeyAbout.setBounds(10, 400, 400, 60);
        maxKeyAbout2.setBounds(10, 410, 400, 60);


        final JTextArea f = new JTextArea();
        f.setBounds(10, 65, 400, 21);
        f.setSize(400, 50);

        final JTextArea s = new JTextArea();
        s.setBounds(10, 235, 400, 21);
        s.setSize(400, 50);

        final JTextArea t = new JTextArea();
        t.setSize(400, 50);
        t.setBounds(10, 150, 400, 21);

        final JTextArea maxKeyArea = new JTextArea();
        maxKeyArea.setSize(400, 50);
        maxKeyArea.setBounds(10, 330, 400, 21);

        JButton one = new JButton("Взломать ключ");
        one.setBounds(200, 290, 200, 21);
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                char[] chipper = t.getText().toCharArray();
                char[] textPart = f.getText().toCharArray();
                char[] maxKey = maxKeyArea.getText().toCharArray();
                String msg;
                String result;
                String regex = "\\d+";
                String maxKeyStr = String.valueOf(maxKey);
                if (!maxKeyStr.matches(regex) || maxKeyStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Для поиска ключа необходимо ввести цифру!");
                } else if (shiftsRadio.isSelected()) {
                    result = secretCracker.broodForceCesar(String.valueOf(chipper), String.valueOf(textPart), Integer.parseInt(maxKeyStr));
                    s.setText(result);
                } else if (premutationRadio.isSelected()) {
                    result = secretCracker.broodForcePermutations(String.valueOf(chipper), String.valueOf(textPart), Integer.parseInt(maxKeyStr));
                    s.setText(result);
                } else if (vijerRadio.isSelected()) {
                    result = secretCracker.broodForceVijner(String.valueOf(chipper), String.valueOf(textPart), Integer.parseInt(maxKeyStr));
                    s.setText(result);
                } else {
                    msg = "Не правильно введены данные";
                    s.setText(msg);
                }
            }
        });
        JButton two = new JButton("Загрузить шифр из файла");
        two.setBounds(200, 125, 200, 21);
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1actionActionPerformed(evt);
            }

            private void button1actionActionPerformed(java.awt.event.ActionEvent evt) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = Files.newBufferedReader(Paths.get("chipper-output.txt"))) {
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
        content.add(maxKey);
        content.add(maxKeyAbout);
        content.add(maxKeyArea);
        content.add(maxKeyAbout2);


        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);


    }


    public static void main(String[] args) {
        WindowCracker set = new WindowCracker();
        set.setVisible(true);
    }
}