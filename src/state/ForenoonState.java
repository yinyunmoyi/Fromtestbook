package state;

public class ForenoonState extends State {

	@Override
	public void WriteProgram(Work w) {
		// TODO Auto-generated method stub
		if(w.getHour() < 12) {
			System.out.println("上午工作！");
		}else {
			w.setState(new NoonState());
			w.WriteProgram();
		}
	}

	
}
