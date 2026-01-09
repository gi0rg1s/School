import java.io.File;

    public class Dir {
        public static void main(String args[]) {
            String list[] = new File(".").list();
            for (int i = 0; i < list.length; i++)
                System.out.println(list[i]);
        }
    }
