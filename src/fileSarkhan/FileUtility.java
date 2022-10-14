package fileSarkhan;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {
//FileWriter, FileReader character yazmaq, oxumaq  ucundur....
    private void writeIntoFile(String fileName, String text, boolean append) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(text);
        }
    }

    public void writeIntoFile(String fileName, String text) throws IOException {
        writeIntoFile(fileName, text, false);
    }

    public void appendIntoFile(String fileName, String text) throws IOException {
        writeIntoFile(fileName, text, true);
    }

    public void writeBytes(String fileName, byte[] data) throws IOException {
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();

        System.out.println("done!");
    }

    public void writeObjectToFile(Serializable object, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        }
    }

    public void writeBytesNIO(String fileName, byte[] data) throws IOException {
        Path filePath = Paths.get(fileName);
        Files.write(filePath, data);
    }


    public String read(String fileName) throws IOException {

        try (InputStream in = new FileInputStream(fileName);
             InputStreamReader inreader = new InputStreamReader(in);
             BufferedReader reader = new BufferedReader(inreader)) {
            String line = null;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        }
    }

    public byte[] readBytes(String fileName) throws IOException {
        File file = new File(fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytesArray = new byte[(int) file.length()];
            System.out.println(file.length());


            fis.read(bytesArray);
            return bytesArray;
        }
    }


    public Object readFileDeserialize(String fileName) throws IOException {
        Object object = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            object = ois.readObject();
        } finally {
            return object;
        }
    }


    public byte[] readBytesNIO(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        byte[] byteArray = Files.readAllBytes(filePath);
        return byteArray;
    }

    public void fromURL(String fromFile, String toFile) throws IOException {

        URL url = new URL(fromFile);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        InputStream in = con.getInputStream();
        ReadableByteChannel rbc = Channels.newChannel(in);
        FileOutputStream fos = new FileOutputStream(toFile);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
