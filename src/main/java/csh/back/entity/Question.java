package csh.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "question", fetch = EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifiyDate;

    public Answer addAnswer(String answer) {
        Answer a = new Answer();
        a.setContent(answer);
        a.setQuestion(this);
        a.setCreateDate(LocalDateTime.now());
        answers.add(a);
        return a;
    }
}
