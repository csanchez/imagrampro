package controllers;



import org.apache.commons.io.FileUtils;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import play.mvc.Http.MultipartFormData;
import views.html.ImaSec.index;
import views.html.ImaSec.show;
import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * Created by csanche on 15/04/15.
 */
public class ImaSec extends Controller {

    public static Result index() {
        List< models.ImaSec> imasecs = models.ImaSec.find.all();
        return ok(index.render(imasecs));

    }

    public static Result show(Long id) {
        models.ImaSec imaSec = models.ImaSec.find.byId(id);
        return ok(show.render(imaSec));
    }



    public static Result save() {

        MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");

        if (picture != null) {//si se envío la imagen
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            try {
                FileUtils.copyFile(file, new File("public/images/" + fileName));
                //si se pudo guardar en disco extraemos el resto de los parámetros
                Map<String, String[]> params = body.asFormUrlEncoded();
                System.out.println("Pamámetros " + params.toString());
                Long id_tienda =  Long.valueOf(params.get("id_tienda")[0]);
                Long id_mueble =  Long.valueOf(params.get("id_mueble")[0]);
                Long id_seccion = Long.valueOf(params.get("id_seccion")[0]);
                Long id_auditor = Long.valueOf(params.get("id_auditor")[0]);
                models.ImaSec imasec = new models.ImaSec( id_tienda,  id_mueble,  id_seccion,  id_auditor,  fileName);

                imasec.save();

            }catch(java.io.IOException ioe){
                return ok("Ocurrio un error al guardar la foto");
            }
            //return ok("File uploaded");
            return redirect("/imasec/index");
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index());
        }
    }


}
