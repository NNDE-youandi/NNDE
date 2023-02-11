package com.ssafy.youandi.entity.user.survey;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "survey")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private long surveyId;

    private String survey;
}
