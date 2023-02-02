package com.ssafy.youandi.entity.mypageinfo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "point")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private long pointId;

    @Column(name = "record_id")
    private long recordId;

    @Column(name = "gamer1")
    private String gamer1;
    @Column(name = "gamer2")
    private String gamer2;
    @Column(name = "gamer3")
    private String gamer3;
    @Column(name = "gamer4")
    private String gamer4;
    @Column(name = "gamer5")
    private String gamer5;
    @Column(name = "gamer6")
    private String gamer6;

}
