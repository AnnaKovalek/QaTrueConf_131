package com.trueconf.videochat.test.testJReport.model;

import java.util.LinkedList;
import java.util.List;

public class TestEntity extends BaseEntity {
    private String name;
    private String title;
    private List<String> stagesOfTest;
    private List<TestCaseEntity> testCases;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStagesOfTest(List<String> stagesOfTest) {
        this.stagesOfTest = stagesOfTest;
    }

    public TestEntity() {
        this.testCases = new LinkedList<TestCaseEntity>();
        this.stagesOfTest = new LinkedList<String>();
    }

    public void addTest(TestCaseEntity test) {
        this.testCases.add(test);
    }

    public List<String> getStagesOfTest() {
        return stagesOfTest;
    }

    public void addStagesOfTest(String stages) {
        this.stagesOfTest.add(stages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestCaseEntity> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseEntity> testCases) {
        this.testCases = testCases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity test = (TestEntity) o;
        return !(name != null ? !name.equals(test.name) : test.name != null) && !(title != null ? !title.equals(test.title) : test.title != null) && !(stagesOfTest != null ? !stagesOfTest.equals(test.stagesOfTest) : test.stagesOfTest != null) && !(testCases != null ? !testCases.equals(test.testCases) : test.testCases != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (stagesOfTest != null ? stagesOfTest.hashCode() : 0);
        result = 31 * result + (testCases != null ? testCases.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", stagesOfTest=" + stagesOfTest +
                ", testCases=" + testCases +
                '}';
    }
}
