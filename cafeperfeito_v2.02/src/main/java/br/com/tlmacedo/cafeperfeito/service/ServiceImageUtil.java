//package br.com.tlmacedo.cafeperfeito.service;
//
////import com.pnuema.java.barcode.Barcode;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.oned.EAN13Writer;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.image.Image;
//
//import javax.imageio.ImageIO;
//import javax.sql.rowset.serial.SerialBlob;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.URL;
//import java.sql.Blob;
//import java.sql.SQLException;
//
////import net.sourceforge.barbecue.Barcode;
////import net.sourceforge.barbecue.BarcodeFactory;
////import net.sourceforge.barbecue.BarcodeImageHandler;
////import net.sourceforge.barbecue.output.OutputException;
//
//public class ServiceImageUtil {
//
//    public static Image getImagemFromUrl(String strUrl) {
//        Image image = null;
//        try {
//            URL url = new URL(strUrl);
//            image = SwingFXUtils.toFXImage(ImageIO.read(url), null);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return image;
//    }
//
//    public static InputStream getInputStreamFromImage(Image image) {
//        if (image == null)
//            return null;
//        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(bufferedImage, "png", outputStream);
//            byte[] res = outputStream.toByteArray();
//            return new ByteArrayInputStream(res);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Blob getBlobFromImage(Image image) throws IOException, SQLException {
//        return new SerialBlob(getInputStreamFromImage(image).readAllBytes());
//    }
//
//    public static Blob getBobFromImage(Image image, int width, int height) throws IOException, SQLException {
//        return new SerialBlob(getInputStreamFromImage(getImageResized(image, width, height)).readAllBytes());
//    }
//
//    public static Blob getBobFromImage(File fileImage, int width, int height) throws IOException, SQLException {
//        return new SerialBlob(getInputStreamFromImage(getImageResized(new Image(new FileInputStream(fileImage)), width, height)).readAllBytes());
//    }
//
//    public static Image getImageFromInputStream(InputStream inputStream) {
//        if (inputStream == null)
//            return null;
//        try {
//            return SwingFXUtils.toFXImage(ImageIO.read(inputStream), null);
//        } catch (Exception ex) {
//            if (!(ex instanceof NullPointerException))
//                ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws WriterException {
//        EAN13Writer barcodeWriter = new EAN13Writer();
//        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 200, 2);
//
//        return MatrixToImageWriter.toBufferedImage(bitMatrix);
//    }
//
//    public static Image getImageCodBarrasEAN13(String barcodeText) throws WriterException {
//        try {
//            return SwingFXUtils.toFXImage(generateEAN13BarcodeImage(barcodeText), null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
////        Barcode barcode = null;
////        try {
////            barcode = BarcodeFactory.createEAN13(busca.substring(0, 12));
//////            barcode.setBarHeight(2);
//////            barcode.setBarWidth(200);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        try {
////            return SwingFXUtils.toFXImage(BarcodeImageHandler.getImage(barcode), null);
////        } catch (OutputException e) {
////            e.printStackTrace();
////        }
////        return null;
//    }
//
//    public static Image getImageResized(final Image image, int width, int height) {
//        BufferedImage inputImage = SwingFXUtils.fromFXImage(image, null);
//        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
//        Graphics2D g2d = outputImage.createGraphics();
//        g2d.drawImage(inputImage, 0, 0, width, height, null);
//        g2d.dispose();
//        return SwingFXUtils.toFXImage(outputImage, null);
//    }
//
//}
