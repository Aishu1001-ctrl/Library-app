package com.example.library_app_java;

public class Members {
    String MemberID;
    String MemberName;
    String MemberDOB;
    String MemberEmail;
    String MemberGender;
    String MemberStatus;

    public String getMemberID() {
        return MemberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public String getMemberDOB() {
        return MemberDOB;
    }

    public String getMemberEmail() {
        return MemberEmail;
    }

    public String getMemberGender() {
        return MemberGender;
    }

    public String getMemberStatus() {
        return MemberStatus;
    }

    public Members(String memberID, String memberName, String memberDOB, String memberEmail, String memberGender, String memberStatus) {
        MemberID = memberID;
        MemberName = memberName;
        MemberDOB = memberDOB;
        MemberEmail = memberEmail;
        MemberGender = memberGender;
        MemberStatus = memberStatus;


    }
}
