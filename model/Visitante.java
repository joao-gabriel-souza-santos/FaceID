package model;



public class Visitante {

    public Visitante(int id, String nome, String cpf, String telefone, String motivo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.motivo = motivo;
    }



    //VARIAVEIS
    private int id;
    private String nome, cpf, telefone, motivo;
    private byte[]foto;

  
    //CONSTRUTORES
    public Visitante (){
        //CONSTRUTOR DEFAULT
    }

  

    //GETTERS E SETTERS
    
    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
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



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getCpf() {
        return cpf;
    }



    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    public String getTelefone() {
        return telefone;
    }



    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
}
