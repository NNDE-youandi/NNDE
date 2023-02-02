package com.ssafy.youandi.entity.game.liargame;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "liargame")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LiarGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lg_id")
    private long lgId;

    @Column(name = "lg_type")
    private String lgType;

    @Column(name = "lg_word")
    private String lgWord;
}
