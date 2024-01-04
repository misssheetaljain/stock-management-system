/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emart.pojo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.krysalis.barcode4j.impl.code128.code128Bean;

/**
 *
 * @author Lenovo
 */
public class Barcode_img_Generator {

    public static String createImage(String image, String myString) {
        try{
        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream():
        BitMapCanvasProvider canvas = new bitMapCanvasProvider(baos,image/x_png,300,BufferedImage.TYPE_BYTE_BINARY,false,0);
        code128.generateBarcode(canvas,myString);
        canvas.finish();
        String userdir = System.getProperty("user.dir");
        System.out.println("userdir is : " + userdir);
        FileOutputStream fos = new FileOutputStream(userdir+"\\Barcode\\" + image_name);
        for.flush();
        fos.close();
        
        }
        catch(Exception e){
             
        }
    }
}
