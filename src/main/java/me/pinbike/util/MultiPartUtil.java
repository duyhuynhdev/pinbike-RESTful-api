package me.pinbike.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by hpduy17 on 1/25/16.
 */
public class MultiPartUtil {
    private Logger logger = LogUtil.getLogger(this.getClass());

    public String uploadFile(List<InputPart> inputParts, String path) {
        String fileNames = "";
        for (InputPart inputPart : inputParts) {
            try {
                String fileName;
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                //convert the uploaded file to input stream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                //constructs upload file path
                fileName = path + File.separator + fileName;

                writeFile(bytes, fileName);
                fileNames += fileName;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        return fileNames;
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private String getExtension(final String urlString) {
        try {
            String extension = urlString.substring(
                    urlString.lastIndexOf(".") + 1).trim();
            return extension;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return null;
        }

    }

    private String getIncreasingName(final String urlString, int number) {
        try {
            String newFileName = urlString.substring(0, urlString.lastIndexOf(".")) + number;
            String extension = urlString.substring(
                    urlString.lastIndexOf(".") + 1).trim();
            return newFileName + "." + extension;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return null;
        }

    }

    private void writeFile(byte[] content, String filename) throws IOException {
        String fn = filename;
        File file = new File(fn);
        int idx = 0;
        while (file.exists()) {
            fn = getIncreasingName(filename, ++idx);
            file = new File(fn);
            if (!file.exists()) {
                break;
            }
        }
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }
}
