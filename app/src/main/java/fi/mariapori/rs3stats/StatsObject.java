package fi.mariapori.rs3stats;

public class StatsObject {
    private RunescapeStats Skill;
    private int Level;
    private long Experience;

    public StatsObject(RunescapeStats skill, int level, long Exp) {
        Skill = skill;
        Level = level;
        Experience = Exp;
    }

    public String getSkill() {
        return Skill.toString();
    }

    public void setSkill(RunescapeStats skill) {
        Skill = skill;
    }

    public void setExperience(long experience) { Experience = experience; }

    public int getLevel() { return Level; }
    public long getExp() { return Experience; }

    public void setLevel(int level) {
        Level = level;
    }
}
