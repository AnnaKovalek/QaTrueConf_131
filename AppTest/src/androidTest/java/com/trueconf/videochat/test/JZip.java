package com.trueconf.videochat.test;

import android.os.Environment;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by anna on 24.02.16.
 */
public class JZip {

    private String result;
    private String targetFolder= Environment.getExternalStorageDirectory() + "/Robotium-Screenshots";
    private String sourceFolderZip= Environment.getExternalStorageDirectory() + "/";

    public String init() {
        try {
            File folder = new File(targetFolder);
            if (folder.isDirectory()) {
                String elements[] = folder.list();
                if (elements.length > 0) {
                    result = zipFolder(targetFolder, sourceFolderZip);
                }
            }
        } catch (Exception e) {
            //Log.d("MyTag", "Exception -> CreateZipArchive >>>> init")
        }
        return result;
    }

    private String zipFolder(String srcFolder, String destZipFile) throws Exception {
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");
        String format = format1.format(date);
        String result = destZipFile + "report_" + format + ".zip";

        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;
        fileWriter = new FileOutputStream(result);
        zip = new ZipOutputStream(fileWriter);
        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
        return result;
    }

    private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
            throws Exception {
        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(srcFile);
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((len = in.read(buf)) > 0) {
                zip.write(buf, 0, len);
            }
        }
    }

    private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
            throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
            }
        }
    }

    private void deleteRecursive(File path) {
        path.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    pathname.listFiles(this);
                    pathname.delete();
                } else {
                    pathname.delete();
                }
                return false;
            }
        });
        path.delete();
    }

}
