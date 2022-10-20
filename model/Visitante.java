package model;



public class Visitante {

    //VARIAVEIS
    private String nome;
    private int cpf;
    private int telefone;
    private String motivo;
    private byte[]foto;

  
    //CONSTRUTORES
    public Visitante (){
        //CONSTRUTOR DEFAULT
    }

    public Visitante(String nome, int cpf, int telefone, String motivo, byte[] foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.motivo = motivo;
        this.foto = foto;
    }

    //GETTERS E SETTERS
    
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



    public String getMotivo() {
        return motivo;
    }



    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }



    public byte[] getFoto() {
        return foto;
    }



    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    
}
