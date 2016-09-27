package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LRUCacheTest {
	LRUCache lru = new LRUCache(5);
	@Test
	public void testLRUCache() {
	//	fail("Not yet implemented");
		
		assertEquals(-1, lru.get(1));
	}

//	@Test
//	public void testMoveToFront() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testForName() {
//		fail("Not yet implemented");
//	}

}
