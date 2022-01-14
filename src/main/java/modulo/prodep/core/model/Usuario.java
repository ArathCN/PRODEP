package modulo.prodep.core.model;

public class Usuario {
    private int id_usuario;
    private String nombre;
    private String a_paterno;
    private String a_materno;
    private int permiso;

    public Usuario(){
        
    }
    public Usuario(int id_usuario, String nombre, String a_paterno, String a_materno, int permiso) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.a_paterno = a_paterno;
        this.a_materno = a_materno;
        this.permiso = permiso;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getA_paterno() {
        return a_paterno;
    }
    public void setA_paterno(String a_paterno) {
        this.a_paterno = a_paterno;
    }
    public String getA_materno() {
        return a_materno;
    }
    public void setA_materno(String a_materno) {
        this.a_materno = a_materno;
    }
    public int getPermiso() {
        return permiso;
    }
    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }
    
}
