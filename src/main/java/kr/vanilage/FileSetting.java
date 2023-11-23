package kr.vanilage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileSetting {
    private static void downloadPaper(String downloadUrl, String downloadDir) {
        try {
            URL url = new URL(downloadUrl);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());

            File downloadFile = new File(downloadDir, "server.jar");

            FileOutputStream fos = new FileOutputStream(downloadFile);

            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            fos.close();

            System.out.printf("%s에서 파일이 정상적으로 다운로드 되었습니다.", downloadDir);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void settingServerStarter(String downloadDir, int memory, boolean nogui) {
        try {
            File file = new File(downloadDir + "/run.sh");
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);

            pw.printf("java -Xms%dM -Xmx%dM --add-modules=jdk.incubator.vector -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -jar server.jar", memory, memory);
            if (nogui) pw.print(" nogui");

            pw.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void settingEula(String downloadDir) {
        try {
            File file = new File(downloadDir + "\\eula.txt");
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);

            pw.print("eula=true");

            pw.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void settingAllFiles(int memory, String version, boolean nogui, String dir) {
        downloadPaper(VersionSetting.getUrl(version), dir);
        settingServerStarter(dir, memory, nogui);
        settingEula(dir);
    }
}
