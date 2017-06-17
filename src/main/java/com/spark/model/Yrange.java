package com.spark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 2017/6/11.
 */
@Entity
public class Yrange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
