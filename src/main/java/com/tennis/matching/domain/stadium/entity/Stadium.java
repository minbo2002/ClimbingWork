package com.tennis.matching.domain.stadium.entity;

import com.tennis.matching.domain.base.BaseTimeEntity;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stadiums")
@ToString
public class Stadium extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="stadium_id")
    private Long id;

    private String name;

    @Lob
    private String content;

    private Boolean parking;

    private Boolean rental;

    private String address;

    @Column(name ="like_count")
    private Long likeCount;

    @Column(name ="single_url", length = 200)
    private String singleUrl;

    @Column(name ="multi_url", length = 200)
    private String multiUrl;

    @Builder
    public Stadium(Long id,
                   String name,
                   String content,
                   Boolean parking,
                   Boolean rental,
                   String address,
                   Long likeCount,
                   String singleUrl,
                   String multiUrl) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
        this.likeCount = 0L;
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
