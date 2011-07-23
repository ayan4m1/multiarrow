MultiArrow
==========

MultiArrow is a custom/multiple arrow plugin for CraftBukkit.

## Dependencies

MultiArrow is only compatible with CraftBukkit build(s) 1000+.
It relies on the new permissions system which was integrated
into CraftBukkit as of build 1000.

## Usage

When holding a bow, left click to change arrow types. Right
click to fire the selected arrow type.

## Permissions

Using the new permissions system built into CraftBukkit, it is
possible to control which arrow types users have access to.

## Arrow Types

The following arrow types are currently implemented:

1. Explosive - Creates a small TNT-like explosion at target
2. Fire - Burns entities / lights blocks on fire
3. Lightning - Calls down lightning at target
4. Sharp - Lethal to entities, destroys blocks it hits
5. Drill - Digs entities into a hole or excavates downwards

## Custom Arrow Types

You can easily define your own arrow types. Simply add a class
to the 'com.ayan4m1.multiarrow.arrows' package. Your class must
implement CustomArrowEffect. The hitEntity method tells you
that an arrow of this type has hit an entity, and the hitGround
method likewise informs you that this type of arrow has been
lodged in a block.
