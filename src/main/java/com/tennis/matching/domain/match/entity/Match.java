package com.tennis.matching.domain.match.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
    private Integer matchNum; // 경기인원(2, 4 선택)

    @Column(name = "applicant_num")
    private Integer applicantNum;  // 신청인원(default 0)

    @Enumerated(EnumType.STRING)
    private MatchStatus status; // OPEN, CLOSE(default OPEN)

    @Enumerated(EnumType.STRING)
    private MatchGender matchGender; // MALE, FEMALE, ALL

    @Lob
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;

    private Integer matchDay;

    @Builder
    public Match(Long id, Stadium stadium, Integer matchNum, Integer applicantNum, MatchStatus status, MatchGender matchGender, String content, LocalDateTime startAt, Integer matchDay) {
        this.id = id;
        this.stadium = stadium;
        this.matchNum = matchNum;
        this.applicantNum = applicantNum;
        this.status = status;
        this.matchGender = matchGender;
        this.content = content;
        this.startAt = startAt;
        this.matchDay = matchDay;
    }

    public void update(Stadium stadium, MatchUpdateRequest matchUpdateRequest) {
        this.stadium = stadium;
        this.matchNum = matchUpdateRequest.getMatchNum();
        this.matchGender = MatchGender.valueOf(matchUpdateRequest.getMatchGender().toUpperCase());
        this.content = matchUpdateRequest.getContent();
        this.startAt = matchUpdateRequest.getStartAt();
    }

    public void increaseApplicantNum() {
        this.applicantNum++;
    }

    public void decreaseApplicantNum() {
        this.applicantNum--;
    }

    public int openOrClosedMatch() {
        return this.matchNum - this.applicantNum;
    }

    public void updateStatus() {
        if(this.startAt.isAfter(LocalDateTime.now()) && openOrClosedMatch() > 0) {
            this.status = MatchStatus.OPEN;
        } else {
            this.status = MatchStatus.CLOSE;
        }
    }

}
