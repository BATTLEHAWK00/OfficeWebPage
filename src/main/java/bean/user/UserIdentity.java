package bean.user;

/**
 * @class: UserIdentity
 * @description:
 * @author: YXL
 **/
public enum UserIdentity {
	Manager("管理员", 1), Student("学生", 2), Other("其它", 3);
	private final String name;
	private final int index;

	UserIdentity(String name, int index) {
		this.index = index;
		this.name = name;
	}

	public static UserIdentity getIdentity(int index) {
		for (var i : values())
			if (i.getIndex() == index)
				return i;
		return null;
	}

	public int getIndex() {
		return index;
	}
}
