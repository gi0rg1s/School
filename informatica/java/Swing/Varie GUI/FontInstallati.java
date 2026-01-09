import java.awt.*;

    public class FontInstallati {
        public static void main(String args[]) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String list[] = env.getAvailableFontFamilyNames();
            for (int i = 0; i < list.length; i++)
                System.out.println(list[i]);
        }
    }
