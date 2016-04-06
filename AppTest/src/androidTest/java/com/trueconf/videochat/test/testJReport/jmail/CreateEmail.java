package com.trueconf.videochat.test.testJReport.jmail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEmail {

    private CreateEmail() {

        JZip zip = new JZip();
        String zipSource = zip.init();

        JMail mail = new JMail(message());
        mail.addAttachment(zipSource);
        mail.send();

        zip.deleteZip(zipSource);
    }

    public static CreateEmail instance() {
        return new CreateEmail();
    }

    private String message() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");
        String format = format1.format(new Date());
        return "Report TrueCOnf QA " + format + " android client ver. 1.3.0";
    }
}
