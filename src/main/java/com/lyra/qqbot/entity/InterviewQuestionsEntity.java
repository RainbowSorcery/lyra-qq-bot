package com.lyra.qqbot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("interview_questions")
public class InterviewQuestionsEntity {
    @TableId
    private Long id;
    private String question;
    private String answer;
    private LocalDateTime createDate;

    private Long printCount;

    @Override
    public String toString() {
        return "InterviewQuestionsEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public Long getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Long printCount) {
        this.printCount = printCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
