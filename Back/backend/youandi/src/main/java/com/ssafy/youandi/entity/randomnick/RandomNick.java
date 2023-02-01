package com.ssafy.youandi.entity.randomnick;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "randomnick")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RandomNick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nick_id")
    private long nickId;

    @Column(name = "random_nickname")
    private String randomNickname;
}
