package com.tennis.matching.domain.review.entity;

import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.member.entity.Member;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reviews")
@ToString
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String writer;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Lob
    private String content;

    @Builder
    public Review(Long id, String writer, Integer score, Member member, Match match, String content) {
        this.id = id;
        this.writer = writer;
        this.score = score;
        this.member = member;
        this.match = match;
        this.content = content;
    }
}
