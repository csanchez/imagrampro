package controllers;



import models.forms.ImaSecForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());

    }

    public static Result foto() {
        //ImaSecForm imaSecForm = new ImaSecForm();
        Form<ImaSecForm> formData = Form.form(ImaSecForm.class);
        return ok(foto.render(formData));
    }

}
