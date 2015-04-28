import play.Application;
import play.GlobalSettings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * Created by csanche on 25/04/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        // TODO Auto-generated method stub
        super.beforeStart(app);

        //String libopencv_java = "/home/csanche/Documents/opencv-2.4.10/build_java/lib/";
                //"/Users/yoonjechoi/git/myFirstApp/target/native_libraries/64bits/libopencv_java246.jnilib";
        System.load("/home/csanche/Documents/opencv-2.4.10/build_java/lib/libopencv_java2410.so");

    }


    /*

    @Override
    public void onStop(Application app) {
        // NOTE: Unload library in here
        try {
            Field field = ClassLoader.class.getDeclaredField("nativeLibraries");
            field.setAccessible(true);
            Vector libs = (Vector)field.get(app.classloader());
            for (Object lib : libs) {
                Method finalize = lib.getClass().getDeclaredMethod("finalize", new Class[0]);
                finalize.setAccessible(true);
                finalize.invoke(lib, new Object[0]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

}
