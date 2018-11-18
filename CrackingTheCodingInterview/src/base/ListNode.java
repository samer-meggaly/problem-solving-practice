package base;

public class ListNode {

	public int data;
	public ListNode next;

	public ListNode(int i) {
		this.data = i;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		ListNode ptr = this;
		while (ptr != null) {
			sb.append(ptr.data);
			if (ptr.next != null)
				sb.append(", ");
			ptr = ptr.next;
		}
		sb.append(" ]");
		return sb.toString();
	}
}
