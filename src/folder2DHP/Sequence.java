package folder2DHP;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import folder2DHP.Folding.Direction;


public class Sequence {
	
	private List<Element> sequence;
	
	public enum Element {H, P}
	
	public Sequence(String sequence) {
		this.sequence = new ArrayList<Sequence.Element>();
		
		for (char c : sequence.toCharArray()) {
			switch (c) {
			case 'H':
			case '1':
				this.sequence.add(Element.H);
				break;
			case 'P':
			case '0':
				this.sequence.add(Element.P);
				break;
			default:
				break;
			}
		}
	}
	
	public List<Element> getSequence() {
		return sequence;
	}
	
	public void draw(Folding folding) {
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		
		for(Point2D point: folding.getCoordinates()) {
			minX = Math.min(minX, point.x);
			maxX = Math.max(maxX, point.x);
			minY = Math.min(minY, point.y);
			maxY = Math.max(maxY, point.y);
		}
		
		List<List<Character>> buffer = new ArrayList<List<Character>>();
		int maxXX = (((maxX - minX)*2)+1);
		int maxYY = (((maxY - minY)*2)+1);
		for (int i = 0; i < maxYY; i++) {
			List<Character> line = new ArrayList<Character>();
			for (int j = 0; j < maxXX; j++) {
				line.add(' ');
			}
			buffer.add(line);
		}
		Point2D translate = new Point2D(-minX, -minY);
		
		int i = 0;
		for(Point2D point: folding.getCoordinates()) {
			Point2D drawAt = new Point2D((point.x + translate.x) * 2, (point.y + translate.y) * 2);
			switch(sequence.get(i)) {
			case H:
				buffer.get(drawAt.y).remove(drawAt.x);
				buffer.get(drawAt.y).add(drawAt.x, 'H');
				break;
			case P:
				buffer.get(drawAt.y).remove(drawAt.x);
				buffer.get(drawAt.y).add(drawAt.x, 'P');
				break;
			}
			try {
				Point2D next = folding.getCoordinates().get(i+1);
				int diffX = next.x - point.x;
				int diffY = next.y - point.y;
				if(diffX != 0) {
					buffer.get(drawAt.y).remove(drawAt.x + diffX);
					buffer.get(drawAt.y).add(drawAt.x + diffX, '-');
				} else {
					buffer.get(drawAt.y + diffY).remove(drawAt.x);
					buffer.get(drawAt.y + diffY).add(drawAt.x, '|');
				}
			} catch (Exception e) {}
			++i;
		}
		
		Collections.reverse(buffer);
		for(List<Character> line: buffer) {
			for(Character c: line) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	@Override
	public String toString() {
		String s = new String();
		for (Element element : sequence) {
			switch (element) {
			case H:
				s += "H";
				break;
			case P:
				s += "P";
				break;
			default:
				break;
			}
		}
		return s;
	}
}
