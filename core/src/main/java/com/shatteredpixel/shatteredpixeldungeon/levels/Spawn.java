/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.levels;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.noosa.audio.Music;

public class Spawn extends Level {

	{
		color1 = 0x534f3e;
		color2 = 0xb9d661;

		viewDistance = 10;
	}

	@Override
	public void playLevelMusic() {
		Music.INSTANCE.play(Assets.Music.CAVES_BOSS, true);
	}

	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_CAVES;
	}

	@Override
	public String waterTex() {
		return Assets.Environment.WATER_CAVES;
	}

	private static int WIDTH = 100;
	private static int HEIGHT = 100;

	@Override
	protected boolean build() {

		setSize(WIDTH, HEIGHT);

		//setup exit area above main boss arena
		Painter.fill(this, 0, 0, 99, 99, Terrain.EMPTY);
		Painter.fill(this, 49, 49, 1, 1, Terrain.ENTRANCE);
		LevelTransition exit = new LevelTransition(this, 1, LevelTransition.Type.REGULAR_EXIT);
		exit.set(Math.round(WIDTH),Math.round(HEIGHT),Math.round(WIDTH),Math.round(HEIGHT));
		transitions.add(exit);
		return true;
	}

	@Override
	protected void createMobs() {

	}

	@Override
	protected void createItems() {

	}


	@Override
	public String tileName(int tile) {
		switch (tile) {
			case Terrain.GRASS:
				return Messages.get(CavesLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(CavesLevel.class, "high_grass_name");
			case Terrain.WATER:
				return Messages.get(CavesLevel.class, "water_name");
			case Terrain.STATUE:
				//city statues are used
				return Messages.get(CityLevel.class, "statue_name");
			default:
				return super.tileName(tile);
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.WATER:
				return super.tileDesc(tile) + "\n\n" + Messages.get(Spawn.class, "water_desc");
			case Terrain.ENTRANCE:
			case Terrain.ENTRANCE_SP:
				return Messages.get(CavesLevel.class, "entrance_desc");
			case Terrain.EXIT:
				//city exit is used
				return Messages.get(CityLevel.class, "exit_desc");
			case Terrain.HIGH_GRASS:
				return Messages.get(CavesLevel.class, "high_grass_desc");
			case Terrain.WALL_DECO:
				return Messages.get(CavesLevel.class, "wall_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(CavesLevel.class, "bookshelf_desc");
			//city statues are used
			case Terrain.STATUE:
				return Messages.get(CityLevel.class, "statue_desc");
			default:
				return super.tileDesc(tile);
		}
	}
}
