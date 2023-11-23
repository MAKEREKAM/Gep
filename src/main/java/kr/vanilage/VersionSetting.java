package kr.vanilage;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class VersionSetting {
    public static String getUrl(String version) {
        try {
            InputStream is = Main.class.getResourceAsStream("/paper-versions.json");
            Scanner sc = new Scanner(is);
            String json = sc.useDelimiter("\\A").next();
            JSONObject jsonObject = new JSONObject(json);

            return jsonObject.getString(version);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
