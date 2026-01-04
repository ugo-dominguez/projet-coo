
public abstract class PassiveSkill implements GameObserver, Timed {
    protected int duration = -1;

    public abstract String getName();

    @Override
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void decreaseDuration() {
        if (duration > 0) {
            duration--;
        }
    }
}
