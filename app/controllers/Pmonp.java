package controllers;

import mx.imagrampro.dao.Product;
import org.opencv.highgui.Highgui;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by csanche on 25/04/15.
 */
public class Pmonp extends Controller {

    public static Result precpro(Long id) {
        models.ImaSec imaSec = models.ImaSec.find.byId(id);
        Product p = new Product(Highgui.imread("/home/csanche/Documents/Documentos/PROJECTS/JAVA/imagrampro/public/images/pinches_huevotes.jpg", Highgui.CV_LOAD_IMAGE_COLOR));
        return redirect("/imasec/index");
    }
}
