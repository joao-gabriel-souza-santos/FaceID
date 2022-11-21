package model;



public class Funcionario {
   
    //VARIAVEIS
    
    public Funcionario(int codigo, int id, String nome, String cargo, String telefone) {
        this.codigo = codigo;
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
    }



    private int codigo, id;
    private String nome;
    private String cargo;
    private String telefone;
    private byte[] foto;
    




    //CONSTRUTORES
    public Funcionario(){
        //CONSTRUTOR DEFAULT
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


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone2) {
        this.telefone = telefone2;
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



    public String getCargo() {
        return cargo;
    }



    public void setCargo(String cargo2) {
        this.cargo = cargo2;
    }
    
  
    
}
