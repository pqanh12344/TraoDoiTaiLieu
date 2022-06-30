package com.example.traodoitailieu.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="Post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_up")
    private Date updated_up;

    private String demGio;

    public String getDemGio() {
        String k = "";
        Date d1 = java.util.Calendar.getInstance().getTime();
        Date d2 = this.updated_up;

        long diff = d1.getTime() - d2.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        long g = diffDays*24*60 + diffHours*60 + diffMinutes;
        if(g == 0){
            k += "Vừa xong";
        }else if(g < 60){
            k += g + " phút trước";
        }else if(g < 24*60){
            k += (g/60) + " giờ trước";
        }else k = ((g/24)/60) + " ngày trước";

        this.demGio = k;
        return demGio;
    }
}
