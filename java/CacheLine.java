public class CacheLine {
	
	/*
	 * Line 의 구조 클래스 
	 */
	
	Boolean valid;
	int tag;
	int[] data;
	
	public CacheLine(int blockSize) {
		this.valid = false; // 비어있음 초기에 
		this.tag = 0; // 이거 몇으로 설정해야할까? 
		this.data = new int[blockSize]; // 오버라이드 하기로 하긴 했는데, 그냥 이대로 data 어레이로 0 이면 direct map, 1이면 2-way 로 구현되어도 될지도
	}
	
	// Valid 한가?
	public boolean isValid() { 
		return valid; 
	}
	
	// tag 가져오기 
	public int getTag() { 
		return tag; 
	}
	
	// data 가져오기 
	public int[] getData() {
		return data;
	}
	
	// 태그가 동일한가?   
	public boolean isSameTag(int tag) {
		if (this.tag == tag) {
			return true;
		}
		return false;
	}
}
