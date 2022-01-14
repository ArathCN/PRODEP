package modulo.prodep.core.model;

public class Fc {
    private int id_fc;
    private int id_rubro;
    private String nombre;
    private String descripcion;
    private Rubro rubro;
    
    public Rubro getRubro() {
        return rubro;
    }
    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }
    public int getId_fc() {
        return id_fc;
    }
    public void setId_fc(int id_fc) {
        this.id_fc = id_fc;
    }
    public int getId_rubro() {
        return id_rubro;
    }
    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
