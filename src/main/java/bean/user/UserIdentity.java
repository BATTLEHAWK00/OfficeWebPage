package bean.user;

/**
 * @class: UserIdentity
 * @description:
 * @author: YXL
 **/
public enum UserIdentity {
	Manager("����Ա", 1), Student("ѧ��", 2), Other("����", 3);
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
