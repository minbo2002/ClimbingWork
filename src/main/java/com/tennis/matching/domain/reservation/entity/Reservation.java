package com.tennis.matching.domain.reservation.entity;

import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.member.entity.Member;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Builder
    public Reservation(Long id, Member member, Match match) {
        this.id = id;
        this.member = member;
        this.match = match;
    }
}
