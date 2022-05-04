package Game;

/**
 * Main class
 * @author Tung Doan
 */
public class PikachuMain {
	PikachuFrame frame;

	public PikachuMain() {
		frame = new PikachuFrame();
		MyTimeCount timeCount = new MyTimeCount();
		timeCount.start();
		new Thread(frame).start();
	}

	public static void main(String[] args) {
		new PikachuMain();
	}

	class MyTimeCount extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.setTime(frame.getTime() - 1);
				if (frame.getTime() == 0) {
					frame.showDialogNewGame("Full time\nDo you want play again?", "Lose");
				}
			}
		}
	}
}
