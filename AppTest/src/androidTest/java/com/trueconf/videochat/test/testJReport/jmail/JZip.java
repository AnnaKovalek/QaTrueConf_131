package com.trueconf.videochat.test.testJReport.jmail;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JZip {

    private String result;
    private String targetFolder = Environment.getExternalStorageDirectory() + "/Robotium-Screenshots";
    private String sourceFolderZip = Environment.getExternalStorageDirectory() + "/";

    public String init() {
        try {
            File folder = new File(targetFolder);
            if (folder.isDirectory()) {
                String elements[] = folder.list();
                if (elements.length > 0) {
                    result = zipFolder(targetFolder, sourceFolderZip);
                    deleteRecursive(folder);
                }
            }
        } catch (Exception e) {
            Log.d("MyTag", "Exception -> CreateZipArchive >>>> init");
        }
        return result;
    }

    private String zipFolder(String srcFolder, String destZipFile) throws Exception {
        SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");
        String format = format1.format(new Date());
        String result = "report_" + format + ".zip";

        FileOutputStream fileWriter = null;
        ZipOutputStream zip = null;
        try {
            fileWriter = new FileOutputStream(destZipFile + result);
            zip = new ZipOutputStream(fileWriter);
            addFolderToZip("", srcFolder, zip);
        } catch (Exception ignored) {
        } finally {
            if (zip != null) {
                zip.flush();
                zip.close();
            }
            if (zip != null) {
                fileWriter.flush();
                fileWriter.close();
            }
        }

        return result;

    }

    private void addFileToZip(String path, String srcFile, ZipOutputStream zip) {
        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, srcFile, zip);
        } else {
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = null;
            try {
                in = new FileInputStream(srcFile);
                zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                while ((len = in.read(buf)) > 0) {
                    zip.write(buf, 0, len);
                }
            } catch (Exception ignored) {
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) {
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

    public void deleteZip(String zip) {
        File path = new File(Environment.getExternalStorageDirectory() + "/" + zip);

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
