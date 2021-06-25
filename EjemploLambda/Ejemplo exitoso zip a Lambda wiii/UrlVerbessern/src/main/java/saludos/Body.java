package saludos;

public class Body {
    int start = 0;
    int length = 0;

    public Body(){
    }

    public Body(int start, int length){
        this.start = start;
        this.length = length;
    }

    public int getStart(){
        return start;
    }

    public void setStart(int start){
        this.start = start;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

}
