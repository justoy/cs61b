public class Piece{
    private boolean isFire;
    private Board board;
    private String type;
    private boolean isKing=false;

    Piece(boolean isFire, Board b, String type){
        this.isFire=isFire;
        board=b;
        this.type=type;
    }

    public boolean isFire(){
        return isFire;
    }

    public int side(){
        return isFire? 0 : 1;
    }

    public boolean isKing(){
        return isKing;
    }

    public void setKing(){
        isKing=true;
    }

    public boolean isBomb(){
        return type.equals("bomb");
    }

    public boolean isShield(){
        return type.equals("shield");
    }
}