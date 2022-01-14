package modulo.prodep.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/*Combrobar si guardar la fecha y hora en variables string o date*/
//Ya que la fecha no se utilizará para hace calculos y sólo es informtiva se guardará en string.
//Pero en la BD sí se guardará como datetime con el formato:
//2021-10-21 09:02:00-07:00
//YYYY-MM-DD hh:mm:ss-00:00
//Las horas son en 24, 
import java.util.TimeZone;


public class Comprobacion {
    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String zona_horaria = "-07:00";
    private int id_comp;
    private int id_usuario;
    private int id_fc;
    private String doc_url;
    private Date fecha;
    private int estado;
    private Date ultima_revision; //tambien combrobar
    private String comentario;

    private Usuario usuario;
    private Fc fc;
    
    public Comprobacion() {
        this.usuario = new Usuario();
        this.fc = new Fc();
        Comprobacion.df.setTimeZone(TimeZone.getTimeZone("GMT-7:00"));
    }
    public Comprobacion(int id_comp, int id_usuario, int id_fc, String doc_url, Date fecha, int estado,
            Date ultima_revision, String comentario) {
        this.id_comp = id_comp;
        this.id_usuario = id_usuario;
        this.id_fc = id_fc;
        this.doc_url = doc_url;
        this.fecha = fecha;
        this.estado = estado;
        this.ultima_revision = ultima_revision;
        this.comentario = comentario;
        this.usuario = new Usuario();
        this.fc = new Fc();
        Comprobacion.df.setTimeZone(TimeZone.getTimeZone("GMT-7:00"));
    }
    public int getId_comp() {
        return id_comp;
    }
    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getId_fc() {
        return id_fc;
    }
    public void setId_fc(int id_fc) {
        this.id_fc = id_fc;
    }
    public String getDoc_url() {
        return doc_url;
    }
    public void setDoc_url(String doc_url) {
        this.doc_url = doc_url;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public Date getUltima_revision() {
        return ultima_revision;
    }
    public void setUltima_revision(Date ultima_revision) {
        this.ultima_revision = ultima_revision;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Fc getFc() {
        return fc;
    }
    public void setFc(Fc fc) {
        this.fc = fc;
    }
}
