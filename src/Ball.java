import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Ball {	
	private int n, change, i, ball, noSwap;
	private static int wShow = 2;
	private DList dList;
	private static PanelBall pBall = new PanelBall();
	private static int timeDelay = 500;
	private static int algorithm_type = 1; // 1 - Normal || 2 - Quick
	public Ball(int ball){
		this.ball = ball;
		noSwap = 0;
		n = ball * 2 + 1;
		change=0;
		i = n/2;
		dList = new DList(ball);
	}
	
	public int[] getArray(){
		return dList.toArray();
	}
		
	private void delay(int timeDelay){
		try {
			Thread.sleep(timeDelay);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void paint(int timeDelay){
		delay(timeDelay);
		if (wShow  == 0 || wShow == 1 )System.out.println(dList.toString());
		if (wShow == 2 || wShow == 0) {
			pBall.set(getArray());
			pBall.repaint();			
		}
	}

	public void algorithm_normal(int timeDelay){
		while(true){
			int size = n-change;
			if (i+1<size){
				if (dList.get(i+1)==-1) dList.hoanVi(i, i-=1);
				else dList.hoanVi(i,i+=2);
			} else {
				boolean state = false;
				if (dList.get(i-1)==1) {dList.hoanVi(i, i-=1);change+=1;}
				else if (change==ball) break;
				else if ((dList.get(i-1)==-1 )){
					while((dList.get(i-1)!=1) && i-2 >= 0){
						if (dList.get(i-2)==1) state = true;
						 	if (i+2 < n-change) if (dList.get(i+2)==1) break;
						dList.hoanVi(i, i-=2);
						noSwap++;
						paint(timeDelay);
						if (state == true) break;
					}
					if (state == true){
						dList.hoanVi(i, i+=1);
						noSwap++;
						paint(timeDelay);
					} 
					continue;
				}
			}
			noSwap++;
			paint(timeDelay);
		}
	}

	public void algorithm_quick(int timeDelay){
		int id =  i-1;
		int huong = 0;
		int size = dList.size-1;
		int start = 0;
		dList.hoanVi(i, id);
		noSwap++;
		paint(timeDelay);
		
		while(size != start){
			if (id + 1 <= size && id - 1 >= start ){
				if (huong == 0){
					if (dList.get(id+1)==1 && dList.get(id-1)==1) {dList.hoanVi(id, id+=2); huong = 0;}
					else if (dList.get(id+1)==-1 && dList.get(id-1)==1) {dList.hoanVi(id, id+=1); huong = 1;}
				} else {
					if (dList.get(id+1)==-1 && dList.get(id-1)==-1) {dList.hoanVi(id, id-=2); huong = 1;}
					else if (dList.get(id+1)==-1 && dList.get(id-1)==1) {dList.hoanVi(id, id-=1); huong = 0;}
				}
			} else {
				if (id == size){
					if (dList.get(id-1) == 1) dList.hoanVi( id, id-=1);
					else dList.hoanVi( id, id-=2);
					huong = 1;
					size--;
				} else if (id == start){
					if (dList.get(id+1) == 1) dList.hoanVi(id, id+=2);
					else dList.hoanVi( id, id+=1);
					huong = 0;
					start++;
				}
			}
			noSwap++;
			paint(timeDelay);
		}
	}
	private int calcBack(int num){
		int sum = 0;
		while (num != 0){
			sum += num--;
		}
		return sum;
	}
	
	public int calcNoSwap_Algorithm_Normal(){
		int temp = this.ball * (this.ball * 2 + 1);
		temp += this.ball%2==0?calcBack(this.ball-1):(calcBack(this.ball-1)-(3*(this.ball-1))/2);
		return temp;
	}
	public int calcNoSwap_Algorithm_Quick(){
		return this.ball * this.ball + this.ball * 2;
	}
	private static Ball input(){
		int noBall = 0;
		if (wShow == 0 || wShow == 1 || wShow == 3){
			try {
				System.out.print("How many balls: ");
				Scanner kb = new Scanner(System.in);
				noBall = kb.nextInt();
				if (noBall == 0) System.out.print("Input Invalid");
			} catch (Exception e) {
				System.out.print("Input Invalid");
				System.exit(0);
			}
		}
		else {
			try {
				noBall = Integer.parseInt(JOptionPane.showInputDialog(null,
						  "How many balls?",
						  "Input Ball",
						  JOptionPane.INFORMATION_MESSAGE));
				if (noBall == 0) JOptionPane.showMessageDialog(null, "Input Invalid");
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "Input Invalid");
				System.exit(0);
			}
		}
		if (noBall == 0) System.exit(0);
		return new Ball(noBall);
	}
	
	private static void initFrame(JFrame jFrame,Ball t){
		pBall.set(t.getArray());
		jFrame = new JFrame("Swap Ball");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(t.n*50+40,110);
		jFrame.add(pBall);
		jFrame.setVisible(true);
		jFrame.setFocusableWindowState(true);
	}
	private static String initCmd(Ball t){
		if (wShow == 3) return "y";
		Scanner kb = new Scanner(System.in);
		System.out.print("Initial Condition: "); System.out.print(t.dList.toString());
		System.out.print("\nContinue (y/n): ");
		String _t = kb.nextLine();
		return _t;
	}
	private static char cont(JFrame jFrame,Ball t){
		String _t = "n";
		if (wShow == 1 || wShow == 3){
			_t = initCmd(t);
		} else if (wShow == 2) {
			initFrame(jFrame, t);
			int more = JOptionPane.showConfirmDialog(null, "Continue?", "Continue", JOptionPane.YES_NO_OPTION);
			if (more == JOptionPane.YES_OPTION){
				return 'y';
			} else System.exit(0);
		} else {
			initFrame(jFrame, t);
			_t = initCmd(t);
		}
		return _t.charAt(0);
	}
	
	public static void checkMode(String args[]){
		if (args.length < 3 && args.length > 1) {
			System.out.println("Program Error!!!");
			System.exit(0);
		}
		if (args.length >= 1){
			if (args[0].contains("DOS") && args[0].contains("WIN")) wShow = 0;
			else if (args[0].contains("DOS") && !args[0].contains("WIN")) wShow = 1;
			else if (!args[0].contains("DOS") && args[0].contains("WIN")) wShow = 2;
			else if (!args[0].contains("DOS") && !args[0].contains("WIN") && args[0].contains("CALC") ) wShow = 3;	
		}
		if (args.length >= 2){
			if (args[1].equalsIgnoreCase("NORMAL")) algorithm_type = 1;
			else if (args[1].equalsIgnoreCase("QUICK")) algorithm_type = 2;			
		}
		if (args.length >= 3){
			timeDelay = Integer.parseInt(args[2]);						
		}
	}
	
	public static void done(JFrame jFrame, int noSwap){
		if (wShow == 0 || wShow == 1)System.out.print("Done. " + noSwap + " swaps required.");
		else if (wShow == 2){
			JOptionPane.showMessageDialog(jFrame, "Done. " + noSwap + " swaps required.");
			System.exit(0);
		}
	}
	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JFrame jFrame = null;
		Ball t = null;
		char cont;
		checkMode(args);
		t = input();
		cont = cont(jFrame,t);			
		if (cont != 'y') System.exit(0);
		else{
			if (wShow != 3){
				if (algorithm_type == 2) t.algorithm_quick(timeDelay);
				else t.algorithm_normal(timeDelay);
				done(jFrame, t.noSwap);
			} else {
				if (args[0].contains("_1"))
					System.out.println("Done. " + t.calcNoSwap_Algorithm_Normal() + " swaps required.");
				else if (args[0].contains("_2")) System.out.println("Done. " + t.calcNoSwap_Algorithm_Quick() + " swaps required.");
			}
		}
	}
}
