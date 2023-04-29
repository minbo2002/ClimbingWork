package com.tennis.matching.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    @Column(name = "member_id")
    private Long id;

    @JsonIgnore
    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 50)
    private String password;

    @JsonIgnore
    @Column(name = "nickname", length = 100)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated", length = 50)
    private boolean activated; // 활성화 여부

    @ManyToMany
    @JoinTable(
               name = "user_authority",
               joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
               inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
              )
    private Set<Authority> authorities;
}
