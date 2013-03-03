package in.thekreml.plugins.multiarrow;

import org.bukkit.entity.Arrow;

public interface TimedArrowEffect extends ArrowEffect {
	public Runnable getDelayTriggerRunnable(Arrow arrow);
	public long getDelayTicks();
}
