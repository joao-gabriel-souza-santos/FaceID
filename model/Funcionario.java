package model;



public class Funcionario {
   
    //VARIAVEIS
    private int codigo;
    private String nome;
    private int cpf;
    private int telefone;
    private byte[] foto;
    
    //CONSTRUTORES
    public Funcionario(){
        //CONSTRUTOR DEFAULT
    }

    public Funcionario(int codigo, String nome, int cpf, int telefone, byte[] foto) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto = foto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
  
    
}
