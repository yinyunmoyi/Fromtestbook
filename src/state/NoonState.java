package state;

public class NoonState extends State {

	@Override
	public void WriteProgram(Work w) {
		// TODO Auto-generated method stub
		if(w.getHour() < 13) {
			System.out.println("午休开始！");
		}else {
			w.setState(new AfternoonState());
			w.WriteProgram();
		}
	}

}
