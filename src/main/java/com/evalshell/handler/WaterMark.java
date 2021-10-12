package com.evalshell.handler;

import com.evalshell.service.impl.HomeDaoImpl;
import com.evalshell.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Configuration
//@EnableScheduling
public class WaterMark {

    @Autowired
    HomeDaoImpl homeService;

    public static void main(String[] args) throws Exception {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        mark();
    }

    public static String path = "/Users/fengxuan/Downloads/";

    @Scheduled(cron="*/10 * * * * *")
    public void test(){

    }

//    @Scheduled(cron="0 * */1 * * *")
    public static void mark() throws  Exception{
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path+"fonts/PingFang_SC_Regular.ttf"));
//        font = new Font("宋体", Font.PLAIN, 20);
        Font font1 = font.deriveFont(Font.PLAIN, 80f);
        Color color = new Color(255,255,255);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String newname = df.format(new Date()) + ".png";// new Date()为获取当前系统时间
        String newpath = path + newname; 
        addWatermark(path + "bg.png", newpath, font1, color, "05", 50, 800);
        font1 = font.deriveFont(Font.PLAIN, 30f);
        addWatermark(newpath, path + newname, font1, color, "oct 2021", 50, 850);
        font1 = font.deriveFont(Font.BOLD, 35f);
        addWatermark(newpath, newpath, font1, color, "因为项目中考虑到添加图片版权的保护，特意看了下水印的处理以下有两种方式", 50, 950);

        font1 = font.deriveFont(Font.PLAIN, 30f);
        addWatermark(newpath, newpath, font1, color, "历史学家，何永杰22", 50, 1150);
        markImgMark(path + "gh_2.jpg", newpath, newpath);

        try {
            QiniuUtil.uploadFile(newpath, "image/calendar/" + newname);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("http://music.gitsort.com/image/calendar/" + newname);

    }


    public static String markImgMark(String watermarkUrl, String source, String output) throws IOException {
        String result = "添加图片水印出错";
        File file = new File(source);
        Image img = ImageIO.read(file);
        int width = img.getWidth(null);//水印宽度
        int height = img.getHeight(null);//水印高
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIcon imgIcon = new ImageIcon(watermarkUrl);
        Image con = imgIcon.getImage();
        float clarity = 1;//透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clarity));
        g.drawImage(con, 550, 1150, null);//水印的位置
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g.dispose();
        File sf = new File(output);
        ImageIO.write(bi, "png", sf); // 保存图片
        System.out.println("添加图片水印成功");
        return result;
    }

    /** 设置文字水印
     * @param sourceImg 源图片路径
     * @param targetImg 保存的图片路径
     * @throws IOException
     */
    public static void addWatermark(String sourceImg, String targetImg, Font font, Color color,String text, Integer posix, Integer posiy) throws IOException {
        try {
            // 读取原图片信息
            File srcImgFile = new File(sourceImg);
            Image srcImg = ImageIO.read(srcImgFile);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth,
                    srcImgHeight,
                    BufferedImage.TYPE_INT_RGB);
            //获取 Graphics2D 对象
            Graphics2D g = bufImg.createGraphics();
            //设置绘图区域
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            //设置字体
//            Font font = new Font("宋体", Font.PLAIN, 20);

            // 根据图片的背景设置水印颜色
            g.setColor(color);
            g.setFont(font);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //获取文字长度
            int end = 0;
            if (text.length() > 18){
                for (int i = 0; i < text.length(); i+=18){
                    System.out.println(i);
                    end = i + 18;
                    if (end > text.length()){
                        end = text.length();
                    }
                    System.out.println(text.substring(i, end));
                    g.drawString(text.substring(i, end), posix, posiy);
                    posiy += 70;
                }
            }else{
                g.drawString(text, posix, posiy);
            }
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(targetImg);
            ImageIO.write(bufImg, "png", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param srcImg     原图片
     * @param destImg    目标位置
     * @param width      期望宽
     * @param height     期望高
     * @param equalScale 是否等比例缩放
     */
    public static void reSize(File srcImg, File destImg, int width,
                              int height, boolean equalScale) {
        String type = getImageType(srcImg);
        if (type == null) {
            return;
        }
        if (width < 0 || height < 0) {
            return;
        }

        BufferedImage srcImage = null;
        try {
            srcImage = ImageIO.read(srcImg);
            System.out.println("srcImg size=" + srcImage.getWidth() + "X" + srcImage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (srcImage != null) {
            // targetW，targetH分别表示目标长和宽
            BufferedImage target = null;
            double sx = (double) width / srcImage.getWidth();
            double sy = (double) height / srcImage.getHeight();
            // 等比缩放
            if (equalScale) {
                if (sx > sy) {
                    sx = sy;
                    width = (int) (sx * srcImage.getWidth());
                } else {
                    sy = sx;
                    height = (int) (sy * srcImage.getHeight());
                }
            }
            System.out.println("destImg size=" + width + "X" + height);
            ColorModel cm = srcImage.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();

            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
            Graphics2D g = target.createGraphics();
            // smoother than exlax:
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.drawRenderedImage(srcImage, AffineTransform.getScaleInstance(sx, sy));
            g.dispose();
            // 将转换后的图片保存
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(target, type, baos);
                FileOutputStream fos = new FileOutputStream(destImg);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件后缀不带.
     *
     * @param file 文件后缀名
     * @return
     */
    private static String getImageType(File file) {
        if (file != null && file.exists() && file.isFile()) {
            String fileName = file.getName();
            int index = fileName.lastIndexOf(".");
            if (index != -1 && index < fileName.length() - 1) {
                return fileName.substring(index + 1);
            }
        }
        return null;
    }
}
