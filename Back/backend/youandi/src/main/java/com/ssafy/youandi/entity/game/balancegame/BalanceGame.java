package com.ssafy.youandi.entity.game.balancegame;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "balancegame")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BalanceGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bg_id")
    private long bgId;

    @Column(name = "bg_question1")
    private String bgQuestion1;

    @Column(name = "bg_question2")
    private String bgQuestion2;

}
