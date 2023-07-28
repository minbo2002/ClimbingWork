package com.tennis.matching.domain.stadium.entity;

import com.tennis.matching.domain.like.entity.Like;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stadiums")
@ToString
public class Stadium extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="stadium_id", length = 20)
    private Long id;

    @Column(name ="name", length = 50)
    private String name;

    @Lob
    private String content;

    @Column(name = "like_count", length = 20)
    private Long likeCount;

    @Column(name = "parking")
    private Boolean parking;

    @Column(name = "rental")
    private Boolean rental;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name ="single_url", length = 200)
    private String singleUrl;

    @Column(name ="multi_url", length = 200)
    private String multiUrl;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @Builder
    public Stadium(Long id,
                   String name,
                   String content,
                   Long likeCount,
                   Boolean parking,
                   Boolean rental,
                   String address,
                   String singleUrl,
                   String multiUrl) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.likeCount = likeCount;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
        this.singleUrl = singleUrl;
        this.multiUrl = multiUrl;
    }

    public void update(StadiumUpdateRequest stadiumUpdateRequest) {
        this.name = stadiumUpdateRequest.getName();
        this.content = stadiumUpdateRequest.getContent();
        this.parking = stadiumUpdateRequest.getParking();
        this.rental = stadiumUpdateRequest.getRental();
        this.address = stadiumUpdateRequest.getAddress();
    }

    public void updateUrl(String singleUrl) {
        this.singleUrl = singleUrl;
    }
}
