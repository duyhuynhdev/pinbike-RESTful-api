package me.pinbike.util.common;

import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class Path {
    private static Path path = null;
    @NotNull
    private final String dbPathRootWindows = "C:\\pinbike2";
    @NotNull
    private final String dbPathRootUNIX = "/pinbike2";
    private final String serverPort = "8080";
    private final String appName = "api";

    private String dataPath, imagePath, logPath, userProfileImgPath;
    private String serverAddress = "";
    private String defaultAvatar = "";

    private Path() {
    }

    public static Path getInstance() throws IOException {
        if (path == null) {
            path = new Path();
            path.buildRoot();
        }
        return path;
    }

    public void buildRoot() throws IOException {
        serverAddress = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + serverPort + "/" + appName;
        String root;
        if (File.separator.equals("\\"))
            root = dbPathRootWindows;
        else
            root = dbPathRootUNIX;
        dataPath = root + File.separator + "data";
        imagePath = root + File.separator + "images";
        userProfileImgPath = imagePath + File.separator + "avatars";
        logPath = dataPath + File.separator + "log";
        File fileData = new File(dataPath);
        File fileImages = new File(imagePath);
        File fileLogos = new File(userProfileImgPath);
        File fileLog = new File(logPath);
        if (!fileData.exists()) {
            fileData.mkdirs();
        }
        if (!fileImages.exists()) {
            fileImages.mkdirs();
        }
        if (!fileLogos.exists()) {
            fileLogos.mkdirs();
        }
        if (!fileLog.exists()) {
            fileLog.mkdirs();
        }
    }

    public String getUrlFromPath(String path) {
        try {
            URL url = new URL(path);
            return url.toString();
        } catch (MalformedURLException e) {
            // it wasn't a URL
            if(getServerAddress().contains("192.168.1.") || getServerAddress().contains("127.0.1.1"))
                return "http://pinride.ddns.net:8080/api"+ (path.replaceAll(" ", "").equals("") ? defaultAvatar : path);
            return getServerAddress() + (path.replaceAll(" ", "").equals("") ? defaultAvatar : path);
        }
    }


    public String[] getUrlsFromPaths(String[] paths) {
        String[] urls = new String[paths.length];
        for (int i = 0; i < paths.length; i++)
            urls[i] = getUrlFromPath(paths[i]);
        return urls;
    }

    public String buildGetNewPasswordUrl(String newPassword, String email){
        return "http://pinride.ddns.net:8080/ChangePasswordFromMailAPI?p="+ new StringUtil().EncryptText(newPassword)+"&a="+ new StringUtil().EncryptText(PinBikeConstant.accesskey)+"&e="+email;
    }

    @NotNull
    public String getDbPathRootWindows() {
        return dbPathRootWindows;
    }

    @NotNull
    public String getDbPathRootUNIX() {
        return dbPathRootUNIX;
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getLogPath() {
        return logPath;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public String getUserProfileImgPath() {
        return userProfileImgPath;
    }

    public void setUserProfileImgPath(String userProfileImgPath) {
        this.userProfileImgPath = userProfileImgPath;
    }
}
