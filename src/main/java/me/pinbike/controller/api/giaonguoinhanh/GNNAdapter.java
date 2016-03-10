package me.pinbike.controller.api.giaonguoinhanh;

import me.pinbike.util.common.Path;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by hpduy17 on 3/7/16.
 */
public class GNNAdapter {
    private String customerSaveFilePath = "customer.txt";
    private String employeeSaveFilePath = "employee.txt";
    private String lineContentFormatCustomer = "name: %s\temail: %s\tcity: %s\twant to hire: %s\t\n";
    private String lineContentFormatEmployee = "name: %s\temail: %s\timages: %s\t\n";

    public GNNAdapter() throws IOException {
        this.customerSaveFilePath = Path.getInstance().getDataPath() + File.separator + customerSaveFilePath;
        this.employeeSaveFilePath = Path.getInstance().getDataPath() + File.separator + employeeSaveFilePath;
    }

    public void registerCustomer(String name, String email, String city, String sexOfEmployee) throws IOException {
        appendNewLine(String.format(lineContentFormatCustomer, name, email, city, sexOfEmployee), customerSaveFilePath);
    }

    public void registerEmployee(String name, String email, String[] images) throws IOException {
        appendNewLine(String.format(lineContentFormatEmployee, name, email, Arrays.toString(images)), employeeSaveFilePath);
    }


    private void appendNewLine(String path, String data) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true));
        bufferWriter.write(data);
        bufferWriter.close();
    }
}
