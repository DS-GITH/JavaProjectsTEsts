package CadastroELoginComplexo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel PanelGeral;
	private JTextField nomeField;
	private JPasswordField senhaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		PanelGeral = new JPanel();
		PanelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(PanelGeral);
		PanelGeral.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 264, 207);
		PanelGeral.add(panel);
		panel.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel nomeLabel = new JLabel("Nome:");
		panel.add(nomeLabel);
		
		nomeField = new JTextField();
		panel.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel senhaLabel = new JLabel("Senha:");
		panel.add(senhaLabel);
		
		senhaField = new JPasswordField();
		panel.add(senhaField);
		
		JButton logarBtn = new JButton("Logar");
		panel.add(logarBtn);
		logarBtn.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
		        String nome = nomeField.getText(); // Pega o texto da JTextField
		        String senha = new String(senhaField.getPassword()); // Pega o texto do JPasswordField
			
		        if (LoginGUI.validarLogin(nome, senha)) {
		            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

		            // Abrindo a nova interface
		            MenuGUI menuGUI = new MenuGUI();
		            menuGUI.setVisible(true);

		            // Fecha a janela de login atual
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		        
		}
});
		
		JButton criarBtn = new JButton("Criar Conta");
		criarBtn.setBounds(10, 228, 107, 23);
		PanelGeral.add(criarBtn);

	}
	
	
	 public static boolean validarLogin(String nome, String senha) {
	        String sql = "SELECT * FROM users WHERE nome = ? AND senha = ?";
	        try (Connection conexao = BdConnection.getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {

	            stmt.setString(1, nome);
	            stmt.setString(2, senha);

	            try (ResultSet rs = stmt.executeQuery()) {
	                return rs.next(); // Retorna true se encontrar o usuário no banco
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Erro ao validar login!");
	        }
	    }
}
