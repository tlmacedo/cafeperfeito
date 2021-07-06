//package br.com.tlmacedo.cafeperfeito.service;
//
//import javafx.embed.swing.SwingFXUtils;
//
//import javafx.scene.image.Image;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Blob;
//import java.sql.SQLException;
//
//public class ServiceEan13 {
//
//	/*
//	-=Structure=-
//|First |First 6|Last 6|
//    0   LLLLLL  RRRRRR
//    1	LLGLGG	RRRRRR
//    2	LLGGLG	RRRRRR
//    3	LLGGGL	RRRRRR
//    4	LGLLGG	RRRRRR
//    5	LGGLLG	RRRRRR
//    6	LGGGLL	RRRRRR
//    7	LGLGLG	RRRRRR
//    8	LGLGGL  RRRRRR
//    9   LGGLGL	RRRRRR
//
//	-=Encodings=-
//|Digit |L-code |G-code |R-code
//    0   0001101 0100111	1110010
//    1	0011001	0110011	1100110
//    2	0010011	0011011	1101100
//    3	0111101	0100001	1000010
//    4	0100011	0011101	1011100
//    5	0110001	0111001	1001110
//    6	0101111	0000101	1010000
//    7	0111011	0010001	1000100
//    8	0110111	0001001	1001000
//    9	0001011	0010111	1110100
//	*/
//
//    static String code;
//    static String path;
//    static BufferedImage bi;
//    static Graphics2D ig2;
//    static int barPos;
//    static int barPosBin;
//    static int imgPixelPos;
//    static int barWidth;
//    static int barHeight;
//    static int imgWidth;
//    static int imgHeight;
//    static int[][] barcodeBinary;
//    static Font font = new Font("Arial", Font.PLAIN, 10);
//
//    int[][] firstSix = {
//            {0, 0, 0, 0, 0, 0},
//            {0, 0, 1, 0, 1, 1},
//            {0, 0, 1, 1, 0, 1},
//            {0, 0, 1, 1, 1, 0},
//            {0, 1, 0, 0, 1, 1},
//            {0, 1, 1, 0, 0, 1},
//            {0, 1, 1, 1, 0, 0},
//            {0, 1, 0, 1, 0, 1},
//            {0, 1, 0, 1, 1, 0},
//            {0, 1, 1, 0, 1, 0}
//    };
//
//    int[] lastSix = {2, 2, 2, 2, 2, 2};
//
//    int[][][] encodings = {
//            {{0, 0, 0, 1, 1, 0, 1},
//                    {0, 1, 0, 0, 1, 1, 1},
//                    {1, 1, 1, 0, 0, 1, 0}},
//            {{0, 0, 1, 1, 0, 0, 1},
//                    {0, 1, 1, 0, 0, 1, 1},
//                    {1, 1, 0, 0, 1, 1, 0}},
//            {{0, 0, 1, 0, 0, 1, 1},
//                    {0, 0, 1, 1, 0, 1, 1},
//                    {1, 1, 0, 1, 1, 0, 0}},
//            {{0, 1, 1, 1, 1, 0, 1},
//                    {0, 1, 0, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 0, 1, 0}},
//            {{0, 1, 0, 0, 0, 1, 1},
//                    {0, 0, 1, 1, 1, 0, 1},
//                    {1, 0, 1, 1, 1, 0, 0}},
//            {{0, 1, 1, 0, 0, 0, 1},
//                    {0, 1, 1, 1, 0, 0, 1},
//                    {1, 0, 0, 1, 1, 1, 0}},
//            {{0, 1, 0, 1, 1, 1, 1},
//                    {0, 0, 0, 0, 1, 0, 1},
//                    {1, 0, 1, 0, 0, 0, 0}},
//            {{0, 1, 1, 1, 0, 1, 1},
//                    {0, 0, 1, 0, 0, 0, 1},
//                    {1, 0, 0, 0, 1, 0, 0}},
//            {{0, 1, 1, 0, 1, 1, 1},
//                    {0, 0, 0, 1, 0, 0, 1},
//                    {1, 0, 0, 1, 0, 0, 0}},
//            {{0, 0, 0, 1, 0, 1, 1},
//                    {0, 0, 1, 0, 1, 1, 1},
//                    {1, 1, 1, 0, 1, 0, 0}}
//    };
//
//    public ServiceEan13(String ean) {
//        this.code = ean.length() < 13 ? String.format("%013d", Long.valueOf(ean)) : ean;
//        this.barPos = 12;
//        this.barPosBin = 7;
//        this.imgPixelPos = 12;
//        this.barcodeBinary = new int[barPos][barPosBin];
//        this.barWidth = 1;
//        this.barHeight = 25;
//        this.imgWidth = ((12 * 7) + (2 * 9) + (2 * 3) + (1 * 5)) * this.barWidth;
//        this.imgHeight = this.barHeight + 15;
//    }
//
//    public Image createBarcodePNG() {
//        if (!ServiceValidarDado.isEan13Valido(this.code)) return null;
////        this.code += Integer.toString(calculateControlDigit(this.code));
//        generateBinaryMap();
//        return generateBarcodePNG();
//    }
//
//    private void generateBinaryMap() {
//        int first = Integer.parseInt(String.valueOf(this.code.charAt(0)));
//
//        //i = 1, first digit is not welcome to the bar
//        for (int i = 1; i < 13; ++i) {
//            int current = Integer.parseInt(String.valueOf(this.code.charAt(i)));
//
//            if (i < 7)
//                this.barcodeBinary[i - 1] = this.encodings[current][this.firstSix[first][i - 1]];
//            else
//                this.barcodeBinary[i - 1] = this.encodings[current][this.lastSix[i - 7]];
//        }
//    }
//
//    public Blob getBlobEAN13() throws IOException, SQLException {
//        return ServiceImageUtil.getBlobFromImage(createBarcodePNG());
//    }
//
//    Image generateBarcodePNG() {
//        bi = new BufferedImage(this.imgWidth, this.imgHeight, BufferedImage.TYPE_INT_ARGB);
//        ig2 = bi.createGraphics();
//
//        ig2.setPaint(Color.WHITE);
//        ig2.fillRect(0, 0, bi.getWidth(), bi.getHeight());
//
//        ig2.setColor(Color.black);
//        ig2.setFont(this.font);
//        addTexto(this.code.substring(0, 1), 5);
//
//        //Draw lead
//        drawSpecial(1);
//
//        ig2.setColor(Color.black);
//        ig2.setFont(this.font);
//        addTexto(this.code.substring(1, 7), 18);
//
//        //Draw first group
//        drawGroup(1);
//
//        //Draw separator
//        drawSpecial(2);
//
//        //Draw second group
//        drawGroup(2);
//        ig2.setColor(Color.black);
//        ig2.setFont(this.font);
//        addTexto(this.code.substring(7), 65);
//
//        //Draw lead
//        drawSpecial(1);
//
//        //Draw quiet zone
//        drawSpecial(0);
//
//        //return SwingFXUtils.toFXImage(bi, null);
//        InputStream inputStream =  bi;
//
//        return new Image(inputStream);
//    }
//
//    void drawGroup(int groupPart) {
//        int i = 0, length = 0;
//        if (groupPart == 1) {
//            i = 0;
//            length = (this.barcodeBinary.length / 2);
//        } else if (groupPart == 2) {
//            i = 6;
//            length = this.barcodeBinary.length;
//        }
//
//        for (; i < length; ++i) {
//            for (int n = 0; n < this.barcodeBinary[i].length; ++n) {
//                if (this.barcodeBinary[i][n] == 0) {
//                    ig2.setPaint(Color.white);
//                    ig2.setStroke(new BasicStroke(this.barWidth));
//                } else {
//                    ig2.setPaint(Color.black);
//                    ig2.setStroke(new BasicStroke(this.barWidth));
//                }
//
//                int pos = this.imgPixelPos;
//                ig2.drawLine(pos, 5, pos, this.barHeight);
//                this.imgPixelPos += this.barWidth;
//            }
//        }
//    }
//
//    void drawSpecial(int type) {
//
//		/*
//		Special Symbol Pattern
//		Quite Zone 	000000000
//		Lead / Trailer 	101
//		Separator 	01010
//		 */
//
//        int[] quiteZone = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int[] leadtrail = {1, 0, 1};
//        int[] separator = {0, 1, 0, 1, 0};
//        int binaryArrLength = 0;
//        int[] arr;
//
//        if (type == 0) {
//            binaryArrLength = quiteZone.length;
//            arr = quiteZone;
//        } else if (type == 1) {
//            binaryArrLength = leadtrail.length;
//            arr = leadtrail;
//        } else {
//            binaryArrLength = separator.length;
//            arr = separator;
//        }
//
//        for (int n = 0; n < binaryArrLength; ++n) {
//            if (arr[n] == 0)
//                ig2.setPaint(Color.white);
//            else
//                ig2.setPaint(Color.black);
//
//            int pos = this.imgPixelPos;
//            ig2.drawLine(pos, 5, pos, this.barHeight + 7);
//            this.imgPixelPos += this.barWidth;
//        }
//    }
//
//    void addTexto(String texto, int x) {
//        ig2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        ig2.drawString(texto, x, this.barHeight + 10);
//    }
//
//
//}