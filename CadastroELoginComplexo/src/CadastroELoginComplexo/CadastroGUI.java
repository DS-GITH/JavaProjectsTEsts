package CadastroELoginComplexo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CadastroGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel PainelGeral;
    private JPanel cadastrarPanel;
    private JLabel nomelabel;
    private JTextField nomeField;
    private JLabel senhaLabel;
    private JPasswordField senhaField;
    private JButton cadastrarBtn;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastroGUI frame = new CadastroGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CadastroGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 342);
        PainelGeral = new JPanel();
        PainelGeral.setBorder(new TitledBorder(null, "Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        setContentPane(PainelGeral);
        PainelGeral.setLayout(null);
        
        cadastrarPanel = new JPanel();
        cadastrarPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        cadastrarPanel.setBounds(10, 16, 264, 204);
        PainelGeral.add(cadastrarPanel);
        cadastrarPanel.setLayout(new GridLayout(5, 0, 0, 0));
        setLocationRelativeTo(null);
        
        nomelabel = new JLabel("Nome:");
        cadastrarPanel.add(nomelabel);
        
        nomeField = new JTextField();
        nomeField.setColumns(10);
        cadastrarPanel.add(nomeField);
        
        senhaLabel = new JLabel("Senha:");
        cadastrarPanel.add(senhaLabel);
        
        senhaField = new JPasswordField();
        cadastrarPanel.add(senhaField);
        
        cadastrarBtn = new JButton("Cadastrar");
        cadastrarPanel.add(cadastrarBtn);
        cadastrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = new String(senhaField.getPassword());

                // Criando o objeto User
                User user = new User(nome, senha);

                // Se for necessário um método estático, usar CadastroGUI.Registrar(user);
                if (Registrar(user)) {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    MenuGUI menuGUI = new MenuGUI();
                    menuGUI.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public boolean Registrar(User user) {
        String sql = "INSERT INTO users (nome, senha) VALUES (?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getSenha());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
