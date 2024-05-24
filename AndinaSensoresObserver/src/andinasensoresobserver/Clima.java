package andinasensoresobserver;

import java.util.ArrayList;
import java.util.List;

public class Clima implements Subject {

	private List<Observer> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();
	
	public Clima(){
		this.observers=new ArrayList<>();
	}
	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
		if(!observers.contains(obj)) observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		synchronized (MUTEX) {
		observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer> observersLocal = null;
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed=false;
		}
		for (Observer obj : observersLocal) {
			obj.update(message);
		}

	}
	
	//method to post message to the topic
	public void setClima(String clima, double temperatura){
		this.message="Hoy se espera un clima "+clima+ " y una temperatura de "+temperatura+" grados Celsius.";
                this.changed=true;
		notifyObservers();
                
                if ("lluvioso".equals(clima)){
                    advertenciaLluvia();
                }
                if (temperatura < 20){
                    advertenciaBajasTemperaturas();
                }
	}
        
        private void advertenciaLluvia(){
		this.message="ADVERTENCIA: Se esperan fuertes lluvias";
		this.changed=true;
		notifyObservers();
	}
        
        private void advertenciaBajasTemperaturas(){
		this.message="ADVERTENCIA: Se esperan bajas temperaturas";
		this.changed=true;
		notifyObservers();
	}

}
