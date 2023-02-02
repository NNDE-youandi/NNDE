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
    private int gamer1;
    @Column(name = "gamer2")
    private int gamer2;
    @Column(name = "gamer3")
    private int gamer3;
    @Column(name = "gamer4")
    private int gamer4;
    @Column(name = "gamer5")
    private int gamer5;
    @Column(name = "gamer6")
    private int gamer6;

}
