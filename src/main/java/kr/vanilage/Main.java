package kr.vanilage;

public class Main {
    public static void main(String[] args) {
        if (args.length == 3) {
            int memory = Integer.parseInt(args[0]);
            String version = args[1];
            boolean nogui = Boolean.parseBoolean(args[2]);
            String dir = System.getProperty("user.dir");

            FileSetting.settingAllFiles(memory, version, nogui, dir);
        }

        if (args.length == 4) {
            int memory = Integer.parseInt(args[0]);
            String version = args[1];
            boolean nogui = Boolean.parseBoolean(args[2]);
            String dir = args[3];

            FileSetting.settingAllFiles(memory, version, nogui, dir);
        }
    }
}