import java.util.*;

public class Main {
	static L1Cache l1Cache = new L1Cache();
	static L2Cache l2Cache = new L2Cache();
	static L3Cache l3Cache = new L3Cache();
	static MainMemory l4Cache = new MainMemory();
	
	static int[] dataset; //임시 
	
	public static void main(String [] args) throws Exception {
		// 예시 데이터셋 설정
		dataset = new int[8000];
		for (int i = 0; i < dataset.length; i++) {
			dataset[i] = i * 10; 
		}
		
		int[] tests = {5, 133, 5, 261, 5, 133, 389, 5, 133};
		
		for (int addr : tests) {
			int value = access(addr);
			System.out.println("addr = " + addr + " value = " + value);
		}
	}

	private static int access(int address) throws Exception {
		/*
		 * L1 -> L2 -> L3 -> L4 -> 원본 데이터 탐색 
		 */
		
		if(l1Cache.lookUp(address)) {
			return dataset[address];
		} else if (l2Cache.lookUp(address)) {
			l1Cache.insert(address);
			return dataset[address];
		} else if (l3Cache.lookUp(address)) {
			l2Cache.insert(address);
			l1Cache.insert(address);
			return dataset[address];
		} else if (l4Cache.lookUp(address)) {
			l3Cache.insert(address);
			l2Cache.insert(address);
			l1Cache.insert(address);
			return dataset[address];
		} else {
			// 몰라 
			return 0;
		}
	}
	
	private void printHitMiss() {
		System.out.println("L1 hits: " + l1Cache.getHit() + ", " + "L1 misses: " + l1Cache.getMiss());
		System.out.println("L2 hits: " + l2Cache.getHit() + ", " + "L2 misses: " + l2Cache.getMiss());
		System.out.println("L3 hits: " + l3Cache.getHit() + ", " + "L3 misses: " + l3Cache.getMiss());
		System.out.println("L3 hits: " + l4Cache.getHit() + ", " + "L3 misses: " + l4Cache.getMiss());
		
	}
}