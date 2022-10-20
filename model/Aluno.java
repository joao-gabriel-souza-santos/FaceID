package model;



public class Aluno {
    public Aluno(String matricula, String nome, String cpf, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }



    public Aluno(String matricula, String nome, String cpf, String telefone, byte[] foto) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto = foto;
    }




    //variaveis
    private String matricula;
    private String nome;
    private String cpf;
    private String telefone;
    private byte[] foto;
    
    


    //CONSTRUTORES
    public Aluno (){
        //CONSTRUTOR DEFAULT
    }



    public Aluno(Aluno pesquisarCad) {
    }



    //GETTERS E SETTERS
    public String getMatricula() {
        return matricula;
    }




    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }




    public String getNome() {
        return nome;
    }




    public void setNome(String nome) {
        this.nome = nome;
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




    public byte[] getFoto() {
        return foto;
    }




    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
   

}
