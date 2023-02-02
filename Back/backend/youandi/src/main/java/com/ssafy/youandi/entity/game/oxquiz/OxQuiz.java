package com.ssafy.youandi.entity.game.oxquiz;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "oxquiz")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OxQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ox_id")
    private long oxId;

    private String quiz;

    @Column(name = "istrue")
    private boolean isTrue;

    private String description;
}
