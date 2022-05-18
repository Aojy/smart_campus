package com.ojy.smart_campus.uitl;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @project: smart_campus
 * @description: 绘制验证码图片
 */
public class CreateVerIficationCodeImage {

    // 图片宽度
    private static int WIDTH = 90;
    // 图片高度
    private static int HEIGHT= 35;
    // 字符大小
    private static int FONT_SIZE = 20;
    // 验证码
    private static char[] verificationCode;
    // 验证码图片
    private static BufferedImage verificationCodeImage;

    /**
     * 获取验证码图片
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getVerificationCodeImage() {
        // 获取图片对象
        verificationCodeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 通过图片对象获取Graphics对象
        Graphics graphics = verificationCodeImage.getGraphics();
        // 获取验证码
        verificationCode = generateCheckCode();
        // 修改Graphics背景
        drawBackground(graphics);
        // 将验证码和Graphics组合
        drawRands(graphics, verificationCode);

        graphics.dispose();
        return verificationCodeImage;
    }

    /**
     * 获取随机生成的颜色
     * @return Color颜色
     */
    private static Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(220), random.nextInt(220), random.nextInt(220));
    }

    /**
     * 绘制图片背景
     * @param graphics
     */
    private static void drawBackground(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制验证码干扰点
        int i = 0;
        while (i < 200) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            graphics.setColor(getRandomColor());
            graphics.drawOval(x, y, 1, 1);
            ++i;
        }
    }

    /**
     * 绘制验证码
     * @param graphics
     * @param rands
     */
    private static void drawRands(Graphics graphics, char[] rands) {
        graphics.setFont(new Font("Console", Font.BOLD, FONT_SIZE));

        int i = 0;
        while (i < rands.length) {
            graphics.setColor(getRandomColor());
            graphics.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
            ++i;
        }

    }

    /**
     * 获取随机生成的验证码
     * @return
     */
    private static char[] generateCheckCode() {
        String chars = "0123456789" + "abcdefghijklnmopqrstuvwsyz" + "ABCDEFGHIJKLNMOPQRSTUVWSYZ";
        char[] rands = new char[4];

        int i = 0;
        while (i < 4) {
            int rand = (int) (Math.random() * (10 + (26 * 2)));
            rands[i] = chars.charAt(rand);
            ++i;
        }

        return rands;
    }

    /**
     * 获取验证码
     * @return
     */
    public static char[] getVerificationCode() {
        return verificationCode;
    }
}
