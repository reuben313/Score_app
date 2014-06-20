package Utils;

public class Tuple<T1,T2> {
    private T1 first;
    private T2 second;
	public Tuple(T1 t1,T2 t2){
	setFirst(t1);
	setSecond(t2);
	}
	public T1 getFirst() {
		return first;
	}
	private void setFirst(T1 first) {
		this.first = first;
	}
	public T2 getSecond() {
		return second;
	}
	private void setSecond(T2 second) {
		this.second = second;
	}
	
	
}
