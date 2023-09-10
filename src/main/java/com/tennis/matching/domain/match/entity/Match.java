package com.tennis.matching.domain.match.entity;

import com.tennis.matching.domain.reservation.entity.Reservation;
import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.review.entity.Review;
import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
@Table(name = "matches")
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", length = 20)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(name = "match_num", length = 2)
    private Integer matchNum; // 경기인원(2, 4 선택)

    @Column(name = "applicant_num", length = 10)
    private Integer applicantNum;  // 신청인원(default 0)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private MatchStatus status; // OPEN, CLOSE(default OPEN)

    @Enumerated(EnumType.STRING)
    @Column(name = "match_gender", length = 10)
    private MatchGender matchGender; // MALE, FEMALE, ALL

    @Lob
    private String content;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Column(name = "match_day", length = 10)
    private Integer matchDay;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @Builder
    public Match(Long id, Stadium stadium, Integer matchNum, Integer applicantNum, MatchStatus status, MatchGender matchGender, String content, LocalDateTime startAt, LocalDateTime endAt, Integer matchDay) {
        this.id = id;
        this.stadium = stadium;
        this.matchNum = matchNum;
        this.applicantNum = applicantNum;
        this.status = status;
        this.matchGender = matchGender;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.matchDay = matchDay;
    }

    public void update(Stadium stadium, MatchUpdateRequest matchUpdateRequest) {
        this.stadium = stadium;
        this.matchNum = matchUpdateRequest.getMatchNum();
        this.matchGender = MatchGender.valueOf(matchUpdateRequest.getMatchGender().toUpperCase());
        this.content = matchUpdateRequest.getContent();
        this.startAt = LocalDateTime.of(matchUpdateRequest.getStartAt(), LocalTime.now());
        this.endAt = LocalDateTime.of(matchUpdateRequest.getEndAt(), LocalTime.now());
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
