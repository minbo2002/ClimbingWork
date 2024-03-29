package com.tennis.matching.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tennis.matching.domain.review.entity.Review;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "members")
@ToString
public class Member {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", length = 20)
    private Long id;

    @JsonIgnore
    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @JsonIgnore
    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "gender", length = 20)
    private String gender;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated; // 활성화 여부

    @ManyToMany
    @JoinTable(
                name = "user_authority",
                joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
                inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
               )
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews;

    @Builder
    public Member(
                   Long id,
                   String username,
                   String password,
                   String nickname,
                   String gender,
                   boolean activated,
                   Set<Authority> authorities
                 ) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.activated = activated;
        this.authorities = authorities;
    }
}
