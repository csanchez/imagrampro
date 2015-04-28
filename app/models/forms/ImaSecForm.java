package models.forms;

import play.data.validation.Constraints;

/**
 * Created by csanche on 22/04/15.
 */
public class ImaSecForm {

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
}
