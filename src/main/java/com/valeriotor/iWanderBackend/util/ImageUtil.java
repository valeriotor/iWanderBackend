package com.valeriotor.iWanderBackend.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {

    public static BufferedImage resizeImage(byte[] originalImageBytes, int maxWidth, int maxHeight) throws IOException {
        return resizeImage(bytesToImage(originalImageBytes), maxWidth, maxHeight);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int maxWidth, int maxHeight) throws IOException {
        int[] newDimensions = getNewDimensions(originalImage.getWidth(), originalImage.getHeight(), maxWidth, maxHeight);
        Image resultingImage = originalImage.getScaledInstance(newDimensions[0], newDimensions[1], Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(newDimensions[0], newDimensions[1], BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    private static int[] getNewDimensions(int width, int height, int maxWidth, int maxHeight) {
        double widthRatio = ((double)maxWidth)/width;
        double heightRatio = ((double)maxHeight)/height;
        if(widthRatio > 1 && heightRatio > 1) return new int[] {width, height};
        else if(widthRatio < heightRatio) return new int[] {(int) (width*widthRatio), (int) (height*widthRatio)};
        else return new int[] {(int) (width*heightRatio), (int) (height*heightRatio)};
    }

    public static BufferedImage bytesToImage(byte[] bytes) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(bytes));
    }

    public static byte[] imageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", bos);
        return bos.toByteArray();
    }

}
