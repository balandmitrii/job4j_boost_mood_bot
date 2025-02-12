package ru.job4j.bmb.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "mb_mood_content")
public class MoodContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ig;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private Mood mood;

    private String text;

    public MoodContent(Mood mood, String text) {
        this.mood = mood;
        this.text = text;
    }

    public Long getIg() {
        return ig;
    }

    public void setIg(Long ig) {
        this.ig = ig;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoodContent that = (MoodContent) o;
        return Objects.equals(ig, that.ig) && Objects.equals(mood, that.mood) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ig, mood, text);
    }
}
