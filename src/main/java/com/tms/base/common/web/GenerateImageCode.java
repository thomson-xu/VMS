package com.tms.author.base.common.web;

/**
 * Created by Administrator on 2016/8/9.
 */

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Random;

public class GenerateImageCode {
    private static String code = null;
    private ByteArrayInputStream inputStream;

    public GenerateImageCode() {
    }

    public static void generateRandomCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte width = 60;
        byte height = 22;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", 0, 20));
        g.setColor(getRandColor(160, 200));

        int session;
        for(int sRand = 0; sRand < 180; ++sRand) {
            int s = random.nextInt(width);
            int names = random.nextInt(height);
            session = random.nextInt(12);
            int rand = random.nextInt(12);
            g.drawLine(s, names, s + session, names + rand);
        }

        String var12 = "";
        String var13 = "1234567890";
        String[] var14 = new String[]{"Times New Roman", "Courier", "Arial"};

        for(session = 0; session < 4; ++session) {
            String var16 = String.valueOf(var13.charAt(random.nextInt(var13.length())));
            var12 = var12 + var16;
            g.setColor(new Color(random.nextInt(110), random.nextInt(110), random.nextInt(110)));
            g.setFont(new Font(var14[random.nextInt(var14.length)], 0, 20));
            g.drawString(var16, 13 * session + 6, 16);
        }

        code = var12;
        HttpSession var15 = request.getSession();
        var15.setAttribute("code", code);
        g.dispose();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("Expires", 0L);
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    public static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public ByteArrayInputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
