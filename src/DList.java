
public class DList {
	private static class Node {
		Node next;
		Node prev;
		int value;
		Node(int value){this.value = value; this.next = this.prev = null;}
		Node(int value, Node next, Node prev){this.value = value; this.next = next; this.prev = prev;}
	}
	
	Node head, tail;
	int size = 0;
	
	public DList(){head = tail = null;}
	public DList(int ball){
		for (int i = 0; i < ball * 2 + 1; i++){
			int v = (i<ball)?1:-1;
			v = (i==ball)?0:v;
			insert(i, v);
		}
	}
	public void insertFirst(int value){
		Node p = new Node(value);
		if (head == null) head = tail = p;
		else {
			p.next = head;
			head.prev = p;
			head = p;
		}
		size++;
	}
	
	public int get(int index){
		Node p = head;
		int i = 0;
		while(p != null && i!=index) {i++; p=p.next;}
		return p.value;
	}
	
	public void insertLast(int value){
		if (head == null) insertFirst(value);
		else {
			Node p = new Node(value);
			tail.next = p;
			p.prev = tail;
			tail = p;
		}
		size++;
	}
	
	public void insert(int index, int value){
		if (index < 0 || index > size) return;
		else if (index == 0) insertFirst(value);
		else if (index == size) insertLast(value);
		else {
			Node t = head;
			int i = 0;
			while (i++ != index && t.next != null) t = t.next;
			Node p = new Node(value, t.next, t);
			t.next.prev = p;
			t.next = p;
			size++;
		}
	}

	public void hoanVi(int vt1, int vt2){
		if (vt1 < 0 || vt2 < 0 || vt1 >= size || vt2 >= size) return;
		if (vt1 > vt2){int t = vt1; vt1 = vt2; vt2 = t;}
		
		Node n1 = head;
		Node n2 = head;
		
		int i = 0;
		
		while (vt1 - i++ != 0) n1 = n1.next; i = 0;
		while (vt2 - i++ != 0) n2 = n2.next; i = 0;
		
		if (size > 2){
			if (vt1 == 0 && vt2 != size-1){
				if (vt1 == vt2 - 1){
					n2.next.prev = head; head.next = n2.next; head.prev = n2;
					n2.next = head; n2.prev = null; head = n2;
				} else {
					Node t = n1.next;
					n2.prev.next = n1;
					n1.prev = n2.prev;
					n1.next = n2.next;
					n2.next.prev = n1;
					t.prev = n2;
					n2.next = t;
					n2.prev = null;
					head = n2;
				}
			} else if (vt2 == size-1 && vt1 != 0){
				if (vt2 == vt1 + 1){
					n1.prev.next = n2;
					n2.prev = n1.prev;
					n2.next = n1;
					n1.prev = n2;
					n1.next = null;
					tail = n1;
				} else {
					Node p = n2.prev;		
					n1.prev.next = n2;
					n2.prev = n1.prev;
					n2.next = n1.next;
					n1.next.prev = n2;
					p.next = n1;
					n1.next = null;
					n1.prev = p;
					tail = n1;				
				}
			} else if (vt1 == 0 && vt2 == size-1){
				Node p = n2.prev;
				Node t = n1.next;		
				p.next = n1;
				n1.next = null;
				n1.prev = p;
				tail = n1;
				t.prev = n2;
				n2.prev = null;
				n2.next = t;
				head = n2;				
			} else if (vt1 + 1 == vt2){
				n2.prev = n1.prev;
				n1.prev.next = n2;
				n2.next.prev = n1;
				n1.next = n2.next;
				n2.next = n1;
				n1.prev = n2;
			} else {
				n1.prev.next = n2;
				n2.prev = n1.prev;
				Node p = n1.next;
				while (p.next.value != n2.value) p = p.next;
				n2.next.prev = p;
				p.next = n2.next;
				n2.next = n1.next;
				n1.next.prev = n2;
				p.next.prev = n1;
				n1.next = p.next;
				p.next = n1;
				n1.prev = p;
			}			
		} else if (vt1 == vt2){
			return;
		} else {
			Node p = new Node(head.value);
			tail.next = p;
			tail.prev = null;
			p.prev = tail;
			head = tail;
			tail = p;
		}
	}
			
	public String toString(){
		String out = "";
		Node p = head;
		while (p != null){
			String t = p.value == 1 ? "B":"R";
			t = p.value == 0 ? "_":t;
			
			out += t + " ";
			p=p.next;
		}
		return out;
	}
	public int[] toArray(){
		Node p = head;
		int matrix[] = new int[size];
		int i = 0;
		while (p!=null){
			matrix[i++] = p.value;
			p = p.next;
		}
		return matrix;
	}
}
