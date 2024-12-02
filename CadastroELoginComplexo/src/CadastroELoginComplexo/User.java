package CadastroELoginComplexo;

public class User {
	
	String nome;
	String senha;
	
	public User(String nome, String senha) {
		
		this.nome = nome;
		this.senha = senha;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
