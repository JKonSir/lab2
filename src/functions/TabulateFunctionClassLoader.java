package functions;

import java.io.*;

/**
 * Created by gijoe on 6/26/2015.
 */
public class TabulateFunctionClassLoader extends ClassLoader {

    public TabulatedFunction classLoader(String fileName) throws IOException {
        File file = null;
        FileInputStream in = null;
        ObjectInputStream oin = null;
        TabulatedFunction function = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            in = new FileInputStream(file.getName());
            oin = new ObjectInputStream(in);
            function = (TabulatedFunction) oin.readObject();
        } catch (ClassNotFoundException ex) {
            oin.close();
            System.out.println(ex);
            return function;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } finally {
            oin.close();
        }

        return function;
    }
}
