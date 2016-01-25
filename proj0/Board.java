import org.junit.Test;

public class Board{
    private static int N=8;
    private Piece[][] pieces;
    private boolean fireTurn=true;
    private boolean isSelected=false;
    private boolean isMoved=false;
    private boolean hasCaptured=false;
    private int x0,y0;//current piece

    public Board(boolean shouldBeEmpty){
        if(shouldBeEmpty) return;
        /**5water  pawn*4
            *4water shield *4
            *3water bomb *4
            *2fire bomb *4
            *1fire shield *4
            *0fire pawn *4*/
        pieces = new Piece[N][N];
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                switch(j){
                    case 0: if(i%2==0) pieces[i][j]=new Piece(true, this, "pawn");
                                break;
                    case 1: if(i%2==1) pieces[i][j]=new Piece(true, this, "shield");
                                break;
                    case 2: if(i%2==0) pieces[i][j]=new Piece(true, this, "bomb");
                                break;
                    case 5: if(i%2==1) pieces[i][j]=new Piece(false, this,"bomb");
                                break;
                    case 6: if(i%2==0)pieces[i][j]=new Piece(false, this, "shield");
                                break;
                    case 7: if(i%2==1) pieces[i][j]=new Piece(false, this, "pawn");
                    default: break;
                }
            }
        }
    }


    private void drawPiece(Piece p,int i, int j){
        if(p==null) return;
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        String group = p.isFire() ? "-fire" : "-water";
        String type;
        if(p.isBomb()) type="bomb";
        else if(p.isShield()) type = "shield";
        else type = "pawn";
        String crown = p.isKing() ? "-crowned" : "";
        String pictureURL = "img/"+type+group+crown+".png";
        StdDrawPlus.picture(i + .5, j + .5, pictureURL , 1, 1);
    } 

    private void drawEmptyBoard(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(isSelected&&x0==i&&y0==j){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);                  
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                } 
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawBoard(){
        drawEmptyBoard();
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                drawPiece(pieces[i][j],i ,j);
            }               
        }
    }

    public Piece pieceAt(int x, int y){
        if(x<N&&y<N){
            return pieces[x][y];
        }
        return null;
    }

    private boolean canMove(int x, int y){
        //if(pieces[x][y]!=null) return false;
        int delt1=Math.abs(x0-x);
        int delt2=Math.abs(y0-y);
        return delt1==1&&delt2==1;
    }
    private boolean canCapture(int x, int y){
        //if(pieces[x][y]!=null) return false;
        int delt1=Math.abs(x0-x);
        int delt2=Math.abs(y0-y);
        if(delt1!=2||delt2!=2){
            return false;
        }
        int captureX=(x0+x)/2;
        int captureY=(y0+y)/2;
        if(pieces[captureX][captureY]==null) return false;
        if(pieces[captureX][captureY].isFire()==pieces[x0][y0].isFire()) return false;
        return true;
    }
    private boolean inBound(int x, int y){
        return (x<N)&&(y<N)&&(x>=0)&&(y>=0);
    }

    public void select(int x, int y){
        //want to select a/another piece
        if(pieces[x][y]!=null && pieces[x][y].isFire()==fireTurn){
            //no piece selected
            if(isSelected!=true){
                x0=x;
                y0=y;
                isSelected=true;

            }
            //one piece selected but not moved
            if(isSelected==true&&isMoved==false){
                x0=x;
                y0=y;
            }
        }
        //want to select a blank square
        if(pieces[x][y]==null&&isSelected==true&&pieces[x0][y0]!=null){
            //if can go back
            if(pieces[x0][y0].isKing()==false&&(y<y0)==fireTurn) return;
            //one piece selected and not moved
            if(isMoved==false&&canMove(x,y)){
                move(x,y);
            }
            //one piece selected and want to capture another
            //first capture
            if(isMoved==false&&canCapture(x,y)){
                capture(x,y);
            }
            //another capture
            if(isMoved==true&&hasCaptured==true&&canCapture(x,y))
                capture(x,y);
        }
    }

    public void explode(){
        if(!pieces[x0][y0].isBomb()){
            return;
        }
        for(int i=x0-1;i<=x0+1&&i<N;++i){
            for(int j=y0-1;j<=y0+1&&i<N;++j){
                if(pieces[i][j]!=null&&!pieces[i][j].isShield()){
                    remove(i,j);
                }
            }
        }
    }

    private void move(int x,int y){
        place(pieces[x0][y0],x,y);
        remove(x0,y0);
        x0=x;
        y0=y;
        isMoved=true;
        if(y0==N-1||y0==0){
            pieces[x0][y0].setKing();
        }
    }

    private void capture(int x, int y){
        remove((x+x0)/2,(y+y0)/2);
        move(x,y);
        hasCaptured=true;
        explode();
    }

    public void endTurn(){
        if(isMoved){
            fireTurn=!fireTurn;
            isSelected=false;
            isMoved=false;
            hasCaptured=false;
        }
    }

    private void start(){
        while(winner()==null){  
            if(StdDrawPlus.mousePressed()){
                int x=(int)StdDrawPlus.mouseX();
                int y=(int)StdDrawPlus.mouseY();
                select(x,y);
            }
            if(StdDrawPlus.isSpacePressed()){
                endTurn();
            }
            drawBoard();
            StdDrawPlus.show(17);    
        }
        System.out.println(winner()+ " win.");
    }

    Piece remove(int x, int y){
        if(!inBound(x,y)){
            System.out.println("Cannot remove: out of bounder!");
            return null;
        }
        if(pieces[x][y]==null){
            System.out.println("Cannot remove: blank square!");
            return null;
        }
        else{
            Piece rm=pieces[x][y];
            pieces[x][y]=null;
            return rm;
        }
    }

    public void place(Piece p, int x, int y){
        pieces[x][y]=p;
    }

    public String winner(){
        int water=0;
        int fire=0;
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                if(pieces[i][j]!=null){
                    if(pieces[i][j].isFire()) fire++;
                    else water++;
                }
            }
        }
        if(water>0 && fire>0){
            return null;
        }
        else if(water==0&&fire==0){
            return "no one";
        }
        else if(water==0){
            return "water";
        }
        else{
            return "fire";
        }
    }

    public static void main(String[] args){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        //board.printPeaces();
        board.start();
    }
}