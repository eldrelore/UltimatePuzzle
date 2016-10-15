package org.eldrelore.puzzle.dto;

import org.eldrelore.puzzle.types.SideDirectionType;
import org.eldrelore.puzzle.types.SideType;

public class Side {
	private SideType type;
	private SideDirectionType direction;

	public Side(SideType type, SideDirectionType direction) {
		this.type = type;
		this.direction = direction;
	}

	public SideDirectionType getDirection() {
		return direction;
	}

	public SideType getType() {
		return type;
	}
}
