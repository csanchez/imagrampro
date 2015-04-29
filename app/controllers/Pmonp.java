package controllers;

import mx.imagrampro.core.*;
import mx.imagrampro.dao.Product;
import mx.imagrampro.dao.Realogram;
import mx.imagrampro.utils.Label;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Pmonp.precpro;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanche on 25/04/15.
 */
public class Pmonp extends Controller {

    public static Result precpro(Long id) {
        System.load("/home/csanche/Documents/opencv-2.4.10/build_java/lib/libopencv_java2410.so");
        models.ImaSec imaSec = models.ImaSec.find.byId(id);
        Realogram r = new Realogram(Highgui.imread("/home/csanche/Documents/Documentos/PROJECTS/JAVA/imagrampro/public/images/realogram.jpg", Highgui.CV_LOAD_IMAGE_COLOR));
        Product p = new Product(Highgui.imread("/home/csanche/Documents/Documentos/PROJECTS/JAVA/imagrampro/public/images/04.jpg", Highgui.CV_LOAD_IMAGE_COLOR));
        Product smallProduct  = new Product(Rescale.rescale(p, 220));

                List<Mat> realSplitYCrCb = new ArrayList<Mat>();
                realSplitYCrCb = Divide.getColorBands(Convert.convertBGR2YCrCb(r));

                List<Mat> smallProdSplitYCrCb = new ArrayList<Mat>();
                smallProdSplitYCrCb = Divide.getColorBands(Convert.convertBGR2YCrCb(smallProduct));
                Mat reference = Match.getReference(realSplitYCrCb, smallProdSplitYCrCb);

                smallProdSplitYCrCb = Divide.getColorBands(reference);

                List<org.opencv.core.Point> points = Match.getPointList(realSplitYCrCb, smallProdSplitYCrCb, 0.9f, 0.9);
                for (org.opencv.core.Point point : points) {

                    Rect coordenadas = new Rect((int) point.x, (int) point.y, smallProduct.width(), smallProduct.height());
                    Label.setFrame(r, coordenadas);
                }

                List<Mat> candidates = Match.getPatterns(r, points,smallProduct.height(), smallProduct.width());
                List<Mat> candidate = Patch.getPatches(Convert.convertBGR2Gray(candidates.get(0)), 4);
                Rect coordenadas = new Rect((int) points.get(0).x,(int) points.get(0).y, smallProduct.width(),smallProduct.height());
                Label.setFrame(r, coordenadas, 0);

                EdgeOrientedHistogram candiHIst = EdgeOrientedHistogram.getEdgeHistogram(candidate);


                for (int i = 1; i < candidates.size(); i++) {
                    List<Mat> cand = Patch.getPatches(Convert.convertBGR2Gray(candidates.get(i)), 4);
                    EdgeOrientedHistogram cadst = EdgeOrientedHistogram.getEdgeHistogram(cand);
                    double dist = EdgeOrientedHistogram.getDistance(candiHIst.getEdgeHistogram(), cadst.getEdgeHistogram());
                    if (dist > 0.9) {
                        coordenadas = new Rect((int) points.get(i).x,(int) points.get(i).y, smallProduct.width(),smallProduct.height());
                        Label.setFrame(r, coordenadas, i);
                    }
                }

                Highgui.imwrite("/home/csanche/Documents/Documentos/PROJECTS/JAVA/imagrampro/public/images/realogram_processed.png",r);

                BufferedImage out;
                byte[] data = new byte[(int)r.size().width *(int)r.size().height * (int)r.elemSize()];
                int type;
                r.get(0, 0, data);

                 if(r.channels() == 1)
                    type = BufferedImage.TYPE_BYTE_GRAY;
                 else
                    type = BufferedImage.TYPE_3BYTE_BGR;

                out = new BufferedImage(320, 240, type);

                out.getRaster().setDataElements(0, 0, 320, 240, data);

                try {
                    File outputfile = new File("/home/csanche/Documents/Documentos/PROJECTS/JAVA/imagrampro/public/images/realogram_processed.jpg");
                    ImageIO.write(out, "jpg", outputfile);
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }


            return ok(precpro.render(imaSec, "04.jpg","realogram_processed.jpg"));
    }
}
