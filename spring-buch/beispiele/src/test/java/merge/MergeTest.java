package merge;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class MergeTest extends AbstractDependencyInjectionSpringContextTests {

	public MergeTest() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

	private MergeDemo childOhneMerge;

	private MergeDemo childMitMerge;

	public void setChildMitMerge(MergeDemo childMitMerge) {
		this.childMitMerge = childMitMerge;
	}

	public void setChildOhneMerge(MergeDemo childOhneMerge) {
		this.childOhneMerge = childOhneMerge;
	}

	public void testMerge() {
		assertEquals(1, childOhneMerge.getList().size());
		assertEquals(3, childMitMerge.getList().size());
	}

	@Override
	protected String[] getConfigLocations() {

		return new String[] { "merge.xml" };
	}

}
