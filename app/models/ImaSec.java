package models;

/**
 * Created by csanche on 22/04/15.
 */


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "imasec")
public class ImaSec extends Model {

    @Id
    @SequenceGenerator(name="gen", sequenceName="imasec_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    public Long id;

    @Constraints.Required
    public Long id_tienda;

    @Constraints.Required
    public Long id_mueble;

    @Constraints.Required
    public Long id_seccion;

    @Constraints.Required
    public Long id_auditor;

    @Constraints.Required
    public String picture;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date fecha;
    @Formats.DateTime(pattern="H:i:s")
    public Date hora;

    public static Finder<Long,ImaSec> find = new Finder<Long,ImaSec>(
            Long.class, ImaSec.class
    );

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Long id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Long getId_mueble() {
        return id_mueble;
    }

    public void setId_mueble(Long id_mueble) {
        this.id_mueble = id_mueble;
    }

    public Long getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(Long id_seccion) {
        this.id_seccion = id_seccion;
    }

    public Long getId_auditor() {
        return id_auditor;
    }

    public void setId_auditor(Long id_auditor) {
        this.id_auditor = id_auditor;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }



    public ImaSec(Long id_tienda, Long id_mueble, Long id_seccion, Long id_auditor, String picture) {
        this.id = id;
        this.id_tienda = id_tienda;
        this.id_mueble = id_mueble;
        this.id_seccion = id_seccion;
        this.id_auditor = id_auditor;
        this.picture = picture;
    }



}
