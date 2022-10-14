package image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ImageFilterDemo extends Frame implements ActionListener {

    Image img;
    PlugInFilter pif;
    Image fimg;
    Image curimg;
    LoadedImage lim;
    Label lab;
    Button reset;

    String[] filters = {"Grayscale", "Invert", "Contrast", "Blur", "Sharpen"};

    public ImageFilterDemo() {
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);

        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);

        for (String fstr : filters) {
            Button b = new Button(fstr);
            b.addActionListener(this);
            p.add(b);

        }

        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        try {
            File imageFile = new File("C:\\Users\\itiss\\Desktop\\cafe-terrace.jpg");
            img = ImageIO.read(imageFile);
        } catch (IOException exc) {
            System.out.println("Cannot load image file");
            System.exit(0);
        }

        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        String a = "";

        try {
            a = ae.getActionCommand();
            if (a.equals("Reset")) {
                lim.set(img);
                lab.setText("Normal");
            } else {
                pif = (PlugInFilter) (Class.forName("image."+a)).getConstructor().newInstance();

                fimg = pif.filter(this, img);
                lim.set(fimg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        } catch (ClassNotFoundException e) {
            lab.setText(a + " not Found");
            lim.set(img);
            repaint();
        } catch (InstantiationException e) {
            lab.setText("could not " + a);
        } catch (IllegalAccessException e) {
            lab.setText("no acces: " + a);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            lab.setText("Filter creation error: " + e);
        }
    }

    public static void main(String[] args) {
        ImageFilterDemo appWin = new ImageFilterDemo();
        appWin.setSize(new Dimension(420, 420));
        appWin.setTitle("ImageFilterDemo");
        appWin.setVisible(true);
    }

}


