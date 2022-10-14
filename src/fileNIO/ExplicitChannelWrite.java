package fileNIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExplicitChannelWrite {
    public static void main(String[] args) {
        //obtain a channel to afile within a try-with-resources block...
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get("test.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
//            create a buffer
            ByteBuffer mBuf = ByteBuffer.allocate(26);

//            Write some bytes to a buffer
            for (int i = 0; i < 26; i++) {
                mBuf.put((byte) ('A' + i));
            }

//            reset the buffer so that it can be written..
//            biz her defe mBuf.get() Buffer-e byte yazanda hazirki position irelileyir
//            ve ona gore de wrote() metodunu cagirmamisdan evvel rewind () metodunu cagirmaliyiq ki, position-u lap evvele reset elesin ve fayla byte-lari evvelden yaza bilsin
            mBuf.rewind();
//            write the buffer to the output file
            fChan.write(mBuf);

        } catch (InvalidPathException e) {
            System.out.println("Path Error " + e);
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }
}
