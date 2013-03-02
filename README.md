MultiArrow
==========

MultiArrow is a custom/multiple arrow plugin for CraftBukkit.

## License

MultiArrow is released under the GNU Lesser General Public
License. See the included LICENSE file for more information.

## Dependencies

MultiArrow is compatible with CraftBukkit v1.4.7-R1.0.

## Usage

When holding a bow, left click to select the next arrow type.
Hold down Sneak (left shift by default) to select the previous
arrow type. Right click to fire the selected arrow type.

## SuperPerms / PermissionsBukkit

Using the new permissions system built into CraftBukkit, it is
possible to control which arrow types users have access to, among
other things.

1. multiarrow.use.all allows users to use all arrow types
2. multiarrow.use.\<name\> allows users to use a specific arrow type
3. multiarrow.free-materials bypasses the check for required items
4. multiarrow.free-fees bypasses the check for iConomy fees
5. multiarrow.infinite allows users to fire infinite arrows


## Arrow Types

The following arrow types are currently implemented:

1. Explosive - Creates a small TNT-like explosion at target
2. Lightning - Calls down lightning at target
3. Drill - Digs entities into a hole or excavates downwards
4. Water - Creates source water at target
5. Torch - Creates a torch at target or lights an entity on fire
6. Animal - Spawns a friendly mob at target
7. Teleport - Teleports the player to a random location nearby

## Custom Arrow Types

You can easily define your own arrow types. Check out a copy of the code (or make your own fork on GitHub) and add a class to the 'com.ayan4m1.multiarrow.arrows' package. It should be named <name>ArrowEffect, e.g. TorchArrowEffect.
Your class must implement either ArrowEffect or TimedArrowEffect. The only difference is that TimedArrowEffect also raises an event a certain amount of time after the arrow effect has occured. You can use this to create an arrow that only modifies an area temporarily, or which has a two-stage effect.
The onEntityHitEvent and onGroundHitEvent methods are raised when one of your arrows hits an entity or the ground, respectively. If they should perform the exact same action, you should define a private method and then call it from both event handlers (look at the Drill arrow for an example of this).
If you implement TimedArrowEffect, you should also implement a getDelayTriggerRunnable and getDelayTicks method. The former should construct a new Runnable which will perform your delayed second effect. Make sure the Arrow parameter is declared as final so the Runnable can access it (see the Water arrow for an example of this). The getDelayTicks method simply returns a long integer representing the number of ticks before the event is raised.
Finally, you will need to add your arrow type name (the <name> part of the original example) to the ArrowType enumeration. Simply insert your arrow type at the position you would like it to occupy in the selection list.
If any of this seems unclear, I encourage you to look at the source code and try to start from an existing custom type.
