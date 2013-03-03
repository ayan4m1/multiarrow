package in.thekreml.plugins.multiarrow.arrows;

import in.thekreml.plugins.multiarrow.ArrowEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.logging.Logger;

import org.reflections.Reflections;

public class ArrowTypes {
	private ArrayList<String> classes = new ArrayList<String>();

	public ArrowTypes() {
		Reflections reflector = new Reflections(ARROW_PACKAGE);
		Set<Class<? extends ArrowEffect>> effects = reflector.getSubTypesOf(ArrowEffect.class);
		for (Class<? extends ArrowEffect> effect : effects) {
			final String fullName = effect.getName();
			classes.add(fullName.substring(fullName.lastIndexOf(".") + 1));
			logger.info("Loaded arrow type " + fullName);
		}

		//TODO: Custom comparator
		Collections.sort(classes, new Comparator<String>() {
		    public int compare(String a, String b) {
		        return b.toString().compareTo(a.toString());
		    }
		});
	}

	public Integer getMaxIndex() {
		return Math.max(0, classes.size() - 1);
	}

	public boolean isType(String name) {
		return classes.contains(getClassName(name));
	}

	public Integer getIndex(String name) {
		return classes.indexOf(name);
	}

	public String getName(Integer index) {
		if (index == -1) {
			return "Normal";
		}

		return classes.get(index);
	}

	public ArrowEffect getType(String name) {
		try {
			return (ArrowEffect)Class.forName(getClassName(name)).newInstance();
		} catch (ClassNotFoundException e) {
			logger.warning("Failed to find class " + name);
			return null;
		} catch (InstantiationException e) {
			logger.warning("Could not instantiate class " + name);
			return null;
		} catch (IllegalAccessException e) {
			logger.warning("Could not access class " + name);
			return null;
		}
	}

	private static String getClassName(String name) {
		return new StringBuilder().append(ARROW_PACKAGE).append(".").append(name).toString();
	}

	private static final Logger logger = Logger.getLogger("Minecraft");
	private static final String ARROW_PACKAGE = "in.thekreml.plugins.multiarrow.arrows";
}
