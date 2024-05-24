package andinasensoresobserver;

public class ClimaSubscriber implements Observer {
	
	private String name;
	private Subject clima;
	
	public ClimaSubscriber(String nm){
		this.name=nm;
	}
	@Override
	public void update(String msg) {
		if(msg == null){
			System.out.println(name+":: No new message");
		}else
		System.out.println("Mensaje enviado a "+name+": "+msg);
	}

	@Override
	public void setSubject(Subject sub) {
		this.clima=sub;
	}

}
