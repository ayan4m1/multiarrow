package in.thekreml.plugins.multiarrow.arrows;

import org.bukkit.entity.Arrow;

public interface TimedArrowEffect extends ArrowEffect {
	public Runnable getDelayTriggerRunnable(Arrow arrow);
	public long getDelayTicks();
}
