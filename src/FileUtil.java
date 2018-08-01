import java.io.*;

public class FileUtil {
    public static final String fileName = "lucky.txt";

    public static void writeFile(ConfigInfo configInfo) {
        ObjectOutputStream fop = null;
        File file;
        try {

            file = new File(fileName);
            fop = new ObjectOutputStream(new FileOutputStream(file));

            // if file doesnt exists, then create it
            file.createNewFile();


            fop.writeObject(configInfo);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConfigInfo getConfig() {
        ConfigInfo configInfo = null;
        ObjectInputStream oip = null;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        } else {
            try {
                oip = new ObjectInputStream(new FileInputStream(file));
                configInfo = (ConfigInfo) oip.readObject();
                oip.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oip != null) {
                        oip.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configInfo;
    }
}
