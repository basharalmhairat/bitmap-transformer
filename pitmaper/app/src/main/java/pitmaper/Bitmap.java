package pitmaper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Bitmap {
    private final String FileName;
    private final String newFileName;


    public Bitmap(String FileName, String newFileName, String transformation) {

        this.FileName = "./src/main/resources" + FileName + ".bmp";
        this.newFileName = "./src/main/resources" + newFileName + ".bmp";
        switch (transformation) {
            case "BlackWhite" -> BlackWhite();
            case "flip" -> flip();
            case "reduce" -> reduce();
            default -> System.out.println("type error: Please use one of these method BlackWhite, flip, or reduce.");
        }
    }

    public void copyFile(String src, String dest) throws IOException{
        File source = new File(src);
        File destination = new File(dest);
        Files.copy(source.toPath(), destination.toPath());
    }

    public void BlackWhite(){
        try {
            File input = new File(FileName);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            graphic.dispose();

            File output = new File(newFileName);
            ImageIO.write(result, "bmp", output);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void flip(){
        try {
            File input = new File(FileName);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image,0,image.getHeight(),image.getWidth(),0,0,0, image.getWidth(),image.getHeight(),null);
            graphic.dispose();

            File output = new File(newFileName);
            ImageIO.write(result, "bmp", output);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void reduce(){
        try {
            File input = new File(FileName);
            BufferedImage image = ImageIO.read(input);

            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0,  0, image.getWidth()/2, image.getHeight()/2,  null);
            graphic.dispose();

            File output = new File(newFileName);
            ImageIO.write(result, "bmp", output);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}