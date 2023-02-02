package com.ssafy.youandi.entity.game.bombgame;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bomb")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BombGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bomb_id")
    private long bombId;

    @Column(name = "bomb_question")
    private String bombQuestion;
}
