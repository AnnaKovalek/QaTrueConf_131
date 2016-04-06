package com.trueconf.videochat.test.testJReport.model;

public class TestCaseEntity extends BaseEntity  {

    private String title;
    private String image;
    private String errorMessage = "No error";
    private boolean successfully;
    private int time;

    public TestCaseEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(boolean successfully) {
        this.successfully = successfully;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseEntity testCase = (TestCaseEntity) o;
        return successfully == testCase.successfully && time == testCase.time && !(title != null ? !title.equals(testCase.title) : testCase.title != null) && !(image != null ? !image.equals(testCase.image) : testCase.image != null) && !(errorMessage != null ? !errorMessage.equals(testCase.errorMessage) : testCase.errorMessage != null);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (successfully ? 1 : 0);
        result = 31 * result + time;
        return result;
    }

    @Override
    public String toString() {
        return "TestCaseEntity{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", successfully=" + successfully +
                ", time=" + time +
                '}';
    }
}
