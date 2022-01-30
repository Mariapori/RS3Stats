package fi.mariapori.rs3stats;

public class StatsObject {
    private RunescapeStats Skill;
    private int Level;

    public StatsObject(RunescapeStats skill, int level) {
        Skill = skill;
        Level = level;
    }

    public String getSkill() {
        return Skill.toString();
    }

    public void setSkill(RunescapeStats skill) {
        Skill = skill;
    }

    public int getLevel() { return Level; }

    public void setLevel(int level) {
        Level = level;
    }
}
