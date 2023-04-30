package com.tennis.matching.domain.like.entity;

import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "likes")
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Builder
    public Like(Long id, Member member, Stadium stadium) {
        this.id = id;
        this.member = member;
        this.stadium = stadium;
    }

    public static Like of(Stadium stadium, Member member) {
        return Like.builder()
                .stadium(stadium)
                .member(member)
                .build();
    }
}
