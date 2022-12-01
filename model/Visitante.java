package model;



public class Visitante {

    public Visitante(int id, String nome, String cpf, String telefone, String motivo, String dataEntrar,
            String horarioEntrar, String dataSair, String horarioSair) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.motivo = motivo;
        this.dataEntrar = dataEntrar;
        this.horarioEntrar = horarioEntrar;
        this.dataSair = dataSair;
        this.horarioSair = horarioSair;
    }



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
    private String dataEntrar;
    private String horarioEntrar;
    private String dataSair;
    private String horarioSair;

  
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

    
}
