package fi.mariapori.rs3stats;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class StatsObject {
    private String Skill;
    private int Level;

    public StatsObject(String skill, int level) {
        Skill = skill;
        Level = level;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public int getLevel() { return Level; }

    public void setLevel(int level) {
        Level = level;
    }
}
