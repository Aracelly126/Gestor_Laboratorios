/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;

public class PanelShadow extends JPanel {

    public PanelShadow() {
        setOpaque(false);
    }

    private final String[] OrientacionSombra = {"CENTER", "TOP_RIGHT", "TOP_LEFT", "BOT_RIGHT", "BOT_LEFT", "BOT", "TOP"};
    private String shadowType = OrientacionSombra[0];
    private int shadowSize = 7;
    private Color shadowColor = Color.BLACK;
    private float shadowOpacity = 0.5f;

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
        super.paintComponent(grphcs);
    }

    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        int width = getWidth() - size;
        int height = getHeight() - size;

        switch (shadowType) {
            case "TOP":
                x = shadowSize;
                y = size;
                break;
            case "BOT":
                x = shadowSize;
                y = 0;
                break;
            case "TOP_LEFT":
                x = size;
                y = size;
                break;
            case "TOP_RIGHT":
                x = 0;
                y = size;
                break;
            case "BOT_LEFT":
                x = size;
                y = 0;
                break;
            case "BOT_RIGHT":
                x = 0;
                y = 0;
                break;
            default:
                // CENTER
                x = shadowSize;
                y = shadowSize;
                break;
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(getBackground());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width, height, 10, 10);

        g.dispose();

        // Create Shadow
        BufferedImage shadow = createShadow(img);
        g2.drawImage(shadow, 0, 0, null);
        g2.drawImage(img, x, y, null);
    }

    private BufferedImage createShadow(final BufferedImage image) {
        int shadowSize = this.shadowSize * 2;

        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();

        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;

        int left = this.shadowSize;
        int right = shadowSize - left;

        int yStop = dstHeight - right;

        int shadowRgb = shadowColor.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int historyIdx;

        int aSum;

        BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);

        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];

        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);

        int lastPixelOffset = right * dstWidth;
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = shadowOpacity / shadowSize;

        int[] hSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }

        int[] vSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        int srcOffset;

        // Horizontal pass
        for (int srcY = 0, dstOffset = left * dstWidth; srcY < srcHeight; srcY++) {

            // First pixels are empty
            for (historyIdx = 0; historyIdx < shadowSize;) {
                aHistory[historyIdx++] = 0;
            }

            aSum = 0;
            historyIdx = 0;
            srcOffset = srcY * srcWidth;

            // Compute the blur average with pixels from the source image
            for (int srcX = 0; srcX < srcWidth; srcX++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;   // Store the alpha value only

                aSum -= aHistory[historyIdx]; // Subtract the oldest pixel from the sum

                // Extract the new pixel ...
                a = srcBuffer[srcOffset + srcX] >>> 24;
                aHistory[historyIdx] = a;   // ... and store its value into history
                aSum += a;                  // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // Blur the end of the row - no new pixels to grab
            for (int i = 0; i < shadowSize; i++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;

                // Subtract the oldest pixel from the sum ... and nothing new to add!
                aSum -= aHistory[historyIdx];

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        // Vertical pass
        for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {

            aSum = 0;

            // First pixels are empty
            for (historyIdx = 0; historyIdx < left;) {
                aHistory[historyIdx++] = 0;
            }

            // And then they come from the dstBuffer
            for (int y = 0; y < right; y++, bufferOffset += dstWidth) {
                int a = dstBuffer[bufferOffset] >>> 24;         // Extract alpha
                aHistory[historyIdx++] = a;                     // Store into history
                aSum += a;                                      // And add to sum
            }

            bufferOffset = x;
            historyIdx = 0;

            // Compute the blur average with pixels from the previous pass
            for (int y = 0; y < yStop; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;  // Store alpha value + shadow color

                aSum -= aHistory[historyIdx];   // Subtract the oldest pixel from the sum

                a = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;   // Extract the new pixel ...
                aHistory[historyIdx] = a;                               // ... and store its value into history
                aSum += a;                                              // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // Blur the end of the column - no pixels to grab anymore
            for (int y = yStop; y < dstHeight; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;

                aSum -= aHistory[historyIdx];   // Subtract the oldest pixel from the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }
}

class GraphicsUtilities {

    public static void getPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
        img.getRGB(x, y, w, h, pixels, 0, w);
    }

    public static void setPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
        img.setRGB(x, y, w, h, pixels, 0, w);
    }
}
