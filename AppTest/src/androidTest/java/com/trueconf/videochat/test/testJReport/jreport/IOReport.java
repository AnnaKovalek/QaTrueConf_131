package com.trueconf.videochat.test.testJReport.jreport;

import android.os.Environment;

import com.trueconf.videochat.test.testJReport.model.TestCaseEntity;
import com.trueconf.videochat.test.testJReport.model.TestEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IOReport extends ResourcesReport {
    private File fileDestination;
    private StringBuilder stringBuilder;
    private String format;

    public IOReport() {
        this.stringBuilder = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy_hh:mm");
        format = format1.format(date);
        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format);
        fileDestination.mkdirs();
        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/img");
        fileDestination.mkdirs();

        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/index.html");
        try {
            fileDestination.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void destroy(TestEntity test, String errorMesage) {
        this.stringBuilder.append(index_01);
        this.stringBuilder.append("  <b> <a href=\"index.html\" class=\"list-group-item\">" + test.getName() + "</a> </b>");
        this.stringBuilder.append(index_02);
        this.stringBuilder.append("<h3>TestEntity:  " + test.getName() + "</h3> <h4>Data:");
        this.stringBuilder.append(format + "</h4>");
        this.stringBuilder.append(" <p>  " + test.getTitle() + " </p> <p>Этапы теста</p> <ol>");
        List<String> stagesOfTest = test.getStagesOfTest();
        for (String iter : stagesOfTest) {
            this.stringBuilder.append("<li>" + iter + "</li>");
        }
        this.stringBuilder.append("</ol> </div> <div class=\"col-xs-8\">");
        List<TestCaseEntity> testCases = test.getTestCases();
        for (TestCaseEntity iter : testCases) {

            this.stringBuilder.append("<div class=\"thumbnail\">  <div class=\"caption\">");
            this.stringBuilder.append("<h4>" + iter.getId() + " " + iter.getTitle() + "</h4>");
            this.stringBuilder.append("<img class=\"img-thumbnail\" height=\"400\" width=\"250\"  src=\"img/" + iter.getImage() + ".jpg" + "\" alt=\"\">");


            this.stringBuilder.append("<h5><b>Error Message : " + iter.getErrorMessage() + " </b></h5>");
            this.stringBuilder.append("<h5><b>Successfully : " + iter.getSuccessfully() + " </b></h5>");
            this.stringBuilder.append(" <h5><b>Time : 3c </b></h5>");
            this.stringBuilder.append("</div> </div>");
        }

        if (errorMesage != null) {
            this.stringBuilder.append(index_error_begin);
            this.stringBuilder.append("<span class=\"colortext\"> <h4> Exception: </h4></span>");
            this.stringBuilder.append("<span class=\"colortext\"><b>" + errorMesage + "</b></span>");
            this.stringBuilder.append(index_error_end);

        }

        this.stringBuilder.append(index_03);

        PrintWriter out = null;
        try {
            out = new PrintWriter(fileDestination.getAbsoluteFile());
            out.print(stringBuilder.toString());
        } catch (IOException ignored) {
        }finally {
            if(out!=null) {
                out.flush();
                out.close();
            }
        }

        for (TestCaseEntity iter : testCases) {
            copy(iter.getImage());
        }
        deleteImage();
    }

    private void copy(String image) {
        FileChannel sourceChannel = null;
        FileChannel destChannel= null;
        try {
            File source = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/" + image + ".jpg");
            File dest = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/img/" + image + ".jpg");

            fileDestination.createNewFile();

           sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            source.delete();



        } catch (Exception ignored) {
        } finally {
            try {
                if(sourceChannel!=null)
                sourceChannel.close();

                if(destChannel!=null)
                destChannel.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void deleteImage() {
        try {
            File source = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/init.jpg");
            source.delete();
        } catch (Exception ignored) {

        }
    }
}
