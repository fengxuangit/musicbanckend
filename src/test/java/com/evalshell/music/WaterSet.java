package com.evalshell.music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class WaterSet {

    public static void main(String[] args) throws IOException, FontFormatException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/fengxuan/Downloads/PingFang_changgui_jb51/PingFang_SC_Regular.ttf"));
//        font = new Font("宋体", Font.PLAIN, 20);
        Font font1 = font.deriveFont(Font.PLAIN, 80f);
        Color color = new Color(255,255,255);
        addWatermark("/Users/fengxuan/Downloads/bg.png", "/Users/fengxuan/Downloads/out.png", font1, color, "05", 50, 800);
        font1 = font.deriveFont(Font.PLAIN, 30f);
        addWatermark("/Users/fengxuan/Downloads/out.png", "/Users/fengxuan/Downloads/out.png", font1, color, "oct 2021", 50, 850);
        font1 = font.deriveFont(Font.BOLD, 35f);
        addWatermark("/Users/fengxuan/Downloads/out.png", "/Users/fengxuan/Downloads/out.png", font1, color, "因为项目中考虑到添加图片版权的保护，特意看了下水印的处理以下有两种方式。", 50, 950);

        font1 = font.deriveFont(Font.PLAIN, 30f);
        addWatermark("/Users/fengxuan/Downloads/out.png", "/Users/fengxuan/Downloads/out.png", font1, color, "历史学家，何永杰", 50, 1150);
        markImgMark("/Users/fengxuan/Downloads/gh_d3e6c59e4b6a_258.jpg", "/Users/fengxuan/Downloads/out.png", "/Users/fengxuan/Downloads/out.png");
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
        g.drawImage(con, 600, 1200, null);//水印的位置
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
}
