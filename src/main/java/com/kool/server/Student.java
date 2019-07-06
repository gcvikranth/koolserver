package com.kool.server;

public class Student {

    private Long id;
    private String studentName;
    private String schoolName;
    private String classStudiesIn;

    public Student() {

    }

    public Student(long id, String studentName, String schoolName,String classStudiesIn) {
        super();
        this.setId(id);
        this.setStudentName(studentName);
        this.setSchoolName(schoolName);
        this.setClassStudiesIn(classStudiesIn);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return getStudentName();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
        result = prime * result + ((classStudiesIn == null) ? 0 : classStudiesIn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (schoolName == null) {
            if (other.schoolName != null)
                return false;
        } else if (!studentName.equals(other.getStudentName()))
            return false;
        if (getId() == null) {
            if (other.getId()!= null)
                return false;
        } else if (!getId().equals(other.id))
            return false;
        if (studentName == null) {
            if (other.getStudentName() != null)
                return false;
        } else if (!studentName.equals(other.getStudentName()))
            return false;
        return true;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassStudiesIn() {
        return classStudiesIn;
    }

    public void setClassStudiesIn(String classStudiesIn) {
        this.classStudiesIn = classStudiesIn;
    }
}



