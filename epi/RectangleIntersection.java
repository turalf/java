public class RectangleIntersection{
	public static void main(String ... args){
	
	}

	private Rectangle intersectRectangles(Rectangle r1, Rectangle r2){
		Rectangle first = r1.x > r2.x ? r2 : r1;
		Rectangle second = r1.x > r2.x ? r1 : r2;

		if(doesIntersect(first,second)){
			if(first.y>second.y)
				return new Rectangle(second.x,Math.max(first.y, second,y),first.x +  first.width - second.x,first.y + first.h - second.y);
			else
				return new Rectangle(second.x,Math.max(first.y, second,y),first.x +  first.width - second.x,second.y + second.h - first.y);
				
		}
		
		return Rectangle(-1,-1,-1,-1);	
	}

	private Rectangle doesIntersect(Rectangle first, Rectangle second){

		return !(second.x > first.x + first.height ||
			second.y + second.height < first.y ||
			second.y > first.y + first.height);
	}
}

class Rectangle{
	int x, y, width, high;

	public Rectangle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
