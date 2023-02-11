package com.ssafy.youandi.entity.keyword;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "keyword")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private long keywordId;

    private String keyword;
}
