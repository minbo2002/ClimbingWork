package com.tennis.matching.domain.match.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "matches")
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(name = "match_num")
    private Integer matchNum; // 경기인원(2, 4)

    @Column(name = "applicant_num")
    private Integer applicantNum;

    private MatchStatus status; // MALE, FEMALE, ALL

    private MatchGender gender; // OPEN, CLOSE

    @Lob
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;

    public Match(
                  Long id,
                  Stadium stadium,
                  Integer matchNum,
                  Integer applicantNum,
                  MatchStatus status,
                  MatchGender gender,
                  String content,
                  LocalDateTime startAt
                ) {

        this.id = id;
        this.stadium = stadium;
        this.matchNum = matchNum;
        this.applicantNum = applicantNum;
        this.status = status;
        this.gender = gender;
        this.content = content;
        this.startAt = startAt;
    }
}
