package torneo;
public class Match {
    Player player1;
    Player player2;
    Arbitro arbitro;
    Ball ball;
    
    Thread thread1 = new Thread(player1);
    Thread thread2 = new Thread(player2);
    Thread refereeThread = new Thread(arbitro); 
    
    
    Match( int id1, int id2 ) {
        //this.ball = new Ball(1); // Il giocatore 1 inizia con la palla
        this.ball = new Ball(1);
        this.player1 = new Player(id1,"a",ball);
        this.player2 = new Player(id2,"b",ball);
        this.arbitro = new Arbitro(ball);


        thread1 = new Thread(this.player1);
        thread2 = new Thread(this.player2);
        refereeThread = new Thread(this.arbitro);


        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);

    }
    
    public int startMatch(){
        thread1.start();
        thread2.start();
        refereeThread.start();

        try {
            thread1.join();
            thread2.join();
            refereeThread.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Match concluso.");
        
        return this.ball.getWinnerId();
    }
}