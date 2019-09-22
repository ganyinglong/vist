package com.gyl.visit.core.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceUtil {

    public static BufferedImage loadImage(String relativePath) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        path = path + relativePath;
        return ImageIO.read(new File(path));
    }
}
