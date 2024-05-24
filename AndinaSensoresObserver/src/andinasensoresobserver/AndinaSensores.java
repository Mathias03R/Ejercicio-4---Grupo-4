package andinasensoresobserver;

public class AndinaSensores {

    public static void main(String[] args) {
        //create subject
	Clima clima = new Clima();
		
	//create observers
	Observer cel1 = new ClimaSubscriber("cel1");
	Observer cel2 = new ClimaSubscriber("cel2");
	Observer cel3 = new ClimaSubscriber("cel3");
		
	//register observers to the subject
	clima.register(cel1);
	clima.register(cel2);
	clima.register(cel3);
		
	//attach observer to subject
	cel1.setSubject(clima);
	cel2.setSubject(clima);
	cel3.setSubject(clima);
		
	//now send message to subject
	clima.setClima("soleado", 28.5);
        
        System.out.println("");
        
        clima.setClima("nublado", 15.2);
        
        System.out.println("");
        
        clima.setClima("lluvioso", 21);
        
        System.out.println("");
        
        clima.setClima("lluvioso", 15);
    }
    
}
