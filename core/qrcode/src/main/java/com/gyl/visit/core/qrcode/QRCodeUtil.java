package com.gyl.visit.core.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QRCodeUtil {

    /**
     * 创建二维码
     *
     * @param qrLoad    二维码载荷
     * @param width     宽度
     * @param height    高度
     * @param centerImg 二维码中间的图片
     * @param rgb       二维码颜色rgb码
     * @return
     */
    public static BufferedImage createQRCode(String qrLoad, int width, int height, BufferedImage centerImg, int rgb) {
        float proportion = 0.22F;
        QRCodeWriter qcWriter = new QRCodeWriter();
        Hashtable<EncodeHintType, Object> hint = new Hashtable<EncodeHintType, Object>();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hint.put(EncodeHintType.MARGIN, 0);//0-4
        BitMatrix bm = null;
        try {
            bm = qcWriter.encode(qrLoad, BarcodeFormat.QR_CODE, width, height, hint);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        //生成二维码
        BufferedImage qrCode = bitMatrixToBufferedImage(bm, rgb);
        if (centerImg == null) {
            return qrCode;
        }
        //将图像缩小为比例尺寸,5p白边
        int margin = 8;
        int w = Math.round(proportion * width);
        int h = Math.round(proportion * height);
        BufferedImage proportionImg = addWhiteRound(reSizeImage(centerImg, w - (margin << 1), h - (margin << 1)), margin);

        //中间图片起始位置
        int xstart = Math.round((1 - proportion) * width) >> 1;
        int ystart = Math.round((1 - proportion) * height) >> 1;

        Graphics2D grGraphics = (Graphics2D) qrCode.getGraphics();
        grGraphics.drawImage(proportionImg, xstart, ystart, null);
        grGraphics.setColor(Color.gray);
        grGraphics.drawRoundRect(xstart + 3, ystart + 3, w - 6, h - 6, 20, 20);
        return qrCode;
    }

    /**
     * 添加白边
     *
     * @return
     */
    public static BufferedImage addWhiteRound(BufferedImage image, int margin) {
        int width = image.getWidth() + (margin << 1);
        int height = image.getHeight() + (margin << 1);
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) newImg.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setClip(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        graphics.drawImage(image, margin, margin, null);
        return newImg;
    }

    /**
     * 改变图片的大小
     *
     * @param img
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage reSizeImage(BufferedImage img, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setClip(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        graphics.drawImage(img, 0, 0, width, height, null);
        return newImage;
    }

    public static BufferedImage bitMatrixToBufferedImage(BitMatrix bitMatrix, int color) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? color : Color.white.getRGB());
            }
        }
        return image;
    }


}
