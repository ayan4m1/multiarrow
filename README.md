MultiArrow
==========

MultiArrow is a custom/multiple arrow plugin for CraftBukkit.

## Dependencies

MultiArrow is only compatible with CraftBukkit build(s) 1000+.
It relies on the new permissions system which was integrated
into CraftBukkit as of build 1000.

## Usage

When holding a bow, left click to select the next arrow type.
Hold down Sneak (left shift by default) to select the previous
arrow type. Right click to fire the selected arrow type.

## Permissions

Using the new permissions system built into CraftBukkit, it is
possible to control which arrow types users have access to.

1. multiarrow.use.all allows users to use all arrow types
2. multiarrow.use.<name> allows users to use a specific arrow type
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

You can easily define your own arrow types. Simply add a class
to the 'com.ayan4m1.multiarrow.arrows' package. Your class must
implement CustomArrowEffect. The hitEntity method tells you
that an arrow of this type has hit an entity, and the hitGround
method likewise informs you that this type of arrow has been
lodged in a block.
