package com.everydayhabits.product.module.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    public Level() {
    }

    public Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Level{" + "number=" + id + ", description='" + description + '\'' + '}';
    }
}
