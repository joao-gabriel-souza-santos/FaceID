package model;



public class Aluno {
    
    
    public Aluno(String matricula, String nome, String cpf, String telefone, int id, String dataEntrar,
            String horarioEntrar, String dataSair, String horarioSair) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.id = id;
        this.dataEntrar = dataEntrar;
        this.horarioEntrar = horarioEntrar;
        this.dataSair = dataSair;
        this.horarioSair = horarioSair;
    }



    public Aluno(int id, String matricula, String nome, String cpf, String telefone) {
        this.id = id;
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
    private int id;
    private String dataEntrar;
    private String horarioEntrar;
    private String dataSair;
    private String horarioSair;


    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



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




    public void setMatricula(String string) {
        this.matricula = string;
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



    public String getDataEntrar() {
        return dataEntrar;
    }



    public void setDataEntrar(String dataEntrar) {
        this.dataEntrar = dataEntrar;
    }



    public String getHorarioEntrar() {
        return horarioEntrar;
    }



    public void setHorarioEntrar(String horarioEntrar) {
        this.horarioEntrar = horarioEntrar;
    }



    public String getDataSair() {
        return dataSair;
    }



    public void setDataSair(String dataSair) {
        this.dataSair = dataSair;
    }



    public String getHorarioSair() {
        return horarioSair;
    }



    public void setHorarioSair(String horarioSair) {
        this.horarioSair = horarioSair;
    }



    public void getHorarioEntrar(String string) {
    }
    
   

}
